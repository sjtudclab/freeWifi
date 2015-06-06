package cn.edu.sjtu.dclab.freewifi.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IAdService;
import cn.edu.sjtu.dclab.freewifi.service.IPushService;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by Yang on 2015/5/31.
 */

@Service("pushService")
@Transactional
public class PushServiceImpl implements IPushService {
	@Autowired
	private IAdService adService;
	// APPKEY and MASTERSECRET are copied from the app on Jpush.com
	private final String APPKEY = "1c0bdf68225216fa3dee6be9";
	private final String MASTERSECRET = "e3ba0726e6afb6752855c2ef";
	public Logger LOG = Logger.getLogger("PushService");

	@Override
	@Async
	public void pushNotificationAdByMerchantAndUser(Merchant merchant, User user) {
		// List<Ad> adList = adService.getAdListByMerchantAndUser(merchant, user);

		// TODO sth to do here
		// if (0 == adList.size())
		// return;
		//
		// Ad ad = adList.get(0);
		// ��Щ�����ʱ���ڲ���
		String alias = null;
		String title = "Title";
		String msg = "This a msg";
		String url = "URL";
		String urlLink = "www.baidu.com";
		Map<String, String> extra = new HashMap<String, String>();
		extra.put(url, urlLink);
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.android())
				// .setAudience(Audience.alias(alias))
				.setAudience(Audience.all())
				// just for test
				.setNotification(Notification.android(msg, title, extra))
				.build();
		JPushClient jpushClient = new JPushClient(MASTERSECRET, APPKEY, 3);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			// Connection error, should retry later
			LOG.info("Connection error, should retry later" + e);
			e.printStackTrace();
		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			LOG.info("Should review the error, and fix the request" + e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			e.printStackTrace();
		}
	}

	@Override
	@Async
	public void pushMessageAdByMerchantAndUser(Merchant merchant, User user) {
		
	}
}
