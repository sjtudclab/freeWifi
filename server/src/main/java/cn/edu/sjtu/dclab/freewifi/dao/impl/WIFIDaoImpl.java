package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IWIFIDao;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;

@Repository("wifiDao")
public class WIFIDaoImpl implements IWIFIDao {
	@Resource(name = "baseDao")
	private IBaseDao<WIFI> baseDao;
	@Override
	public WIFI getWifiByMerchant(Merchant merchant) {
		List<WIFI> queryResult = baseDao.queryByProperty(WIFI.class, "merchant.id", ""+merchant.getId());
		if (queryResult != null) {
			return queryResult.get(0);
		}
		return null;
	}

	@Override
	public List<WIFI> getWifiListByLocation(double longitude, double latitude) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean addWifi(WIFI wifi) {
		baseDao.save(wifi);
		return true;
	}

}
