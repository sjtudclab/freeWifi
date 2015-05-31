package cn.edu.sjtu.dclab.freewifi.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import cn.edu.sjtu.dclab.freewifi.service.impl.MerchantServiceImpl;
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
			boolean isLauch
			) {
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
		Orientation orientation = new Orientation();
		
		
		
		
		Date _startDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");
		Date _endDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
		AdState state = isLauch? AdState.LAUNCHING: AdState.READY;
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
	public ModelAndView deleteAd(@RequestParam(value = "ad_id", required = true) String adId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public ModelAndView publishAd(@RequestParam(value = "ad_id", required = true) String adId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/unpublish", method = RequestMethod.GET)
	public ModelAndView unpublishAd(@RequestParam(value = "ad_id", required = true) String adId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView getList(@RequestParam(value = "merchant_id", required = false) String merchantId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/mobile/{id}", method = RequestMethod.GET)
	public ModelAndView showAdForMobile(@PathVariable(value = "id") long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/pc/{id}", method = RequestMethod.GET)
	public ModelAndView showAdForPC(@PathVariable(value = "id") long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

}
