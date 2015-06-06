package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IAdDao;
import cn.edu.sjtu.dclab.freewifi.dao.IAdStatsDao;
import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.enums.AdState;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IAdStatsService;
@Service("adService")
@Transactional
public class AdServiceImpl implements IAdService {
	
	@Resource(name = "adDao")
    private IAdDao dao;
	@Resource(name = "adStatsDao")
    private IAdStatsDao adStatsDao;
	@Resource(name = "adStatsService")
    private IAdStatsService adStatsService;
	
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
	public Ad getAdByMerchantAndUser(Merchant merchant, User user) {
		List<Ad> ads = dao.getLaunchingAdByMerchantAndUser(merchant, user);
		if (ads != null) {
			int index = 0;
			double max = 0.0d;
			for (int i = 0; i < ads.size(); i++) {
				AdStats adStats = adStatsDao.getAdStats(user.getSex(), user.getEducation(), user.getIncome(), AgeType.getByBirthDate(user.getBirthdate()),
						merchant.getBusiness(), user.getEngage(), user.getJob(), user.getBaby(), ads.get(i));
				if (adStats !=  null && adStats.getImpression() != 0) {
					double clickRate = adStats.getClick() / adStats.getImpression();
					if (clickRate > max ) {
						max = clickRate;
						index = i;
					}
				}
			}
			
			Ad chosen = ads.get(index);
			adStatsService.addImpression(user, chosen);
			return chosen;
		}
		ads = getAdListByMerchant(merchant);
		if (ads != null) {
			Ad chosen = ads.get(0);
			adStatsService.addImpression(user, chosen);
			return chosen;
		}
		return null;
	}

	@Override
	public boolean deleteAd(Ad ad) {
		ad.setState(AdState.DELETE);
		return dao.updateAd(ad);
	}

	@Override
	public boolean lauchAd(Ad ad) {
		ad.setState(AdState.LAUNCHING);
		return dao.updateAd(ad);
	}

	@Override
	public boolean unlauchAd(Ad ad) {
		ad.setState(AdState.READY);
		return dao.updateAd(ad);
	}

	@Override
	public Ad getAd(long id) {
		return dao.getAdById(id);
	}

}
