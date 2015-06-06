package cn.edu.sjtu.dclab.freewifi.tool;

import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ClassParse {

	private Gson gson;

	public ClassParse() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}



    public String obj2String(Object obj){
        return gson.toJson(obj);
    }

	public List<WIFI> string2WifiList(String content) {
		try {
			Type type = new TypeToken<List<WIFI>>() {
			}.getType();
			if (content != null) {
				List<WIFI> record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public Map<String, Object> string2Map(String content) {
		try {
			Type type = new TypeToken<Map<String, Object>>() {
			}.getType();
			if (content != null) {
				Map<String, Object> record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
}
