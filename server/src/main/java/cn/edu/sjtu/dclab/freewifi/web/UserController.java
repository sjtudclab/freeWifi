package cn.edu.sjtu.dclab.freewifi.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;
import cn.edu.sjtu.dclab.freewifi.service.IAdCollectService;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IAdStatsService;
import cn.edu.sjtu.dclab.freewifi.service.IPushService;
import cn.edu.sjtu.dclab.freewifi.service.IUserService;
import cn.edu.sjtu.dclab.freewifi.service.IWIFIService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;
import cn.edu.sjtu.dclab.freewifi.util.DateUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IWIFIService wifiService;
    @Autowired
    private IPushService pushService;
    @Autowired
    private IAdService adService;
    @Autowired
    private IAdCollectService adCollectService;
    @Autowired 
    private IAdStatsService adStatsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(String device_id,String tel,String education,String gender,String password,
                                        String birthdate,String income,String engage,String baby, String job
                                        ) {
        Date date = DateUtils.parseDate(birthdate, "yyyy-MM-dd");
        User user = new User(device_id, Gender.get(Integer.parseInt(gender)), EngageState.get(Integer.parseInt(engage)), Job.get(Integer.parseInt(job)), 
        		BabyState.get(Integer.parseInt(baby)), password, tel, date, Education.get(Integer.parseInt(education)), IncomeType.get(Integer.parseInt(income)), 0);
        boolean result = userService.addUser(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put(Constants.CODE, 0);
        } else {
            map.put(Constants.CODE, -1);
        }
        return map;
    }

	@RequestMapping(value = "/click", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> clickAd(
			@RequestParam(value = "device_id") String deviceId,
			@RequestParam(value = "ad_id") Long adId) {
		User user = userService.getUserByDeviceId(deviceId);
		Ad ad = adService.getAd(adId);
		boolean result = false;
		if (user != null && ad != null) {
			result = adStatsService.addClick(user, ad);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result) {
			map.put(Constants.CODE, 0);
		}else {
			map.put(Constants.CODE, -1);
		}
		return map;
	}
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> notification(
            @RequestParam(value = "device_id") String deviceId,
            @RequestParam(value = "wifi_id") String wifiId) {

        Merchant merchant = wifiService.getWifiById(Integer.parseInt(wifiId)).getMerchant();
        User user = userService.getUserByDeviceId(deviceId);
        pushService.pushNotificationAdByMerchantAndUser(merchant, user);

        boolean result = true;
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put(Constants.CODE, 0);
        } else {
            map.put(Constants.CODE, -1);
        }
        return map;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginFromMobile(String account,String password) {
    	System.out.println(account);
    	System.out.println(password);
    	Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.getUserByTel(account);
        if (user != null && user.getPassword().equals(password)) {
        	map.put(Constants.CODE, 0);
        	return map;
		}
        map.put(Constants.CODE, -1);
        return map;
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> test() {
    	Map<String, Object> map = new HashMap<String, Object>();        
        map.put(Constants.CODE, -1);
        return map;
    }
    
    
}
