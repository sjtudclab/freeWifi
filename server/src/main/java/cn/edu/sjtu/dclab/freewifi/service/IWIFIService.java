package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.WIFI;


public interface IWIFIService {
public List<WIFI> getWifiListByLocation(double longitude, double latitude);
public boolean addWIFI(WIFI wifi);
public WIFI getWifiById(long id);
}
