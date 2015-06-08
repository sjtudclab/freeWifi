package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;

public interface IAdStatsDao {
	
	public boolean addAdStats(AdStats adStats);

	public List<AdStats> getAdStats(Ad ad,String[] types);

	public List<AdStats> getAdStats(Ad ad);
	
	public boolean updateAdStats(AdStats adStats);
}
