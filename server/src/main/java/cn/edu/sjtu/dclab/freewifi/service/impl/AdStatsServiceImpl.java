package cn.edu.sjtu.dclab.freewifi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdStatsDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.BusinessType;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;
import cn.edu.sjtu.dclab.freewifi.service.IAdStatsService;
@Service("adStatsService")
@Transactional
public class AdStatsServiceImpl implements IAdStatsService {
	
	@Resource(name = "adStatsDao")
    private IAdStatsDao dao;
	
	@Override
	public boolean addClick(User user, Ad ad) {
		Gender sex = user.getSex();
		AgeType age = AgeType.getByBirthDate(user.getBirthdate());
		IncomeType income = user.getIncome();
		Education education = user.getEducation();
		BusinessType business = ad.getMerchant().getBusiness();
		EngageState engage = user.getEngage();
		Job job = user.getJob();
		BabyState baby = user.getBaby();
		
		
		AdStats adStats = dao.getAdStats(sex, education, income, age, business, engage, job, baby, ad);
		if(adStats == null){
			adStats = new AdStats(ad, sex, income, education, age, business, engage, job, baby);
			adStats.setClick(1);
			dao.addAdStats(adStats);
		}else {
			adStats.setClick(adStats.getClick()+1);
			dao.updateAdStats(adStats);
		}
		return true;
	}
	
	@Override
	public boolean addImpression(User user, Ad ad) {
		Gender sex = user.getSex();
		AgeType age = AgeType.getByBirthDate(user.getBirthdate());
		IncomeType income = user.getIncome();
		Education education = user.getEducation();
		BusinessType business = ad.getMerchant().getBusiness();
		EngageState engage = user.getEngage();
		Job job = user.getJob();
		BabyState baby = user.getBaby();
		
		AdStats adStats = dao.getAdStats(sex, education, income, age, business, engage, job, baby, ad);
		if(adStats == null){
			adStats = new AdStats(ad, sex, income, education, age, business, engage, job, baby);
			adStats.setImpression(1);
			dao.addAdStats(adStats);
		}else {
			adStats.setImpression(adStats.getImpression()+1);
			dao.updateAdStats(adStats);
		}
		return true;
	}
	
	

}
