package cn.edu.sjtu.dclab.freewifi.dao;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;

public interface IMerchantDao {
	public Merchant getMerchantByLoginName(String loginname);
}
