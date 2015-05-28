package cn.edu.sjtu.dclab.freewifi.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "wifi")
public class WIFIController {
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWIFIListByLocation(@RequestParam(value = "longitude") Double longitude,@RequestParam(value = "latitude") Double latitude) {
		return null;
	}
}
