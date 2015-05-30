package cn.edu.sjtu.dclab.freewifi.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    public static int get(String expr, String key, int defaultValue) {
        try {
            JSONObject obj = (JSONObject) JSONValue.parse(expr);
            return get(obj, key, defaultValue);
        } catch (Exception e) {
            LOGGER.warn("fail to parse expr:{}", expr);
            return defaultValue;
        }
    }

	public static int get(JSONObject obj, String key, int defaultValue){
		if(null == obj){
            LOGGER.warn("json object is null");
			return defaultValue;
		}
		Object value = obj.get(key);
		if(null == value){
            LOGGER.warn("fail to find value for key:{}", key);
			return defaultValue;
		}
		try{
            return Integer.parseInt(value.toString());
		}catch (Exception e){
            LOGGER.warn("fail to convert value:{} to int", value);
			return defaultValue;
		}
	}


    public static String get(String expr, String key, String defaultValue) {

        try {
            JSONObject obj = (JSONObject) JSONValue.parse(expr);
            return get(obj, key, defaultValue);
        } catch (Exception e) {
            LOGGER.warn("fail to parse expr:{}", expr);
            return defaultValue;
        }
    }
	public static String get(JSONObject obj, String key, String defaultValue){
		if(null == obj){
            LOGGER.warn("json object is null");
			return defaultValue;
		}
		Object value = obj.get(key);
		if(null == value){
            LOGGER.warn("fail to find value for key:{}", key);
			return defaultValue;
		}
		return value.toString();
	}
	

    public static double get(String expr, String key, double defaultValue) {
        try {
            JSONObject obj = (JSONObject) JSONValue.parse(expr);
            return get(obj, key, defaultValue);
        } catch (Exception e) {
            LOGGER.warn("fail to parse expr:{}", expr);
            return defaultValue;
        }
    }
    public static double get(JSONObject obj, String key, double defaultValue) {
        if (null == obj) {
            LOGGER.warn("json object is null");
            return defaultValue;
        }
        Object value = obj.get(key);
        if (null == value) {
            LOGGER.warn("fail to find value for key:{}", key);
            return defaultValue;
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (Exception e) {
            LOGGER.warn("fail to convert value:{} to int", value);
            return defaultValue;
        }
    }

    public static JSONArray get(String expr, String key, JSONArray defaultValue) {
        try {
            JSONObject obj = (JSONObject) JSONValue.parse(expr);
            return get(obj, key, defaultValue);
        } catch (Exception e) {
            LOGGER.warn("fail to parse expr:{}", expr);
            return defaultValue;
        }
    }

    public static JSONArray get(JSONObject obj, String key, JSONArray defaultValue) {
        if (null == obj) {
            LOGGER.warn("json object is null");
            return defaultValue;
        }
        Object value = obj.get(key);
        if (null == value) {
            LOGGER.warn("fail to find value for key:{}", key);
            return defaultValue;
        }
        try {
            return (JSONArray) value;
        } catch (Exception e) {
            LOGGER.warn("fail to convert value:{} to int", value);
            return defaultValue;
        }
    }

}
