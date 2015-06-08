package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IMerchantCollectDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.MerchantCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;
@Repository("merchantCollectDao")
public class MerchantCollectDaoImpl implements IMerchantCollectDao {
	@Resource(name = "baseDao")
	private IBaseDao<MerchantCollect> baseDao;
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
	public boolean addMerchantCollect(MerchantCollect collect) {
		baseDao.save(collect);
		return true;
	}

	@Override
	public List<MerchantCollect> getMerchantCollectsByUser(User user) {
		Criteria criteria = getSession().createCriteria(MerchantCollect.class);
		criteria.add(Restrictions.eq("user.id", user.getId()));
		return criteria.list();
	}

}
