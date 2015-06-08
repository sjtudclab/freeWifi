package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdCollectDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdCollect;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdCollectService;
@Service("adCollectService")
@Transactional
public class AdCollectServiceImpl implements IAdCollectService{

	@Resource(name = "adCollectDao")
	private IAdCollectDao adDao;
	
	@Override
	public boolean addAdCollect(User user, Ad ad) {
		AdCollect collect =  new AdCollect(ad, user, new Date());
		return adDao.addAdCollect(collect);
	}

	@Override
	public List<Ad> getAdsByUser(User user) {
		List<AdCollect> res = adDao.getAdCollectsByUser(user);
		List<Ad> resAds = new ArrayList<Ad>();
		if (res!= null) {
			for (AdCollect adCollect : res) {
				Ad ad = adCollect.getAd();
				
				Merchant merchant = new Merchant(null, null, ad.getMerchant().getName(), ad.getMerchant().getAddress(), 
						ad.getMerchant().getTel(), null, null);
				merchant.setId(ad.getMerchant().getId());
				Ad one = new Ad(merchant, null, null, null, null, 
						null, 0, ad.getName(), 0, null, null);
				one.setId(ad.getId());
				resAds.add(one);
			}
		}
		return resAds;
	}

}
