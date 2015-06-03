package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdCollectDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdCollectService;
@Service("adCollectService")
@Transactional
public class AdCollectServiceImpl implements IAdCollectService{

	@Resource(name = "adDao")
	private IAdCollectDao adDao;
	
	@Override
	public boolean addAdCollect(User user, Ad ad) {
		AdCollect collect =  new AdCollect(ad, user, new Date());
		return adDao.addAdCollect(collect);
	}

	@Override
	public List<AdCollect> getAdCollectsByUser(User user) {
		return adDao.getAdCollectsByUser(user);
	}

}
