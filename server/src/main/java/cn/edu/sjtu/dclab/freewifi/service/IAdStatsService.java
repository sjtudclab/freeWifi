package cn.edu.sjtu.dclab.freewifi.service;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdStatsService {
	
	public boolean addClick(User user, Ad ad);
	
	public boolean addImpression(User user, Ad ad);
}
