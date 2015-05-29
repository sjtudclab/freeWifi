package cn.edu.sjtu.dclab.freewifi.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IOrientation;
import cn.edu.sjtu.dclab.freewifi.domain.Orientation;
@Repository("orientationDao")
public class OrientationImpl implements IOrientation {
	@Resource(name = "baseDao")
	private IBaseDao<Orientation> baseDao;
	@Override
	public boolean addOrientation(Orientation orientation) {
		baseDao.save(orientation);
		return false;
	}

}
