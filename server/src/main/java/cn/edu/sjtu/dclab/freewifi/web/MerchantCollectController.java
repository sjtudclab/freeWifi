package cn.edu.sjtu.dclab.freewifi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantCollectService;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantService;
import cn.edu.sjtu.dclab.freewifi.service.IUserService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
@RequestMapping(value = "/merchant/collect")
public class MerchantCollectController {
	@Autowired
    private IUserService userService;
    @Autowired
    private IAdService adService;
    @Autowired
    private IMerchantCollectService mCollectService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> collect(
            @RequestParam(value = "device_id") String deviceId,
            @RequestParam(value = "ad_id") Long mId) {
    	User user = null;
    	if (deviceId != null && !deviceId.equals("")) {
    		user = userService.getUserByDeviceId(deviceId);
		}
    	Ad ad = null;
    	if (mId != null) {
    		ad = adService.getAd(mId);
		}
    	boolean result = false;
    	if (user != null && ad != null) {
    		result = mCollectService.addMerchantCollect(user, ad.getMerchant());
		}
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put(Constants.CODE, 0);
        } else {
            map.put(Constants.CODE, -1);
        }
        return map;
    }
	
	
	@RequestMapping(value = "/all/{device_id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAdCollects(@PathVariable(value="device_id") String deviceId ) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = null;
    	if (deviceId != null && !deviceId.equals("")) {
    		user = userService.getUserByDeviceId(deviceId);
		}
    	if (user != null) {
    		List<Merchant> result = mCollectService.getMerchantsByUser(user);
    		map.put(Constants.CODE, 0);
    		map.put(Constants.DATA, result);
		}else {
			map.put(Constants.CODE, -1);
		}
        return map;
    }
}
