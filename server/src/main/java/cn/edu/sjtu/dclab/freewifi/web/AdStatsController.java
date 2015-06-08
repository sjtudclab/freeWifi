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
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IAdStatsService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
@RequestMapping(value = "/ad/stats")
public class AdStatsController {
	
	@Autowired
	private IAdStatsService adStatsService;
	
	@Autowired
	private IAdService adService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(@PathVariable(value = "id") long adId,String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		Ad ad = adService.getAd(adId);
		if (ad == null) {
			map.put(Constants.CODE, -1);
			map.put(Constants.ERROR_MSG,"get ad error.");
			return map;
		}
		String[] types = null;
		if (type.equals("Age")) {
			types = AgeType.getTypes();
		}else if (type.equals("Income")) {
			types = IncomeType.getTypes();
		}else if (type.equals("Education")) {
			types = Education.getTypes();
		}else if (type.equals("Sex")) {
			types = Gender.getTypes();
		}else if (type.equals("Child")) {
			types = BabyState.getTypes();
		}else if (type.equals("Marry")) {
			types = EngageState.getTypes();
		}else if (type.equals("Career")) {
			types = Job.getTypes();
		}
		List<AdStats> adStats = adStatsService.getAdStatsByAd(ad,types); 
		map.put(Constants.DATA, adStats);
		map.put(Constants.CODE, 0);
		return map;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String chartView(@RequestParam(value = "id") long adId) {
		return "chart";
	}
}
