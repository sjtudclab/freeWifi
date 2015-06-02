package cn.edu.sjtu.dclab.freewifi.service;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;

/**
 * Created by Yang on 2015/5/31.
 */
public interface IPushService {
    public void pushNotificationAdByMerchantAndUser(Merchant merchant, User user);

	public void pushMessageAdByMerchantAndUser(Merchant merchant, User user);
}
