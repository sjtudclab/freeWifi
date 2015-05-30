package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
@Service("adService")
@Transactional
public class AdServiceImpl implements IAdService {
	@Resource(name = "adDao")
    private IAdDao dao;
	@Override
	public boolean addAd(Ad ad) {
		return dao.addAd(ad);
	}

	@Override
	public List<Ad> getAdListByMerchant(Merchant merchant) {
		return dao.getAdByMerchant(merchant);
	}

	@Override
	public boolean updateAd(Ad ad) {
		return dao.updateAd(ad);
	}

	@Override
	public boolean getAdListByMerchantAndUser(Merchant merchant, User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
