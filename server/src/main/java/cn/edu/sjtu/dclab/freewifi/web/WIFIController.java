package cn.edu.sjtu.dclab.freewifi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import cn.edu.sjtu.dclab.freewifi.service.IWIFIService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
@RequestMapping(value = "wifi")
public class WIFIController {
	@Autowired
	private IWIFIService wifiService;
	
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWIFIListByLocation(@RequestParam(value = "longitude") Double longitude,@RequestParam(value = "latitude") Double latitude) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (longitude != null && latitude != null) {
			List<WIFI> wifis= wifiService.getWifiListByLocation(longitude, latitude);
			map.put(Constants.CODE, 0);
			map.put(Constants.SIZE, wifis.size());
			map.put(Constants.DATA, wifis);
		}else {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG, "location args error");
		}
		return map;
	}
}
