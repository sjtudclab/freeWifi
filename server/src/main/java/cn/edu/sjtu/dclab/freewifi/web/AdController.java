package cn.edu.sjtu.dclab.freewifi.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.Orientation;
import cn.edu.sjtu.dclab.freewifi.enums.AdState;
import cn.edu.sjtu.dclab.freewifi.enums.AdType;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantService;
import cn.edu.sjtu.dclab.freewifi.service.IOrientationService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;
import cn.edu.sjtu.dclab.freewifi.util.DateUtils;

@Controller
@RequestMapping(value = "ad")
public class AdController {
	
	@Autowired 
	private IMerchantService merchantService;
	
	@Autowired 
	private IAdService adService;
	
	@Autowired 
	private IOrientationService orientationService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAdView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editor");
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addAdSubmit(HttpServletRequest request, String content,//富媒体存储的内容
			String startDate, //起始日期
			String endDate,//终止日期
			String startHour, String endHour,String name,
			String sex,//：-男、女、不限
			String age,//String-8岁以下，20-35，35-50，50以上、不限
			String education,//String-高中以下、高中、大专、本科、研究生及以上、不限
			String income,//String-3000元以下、3000-7000元、7000-10000元、10000元以上、不限
			boolean isLaunch
			) {
		//System.out.println(startDate+"----"+endDate+"---"+startHour+"------"+endHour+"----"+name+"----"+sex+"----"+education+"----"+income+"----"+age);
		HttpSession session = request.getSession();
		String merchantTag = session.getAttribute(Constants.CURRENT_USER).toString();
		long merchantId = Long.parseLong(merchantTag);
		Merchant merchant = merchantService.getMerchantById(merchantId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (merchant == null) {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"Login error.");
			return map;
		}
		Orientation orientation = new Orientation(sex, age, education, income);
		orientationService.addOrientation(orientation);
		Date _startDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");
		Date _endDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
		AdState state = isLaunch? AdState.LAUNCHING: AdState.READY;
		int _startHour = -1;
		if (startDate != null && !startHour.equals("") && !startDate.equals("-1")) {
			_startHour = transfer(startHour);
		}
		int _endHour = -1;
		if (endHour != null && !endHour.equals("") && !endHour.equals("-1")) {
			_endHour = transfer(endHour);
		}
		Ad ad = new Ad(merchant, new Date(), AdType.SELF, content, _startDate, _endDate, _startHour, 
				name, _endHour, state, orientation);

		boolean addAdResult = adService.addAd(ad);
		if (addAdResult) {
			map.put(Constants.CODE, 0);
			return map;
		}else{
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"Add ad error.");
			return map;
		}
	}

	private int transfer(String hour){
		String part[] = hour.split(":");
		if (part == null || part.length != 3) {
			return -1;
		}else {
			return Integer.parseInt(part[0]) * 3600 +Integer.parseInt(part[1])*60 +Integer.parseInt(part[2]);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAd(@RequestParam(value = "ad_id", required = true) long adId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Ad ad = adService.getAd(adId);
		if (ad != null) {
			boolean deleteResult = adService.deleteAd(ad);
			if (deleteResult) {
				map.put(Constants.CODE, 0);
				return map;
			}
		}
		
		map.put(Constants.CODE, -1);
		map.put(Constants.ERROR_MSG,"delete error.");
		return map;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> publishAd(@RequestParam(value = "ad_id", required = true) long adId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Ad ad = adService.getAd(adId);
		if (ad != null) {
			boolean deleteResult = adService.lauchAd(ad);
			if (deleteResult) {
				map.put(Constants.CODE, 0);
				return map;
			}
		}
		
		map.put(Constants.CODE, -1);
		map.put(Constants.ERROR_MSG,"delete error.");
		return map;
	}
	
	@RequestMapping(value = "/unpublish", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> unpublishAd(@RequestParam(value = "ad_id", required = true) long adId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Ad ad = adService.getAd(adId);
		if (ad != null) {
			boolean deleteResult = adService.unlauchAd(ad);
			if (deleteResult) {
				map.put(Constants.CODE, 0);
				return map;
			}
		}
		
		map.put(Constants.CODE, -1);
		map.put(Constants.ERROR_MSG,"delete error.");
		return map;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Map<String, Object> getList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String merchantTag = session.getAttribute(Constants.CURRENT_USER).toString();
		Map<String, Object> map = new HashMap<String, Object>();
		if (merchantTag == null || merchantTag.equals("")) {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"Login error.");
			return map;
		}
		long merchantId = Long.parseLong(merchantTag);
		Merchant merchant = merchantService.getMerchantById(merchantId);
		if (merchant == null) {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"Login error.");
			return map;
		}
		List<Ad> list = adService.getAdListByMerchant(merchant);
		map.put(Constants.CODE, 0);
		map.put(Constants.DATA,list);
		map.put(Constants.SIZE, list == null?0:list.size());
		return map;
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAdContent(@RequestParam(value = "id") long id) {
		Ad ad = adService.getAd(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (ad != null) {
			map.put(Constants.CODE, 0);
			map.put(Constants.DATA,ad.getContent());
		}else {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"can not get ad by id:"+id);
		}
		return map;
	}
	
	@RequestMapping(value = "/mobile", method = RequestMethod.GET)
	public ModelAndView showAdForMobile(@RequestParam(value = "id",required = true) long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ad");
		return mav;
	}

}
