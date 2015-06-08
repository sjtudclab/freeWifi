package cn.edu.sjtu.dclab.freewifi.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import cn.edu.sjtu.dclab.freewifi.enums.BusinessType;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantService;
import cn.edu.sjtu.dclab.freewifi.service.IWIFIService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
public class LoginController {
	@Resource(name = "merchantService")
	IMerchantService merchantService;
	
	@Resource(name = "wifiService")
	IWIFIService wifiService;

	@RequestMapping(value = {"/login","/logout"}, method = RequestMethod.GET)
	public ModelAndView loginView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "/editor.html", method = RequestMethod.GET)
	public ModelAndView addAdView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editor");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		Merchant merchant = merchantService.login(username, password);
		if (merchant != null) {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.CURRENT_USER, merchant.getId());
		}else {
			return "redirect:/login";
		}
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		return "manage";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerView(){
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> registerSubmit(String loginname, //登录名
			String tel, String password, String name, //真实姓名
			String address,	double longitude, //经度
			double latitude, String ssid, String wifiPassword,String business,String icon){
		Map<String, Object> map = new HashMap<String, Object>();
		Merchant merchant = new Merchant(loginname, password, name, 
				address, tel,BusinessType.get(Integer.parseInt(business)),icon);
		boolean result = merchantService.addMerchant(merchant);
		if (result) {
			WIFI wifi = new WIFI(ssid, wifiPassword, merchant, longitude, latitude);
			boolean wifiAddResult = wifiService.addWIFI(wifi);
			if (!wifiAddResult) {
				merchantService.deleteMerchant(merchant);
			}else {
				map.put(Constants.CODE, -2);
				map.put(Constants.ERROR_MSG, "Add wifi error");
			}
		}else {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG, "Add merchant error");
		}
		map.put(Constants.CODE, 0);
		return map;
	}

	
}
