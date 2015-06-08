package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IMerchantCollectService {
	public boolean addMerchantCollect(User user, Merchant merchant);
	public List<Merchant> getMerchantsByUser(User user);

}
