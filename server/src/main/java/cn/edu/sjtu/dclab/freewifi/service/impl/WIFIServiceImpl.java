package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IWIFIDao;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import cn.edu.sjtu.dclab.freewifi.service.IWIFIService;
@Service("wifiService")
@Transactional
public class WIFIServiceImpl implements IWIFIService {
	@Resource(name = "wifiDao")
    private IWIFIDao dao;
	@Override
	public List<WIFI> getWifiListByLocation(double longitude, double latitude) {
		return dao.getWifiListByLocation(longitude, latitude);
	}

	@Override
	public boolean addWIFI(WIFI wifi) {
		return dao.addWifi(wifi);
	}

	@Override
	public WIFI getWifiById(long id) {
		return dao.getWifiById(id);
	}

}
