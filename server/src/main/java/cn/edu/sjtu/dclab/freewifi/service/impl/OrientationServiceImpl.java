package cn.edu.sjtu.dclab.freewifi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IOrientationDao;
import cn.edu.sjtu.dclab.freewifi.domain.Orientation;
import cn.edu.sjtu.dclab.freewifi.service.IOrientationService;
@Service("orientationService")
@Transactional
public class OrientationServiceImpl implements IOrientationService {
	@Resource(name = "orientationDao")
    private IOrientationDao dao;
	@Override
	public boolean addOrientation(Orientation orientation) {
		return dao.addOrientation(orientation);
	}

}
