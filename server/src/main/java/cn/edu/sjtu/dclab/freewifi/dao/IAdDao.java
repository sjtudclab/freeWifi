package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdDao {
	public boolean addAd(Ad ad);
	/**
	 * 获取所有的广告
	 * @param merchant
	 * @return
	 */
	public List<Ad> getAdByMerchant(Merchant merchant);
	public boolean updateAd(Ad ad);
	public Ad getAdById(long id);
	public List<Ad> getLaunchingAdByMerchantAndUser(Merchant merchant,User user);
}