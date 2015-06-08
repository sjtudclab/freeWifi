package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdStatsService {
	
	public boolean addClick(User user, Ad ad);
	
	public boolean addImpression(User user, Ad ad);
	
	public boolean  addPush(User user,Ad ad);
	
	public List<AdStats> getAdStatsByAd(Ad ad);
	
	public List<AdStats> getAdStatsByAd(Ad ad,String[] types);
}
