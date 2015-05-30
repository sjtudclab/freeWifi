package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IWIFIDao;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import cn.edu.sjtu.dclab.freewifi.util.Constants;
import cn.edu.sjtu.dclab.freewifi.util.DistanceUtils;

@Repository("wifiDao")
public class WIFIDaoImpl implements IWIFIDao {
	@Resource(name = "baseDao")
	private IBaseDao<WIFI> baseDao;
	
	@Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
        return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public WIFI getWifiByMerchant(Merchant merchant) {
		List<WIFI> queryResult = baseDao.queryByProperty(WIFI.class, "merchant.id", ""+merchant.getId());
		if (queryResult != null) {
			return queryResult.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WIFI> getWifiListByLocation(double longitude, double latitude) {
		Criteria criteria = getSession().createCriteria(WIFI.class);
		Criteria distanceCriteria = criteria.createCriteria("distance");
		double longi = DistanceUtils.getLongitude(longitude, latitude, Constants.WIFI_SEARCH_DISTANCE);
		double lati = DistanceUtils.getLatitude(longitude, latitude, Constants.WIFI_SEARCH_DISTANCE);
		double longiDelta = Math.abs(longi - longitude);
		double latiDelta = Math.abs(lati - latitude);
		distanceCriteria.add(Restrictions.lt("longitude", longitude + longiDelta));
		distanceCriteria.add(Restrictions.gt("longitude", longitude - longiDelta));
		distanceCriteria.add(Restrictions.lt("latitude", latitude+latiDelta));
		distanceCriteria.add(Restrictions.gt("latitude", latitude-latiDelta));
		return distanceCriteria.list();
	}
	@Override
	public boolean addWifi(WIFI wifi) {
		baseDao.save(wifi);
		return true;
	}

}
