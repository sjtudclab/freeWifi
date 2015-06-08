package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdStatsDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.service.IAdStatsService;
@Service("adStatsService")
@Transactional
public class AdStatsServiceImpl implements IAdStatsService {
	
	@Resource(name = "adStatsDao")
    private IAdStatsDao dao;
	
	@Override
	public boolean addClick(User user, Ad ad) {
		String[] types = new String[7];
		
		types[0] = user.getSex().getName();
		types[1] = AgeType.getByBirthDate(user.getBirthdate()).getName();
		types[2] = user.getIncome().getName();
		types[3] = user.getEducation().getName();
		types[4] = user.getEngage().getName();
		types[5] = user.getJob().getName();
		types[6] = user.getBaby().getName();
		for (int i =0;i< types.length;i++) {
			List<AdStats> result = dao.getAdStats(ad, new String[]{types[i]});
			AdStats one = result != null ? result.get(0): null;
			if(one == null){
				one = new AdStats(ad,types[i]);
				one.setClick(1);
				dao.addAdStats(one);
			}else {
				one.setClick(one.getClick()+1);
				dao.updateAdStats(one);
			}
		}
		return true;
	}
	
	@Override
	public boolean addImpression(User user, Ad ad) {
		String[] types = new String[7];
		
		types[0] = user.getSex().getName();
		types[1] = AgeType.getByBirthDate(user.getBirthdate()).getName();
		types[2] = user.getIncome().getName();
		types[3] = user.getEducation().getName();
		types[4] = user.getEngage().getName();
		types[5] = user.getJob().getName();
		types[6] = user.getBaby().getName();
		for (int i =0;i< types.length;i++) {
			List<AdStats> result = dao.getAdStats(ad, new String[]{types[i]});
			AdStats one = (result != null)&&(result.size()>0) ? result.get(0): null;
			if(one == null){
				one = new AdStats(ad,types[i]);
				one.setImpression(1);
				dao.addAdStats(one);
			}else {
				one.setImpression(one.getImpression()+1);
				dao.updateAdStats(one);
			}
		}
		return true;
	}

	@Override
	public boolean addPush(User user, Ad ad) {
		String[] types = new String[7];
		
		types[0] = user.getSex().getName();
		types[1] = AgeType.getByBirthDate(user.getBirthdate()).getName();
		types[2] = user.getIncome().getName();
		types[3] = user.getEducation().getName();
		types[4] = user.getEngage().getName();
		types[5] = user.getJob().getName();
		types[6] = user.getBaby().getName();
		for (int i =0;i< types.length;i++) {
			List<AdStats> result = dao.getAdStats(ad, new String[]{types[i]});
			AdStats one = result != null ? result.get(0): null;
			if(one == null){
				one = new AdStats(ad,types[i]);
				one.setPush(1);
				dao.addAdStats(one);
			}else {
				one.setPush(one.getPush()+1);
				dao.updateAdStats(one);
			}
		}
		return true;
	}

	@Override
	public List<AdStats> getAdStatsByAd(Ad ad) {
		return dao.getAdStats(ad);
	}

	@Override
	public List<AdStats> getAdStatsByAd(Ad ad, String[] types) {
		return dao.getAdStats(ad, types);
	}
	
}
