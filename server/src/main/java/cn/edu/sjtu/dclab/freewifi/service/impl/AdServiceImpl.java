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
		String[] types = new String[7];
		types[0] = user.getSex().getName();
		types[1] = AgeType.getByBirthDate(user.getBirthdate()).getName();
		types[2] = user.getIncome().getName();
		types[3] = user.getEducation().getName();
		types[4] = user.getEngage().getName();
		types[5] = user.getJob().getName();
		types[6] = user.getBaby().getName();

		if (ads != null && ads.size() > 0) {
			int index = 0;
			double max = 0.0d;
			for (int i = 0; i < ads.size(); i++) {
				List<AdStats> adStats = adStatsDao
						.getAdStats(ads.get(i), types);
				double clickRate = 0.0d;
				if (adStats != null) {
					int sumPush = 0;
					int sumClcik = 0;
					for (AdStats one : adStats) {
						sumClcik += one.getClick();
						sumPush += one.getPush();
					}
					if (sumPush != 0) {
						clickRate = sumClcik / sumPush;
					}
				}
				if (clickRate > max) {
					max = clickRate;
					index = i;
				}
			}

			Ad chosen = ads.get(index);
			adStatsService.addImpression(user, chosen);
			return chosen;
		}
		ads = getAdListByMerchant(merchant);
		if (ads != null && ads.size() >0) {
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
