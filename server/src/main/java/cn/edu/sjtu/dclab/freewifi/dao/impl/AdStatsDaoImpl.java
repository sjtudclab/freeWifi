package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IAdStatsDao;
import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;

@Repository("adStatsDao")
public class AdStatsDaoImpl implements IAdStatsDao {
	@Resource(name = "baseDao")
	private IBaseDao<AdStats> baseDao;
	
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
	public boolean addAdStats(AdStats adStats) {
		baseDao.save(adStats);
		return true;
	}

	@Override
	public List<AdStats> getAdStats(Ad ad,String[] types) {
		Criteria criteria = getSession().createCriteria(AdStats.class);
		criteria.add(Restrictions.eq("ad.id", ad.getId()));
		criteria.add(Restrictions.in("orientation", types));
		List<AdStats> result = criteria.list();		
		return result;
	}
	
	@Override
	public List<AdStats> getAdStats(Ad ad) {
		Criteria criteria = getSession().createCriteria(AdStats.class);
		criteria.add(Restrictions.eq("ad.id", ad.getId()));
		List<AdStats> result = criteria.list();		
		return result;
	}
	
	

	@Override
	public boolean updateAdStats(AdStats adStats) {
		baseDao.update(adStats);
		return true;
	}

}
