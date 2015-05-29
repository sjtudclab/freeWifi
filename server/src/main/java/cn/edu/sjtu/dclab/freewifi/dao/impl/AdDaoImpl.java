package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IAdDao;
import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
@Repository("adDao")
public class AdDaoImpl implements IAdDao {
	@Resource(name = "baseDao")
	private IBaseDao<Ad> baseDao;
	@Override
	public boolean addAd(Ad ad) {
		baseDao.save(ad);
		return true;
	}

	@Override
	public List<Ad> getAdByMerchant(Merchant merchant) {
		return baseDao.queryByProperty(Ad.class, "merchant.id", ""+merchant.getId());
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

}
