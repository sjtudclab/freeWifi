package cn.edu.sjtu.dclab.freewifi.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.service.IAdCollectService;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IPushService;
import cn.edu.sjtu.dclab.freewifi.service.IWIFIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.service.IUserService;
import cn.edu.sjtu.dclab.freewifi.service.impl.AdServiceImpl;
import cn.edu.sjtu.dclab.freewifi.util.Constants;
import cn.edu.sjtu.dclab.freewifi.util.DateUtils;

@Controller
@RequestMapping(value = "user")
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestParam(value = "device_id") String deviceId,
                                        @RequestParam(value = "tel") String tel,
                                        @RequestParam(value = "education") int education,
                                        @RequestParam(value = "gender") int gender,
                                        @RequestParam(value = "birthdate") String birthdate,
                                        @RequestParam(value = "income") int income) {
        Date date = DateUtils.parseDate(birthdate, "yyyy-MM-dd");
        User user = new User(deviceId, Gender.get(gender), tel, date,
                Education.get(education), IncomeType.get(income));
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
			@RequestParam(value = "ad_id") String adId) {
		boolean result = true;
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
    
    @RequestMapping(value = "/collect", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> collect(
            @RequestParam(value = "device_id") String deviceId,
            @RequestParam(value = "ad_id") Long adId) {
    	User user = null;
    	if (deviceId != null && !deviceId.equals("")) {
    		user = userService.getUserByDeviceId(deviceId);
		}
    	Ad ad = null;
    	if (adId != null) {
    		ad = adService.getAd(adId);
		}
    	boolean result = false;
    	if (user != null && ad != null) {
    		result = adCollectService.addAdCollect(user, ad);
		}
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put(Constants.CODE, 0);
        } else {
            map.put(Constants.CODE, -1);
        }
        return map;
    }
}
