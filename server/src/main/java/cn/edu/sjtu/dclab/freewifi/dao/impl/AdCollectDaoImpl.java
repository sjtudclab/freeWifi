package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IAdCollectDao;
import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.domain.AdCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;
@Repository("adCollectDao")
public class AdCollectDaoImpl implements IAdCollectDao {
	@Resource(name = "baseDao")
	private IBaseDao<AdCollect> baseDao;

	@Override
	public boolean addAdCollect(AdCollect adCollect) {
		baseDao.save(adCollect);
		return true;
	}

	@Override
	public List<AdCollect> getAdCollectsByUser(User user) {
		return baseDao.queryByProperty(AdCollect.class, "user.id", ""+user.getId());
	}

}
