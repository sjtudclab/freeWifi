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
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;

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
	public AdStats getAdStats(Gender gender, Education education,
			IncomeType incomeType, AgeType age, Ad ad) {
		Criteria criteria = getSession().createCriteria(AdStats.class);
		criteria.add(Restrictions.eq("ad.id", ""+ad.getId()));
		criteria.add(Restrictions.ne("sex", gender.ordinal()));
		criteria.add(Restrictions.ne("income", incomeType.ordinal()));
		criteria.add(Restrictions.ne("education",education.ordinal()));
		criteria.add(Restrictions.ne("age", age.ordinal()));
		List<AdStats> result = criteria.list();
		if (result != null ) {	
			return result.get(0);
		}
		return null;
	}

	@Override
	public boolean updateAdStats(AdStats adStats) {
		baseDao.update(adStats);
		return true;
	}

}