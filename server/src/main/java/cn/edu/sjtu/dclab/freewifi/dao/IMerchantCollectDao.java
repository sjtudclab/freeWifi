package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.MerchantCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IMerchantCollectDao {
	public boolean addMerchantCollect(MerchantCollect collect);
	public List<MerchantCollect> getMerchantCollectsByUser(User user);
}
