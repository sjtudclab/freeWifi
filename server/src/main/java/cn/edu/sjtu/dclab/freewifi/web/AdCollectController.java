package cn.edu.sjtu.dclab.freewifi.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdCollectService;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IUserService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
@RequestMapping(value = "/ad/collect")
public class AdCollectController {
	
	@Autowired
    private IUserService userService;
    @Autowired
    private IAdService adService;
    @Autowired
    private IAdCollectService adCollectService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
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
	
	
	@RequestMapping(value = "/{device_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAdCollects(@PathVariable(value="device_id") String device ) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean result = true;
        if (result) {
            map.put(Constants.CODE, 0);
        } else {
            map.put(Constants.CODE, -1);
        }
        return map;
    }
}
