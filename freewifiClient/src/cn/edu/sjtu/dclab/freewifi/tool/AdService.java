package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;

/**
 * Created by Yang on 2015/6/7.
 */
public class AdService extends BasicWebService {

    private String url;

    private Context _context;

    public AdService(Context _context, String url) {
        super();
        this._context = _context;
        this.url = url;
    }

    public String getAdList(String deviceId) {
        String result = sendGetRequest(url+"/"+deviceId, null);
        return result;
    }
}
