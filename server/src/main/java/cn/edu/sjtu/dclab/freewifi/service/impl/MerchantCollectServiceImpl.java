package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IMerchantCollectDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.MerchantCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantCollectService;
@Service("merchantCollectService")
@Transactional
public class MerchantCollectServiceImpl implements IMerchantCollectService {
	
	@Resource(name = "merchantCollectDao")
	private IMerchantCollectDao collectDao;
	
	@Override
	public boolean addMerchantCollect(User user, Merchant merchant) {
		MerchantCollect collect =  new MerchantCollect(merchant, user, new Date());
		return collectDao.addMerchantCollect(collect);
	}

	@Override
	public List<Merchant> getMerchantsByUser(User user) {
		List<MerchantCollect> res =   collectDao.getMerchantCollectsByUser(user);
		List<Merchant> merchants = new ArrayList<Merchant>();
		if (res != null) {
			for (MerchantCollect merchantCollect : res) {
				Merchant merchant = merchantCollect.getMerchant();
				merchant.setIcon(null);
				merchants.add(merchant);
			}
		}
		return merchants;
	}

}
