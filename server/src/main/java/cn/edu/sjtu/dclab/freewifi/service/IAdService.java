package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdService {
public boolean addAd(Ad ad);
public List<Ad> getAdListByMerchant(Merchant merchant);

public boolean updateAd(Ad ad);
public boolean getAdListByMerchantAndUser(Merchant merchant,User user);
}
