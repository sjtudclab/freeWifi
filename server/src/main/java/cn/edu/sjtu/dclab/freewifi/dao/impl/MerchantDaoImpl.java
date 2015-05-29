package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IMerchantDao;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;

@Repository("merchantDao")
public class MerchantDaoImpl implements IMerchantDao{
	@Resource(name = "baseDao")
	private IBaseDao<Merchant> baseDao;
	
	@Override
	public Merchant getMerchantByLoginName(String loginname) {
		List<Merchant> merchants = baseDao.queryByProperty(Merchant.class, "loginname", loginname);
		if (merchants.size() == 0) {
			return null;
		}
		return merchants.get(0);
	}
	@Override
	public boolean addMerchant(Merchant merchant) {
		baseDao.save(merchant);
		return true;
	}
	@Override
	public boolean updateMerchant(Merchant merchant) {
		baseDao.update(merchant);
		return true;
	}

}
