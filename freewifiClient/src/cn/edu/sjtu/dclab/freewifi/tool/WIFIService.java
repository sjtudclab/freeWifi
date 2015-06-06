package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yang on 2015/6/6.
 */
public class WIFIService  extends BasicWebService {

    private String url;

    private Context _context;

    public WIFIService(Context _context, String url) {
        super();
        this._context = _context;
        this.url = url;
    }

    public String getWifiList(String lon, String lati) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(SharedDataTool.LONGITUDE, lon);
        map.put(SharedDataTool.LATITUDE, lati);
        String result = sendGetRequest(url, map);
        return result;
    }
}
