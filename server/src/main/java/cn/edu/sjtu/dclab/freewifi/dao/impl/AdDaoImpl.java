package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IAdDao;
import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.AdState;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
@Repository("adDao")
public class AdDaoImpl implements IAdDao {
	@Resource(name = "baseDao")
	private IBaseDao<Ad> baseDao;
	
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
	public boolean addAd(Ad ad) {
		baseDao.save(ad);
		return true;
	}

	@Override
	public List<Ad> getAdByMerchant(Merchant merchant) {
		Criteria criteria = getSession().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("merchant.id", ""+merchant.getId()));
		criteria.add(Restrictions.ne("state", AdState.DELETE));
		return criteria.list();
	}

	@Override
	public boolean updateAd(Ad ad) {
		baseDao.update(ad);
		return true;
	}

	@Override
	public Ad getAdById(long id) {
		return baseDao.queryById(Ad.class, id);
	}
	@Override
	public List<Ad> getLaunchingAdByMerchantAndUser(Merchant merchant, User user) {
		Criteria criteria = getSession().createCriteria(Ad.class);
		criteria.add(Restrictions.eq("merchant.id", ""+merchant.getId()));
		criteria.add(Restrictions.eq("state", AdState.LAUNCHING));
		//添加四个定向方案
		criteria.add(Restrictions.like("orientation.sex", ""+user.getSex().getValue(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("orientation.income", ""+user.getIncome().getValue(), MatchMode.ANYWHERE));
		criteria.add(Restrictions.like("orientation.education", ""+user.getEducation().getValue(), MatchMode.ANYWHERE));	
		criteria.add(Restrictions.like("orientation.age", ""+AgeType.getByBirthDate(user.getBirthdate()).getValue(), MatchMode.ANYWHERE));
		//时间配置
		criteria.add(Restrictions.le("startDate", new Date()));
		criteria.add(Restrictions.ge("endDate", new Date()));
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int now = hour*3600 + minute*60 +second;
		criteria.add(Restrictions.le("startHour", now));	
		criteria.add(Restrictions.ge("endHour", now));
		return criteria.list();
	}

}
