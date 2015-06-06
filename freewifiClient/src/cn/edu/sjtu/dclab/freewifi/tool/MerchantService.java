package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;

/**
 * Created by Yang on 2015/6/7.
 */
public class MerchantService extends BasicWebService {

    private String url;

    private Context _context;

    public MerchantService(Context _context, String url) {
        super();
        this._context = _context;
        this.url = url;
    }

    public String getMerchantList(String deviceId) {
        String result = sendGetRequest(url+"/"+deviceId, null);
        return result;
    }
}