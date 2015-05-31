package cn.edu.sjtu.dclab.freewifi.service;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;

public interface IMerchantService {
	public Merchant login(String loginname, String password);
	
	public boolean addMerchant(Merchant merchant);
	
	public boolean checkLoginName(String loginName);
	
	public boolean updateMerchant(Merchant merchant);
	
	public boolean deleteMerchant(Merchant merchant);
	
	public Merchant getMerchantById(long id);
}
