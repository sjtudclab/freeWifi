package cn.edu.sjtu.dclab.freewifi.service.impl;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
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
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Yang on 2015/5/31.
 */
public class PushServiceImpl implements IPushService {
    private final String APPKEY = "1c0bdf68225216fa3dee6be9";
    private final String MASTERSECRET = "e3ba0726e6afb6752855c2ef";
    private IAdService adService;
    public Logger LOG = Logger.getLogger("PushService");

    @Override
    @Async
    public void pushAdByMerchanAndUser(Merchant merchant, User user) {
        List<Ad> adList = adService.getAdListByMerchantAndUser(merchant, user);
        //TODO ad to json
        if (0 == adList.size())
            return;

        Ad ad = adList.get(0);
        String alias = null;
        String title = null;
        String msg = null;
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.android(msg, title, null))
                .build();
        JPushClient jpushClient = new JPushClient(MASTERSECRET,APPKEY, 3);
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
}
