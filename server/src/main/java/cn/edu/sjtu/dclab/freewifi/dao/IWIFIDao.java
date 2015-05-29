package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;

public interface IWIFIDao {
	public WIFI getWifiByMerchant(Merchant merchant);

	public List<WIFI> getWifiListByLocation(double longitude, double latitude);
	
	public boolean addWifi(WIFI wifi);
}
