package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yang on 2015/6/7.
 */
public class UserService   extends BasicWebService {

    private String url;

    private Context _context;

    public UserService(Context _context, String url) {
        super();
        this._context = _context;
        this.url = url;
    }

    public String notification(String deviceId, String wifiId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(SharedDataTool.IMEI, deviceId);
        map.put(SharedDataTool.WIFIID, wifiId);
        String result = sendGetRequest(url, map);
        return result;
    }
}