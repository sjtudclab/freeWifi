package cn.edu.sjtu.dclab.freewifi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Orientation;

@Controller
@RequestMapping(value = "ad")
public class AdController {
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addAdView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addAdSubmit(
			@RequestParam(value = "merchant_id", required = false) String merchantId,
			Ad ad, Orientation orientation) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
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
