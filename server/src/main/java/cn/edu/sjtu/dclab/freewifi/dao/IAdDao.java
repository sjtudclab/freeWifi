package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;

public interface IAdDao {
public boolean addAd(Ad ad);
public List<Ad> getAdByMerchant(Merchant merchant);
public boolean updateAd(Ad ad);
public Ad getAdById(long id);
}
