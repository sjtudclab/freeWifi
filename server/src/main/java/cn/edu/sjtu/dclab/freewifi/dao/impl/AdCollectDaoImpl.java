package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IAdCollectDao;
import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;
@Repository("adCollectDao")
public class AdCollectDaoImpl implements IAdCollectDao {
	
	@Resource(name = "baseDao")
	private IBaseDao<AdCollect> baseDao;
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
	public boolean addAdCollect(AdCollect adCollect) {
		baseDao.save(adCollect);
		return true;
	}

	@Override
	public List<AdCollect> getAdCollectsByUser(User user) {
		Criteria criteria = getSession().createCriteria(AdCollect.class);
		criteria.add(Restrictions.eq("user.id", user.getId()));
		return criteria.list();
	}

}
