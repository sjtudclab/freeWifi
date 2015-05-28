package cn.edu.sjtu.dclab.freewifi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user")
public class UserController {
	@RequestMapping(value = "/register",method=  RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam(value = "device_id") String deviceId,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "education") int education,
			@RequestParam(value = "gender") int gender,
			@RequestParam(value = "birthdate") String birthdate,
			@RequestParam(value = "income") int income) {
		return null;
	}

	@RequestMapping(value = "/notification",method=  RequestMethod.POST)
	@ResponseBody
	public String notification(
			@RequestParam(value = "device_id") String deviceId,
			@RequestParam(value = "wifi_id") String wifiId) {
		return null;
	}
}
