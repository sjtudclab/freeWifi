package cn.edu.sjtu.dclab.freewifi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IMerchantDao;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantService;

@Service("merchantService")
@Transactional
public class MerchantServiceImpl implements IMerchantService {
	
	@Resource(name = "merchantDao")
    private IMerchantDao dao;
	@Override
	public Merchant login(String loginname, String password) {
		Merchant merchant = dao.getMerchantByLoginName(loginname);
		if (merchant.getPassword().equals(password)) {
			return merchant;
		}else {
			return null;
		}
	}
	@Override
	public boolean addMerchant(Merchant merchant) {
		return dao.addMerchant(merchant);
	}
	@Override
	public boolean checkLoginName(String loginName) {
		return dao.getMerchantByLoginName(loginName) == null ? true : false;
	}
	@Override
	public boolean updateMerchant(Merchant merchant) {
		return dao.updateMerchant(merchant);
	}
	@Override
	public boolean deleteMerchant(Merchant merchant) {
		return dao.deleteMerchant(merchant);
	}
	@Override
	public Merchant getMerchantById(long id) {
		return dao.getMerchant(id);
	}

}
