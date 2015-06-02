package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdService {
public boolean addAd(Ad ad);
public List<Ad> getAdListByMerchant(Merchant merchant);

public boolean updateAd(Ad ad);
/**
 * 根据用户的商家信息进行广告推送
 * @param merchant
 * @param user
 * @return
 */
public List<Ad> getAdListByMerchantAndUser(Merchant merchant,User user);

public boolean deleteAd(Ad ad);

public boolean lauchAd(Ad ad);

public boolean unlauchAd(Ad ad);
}