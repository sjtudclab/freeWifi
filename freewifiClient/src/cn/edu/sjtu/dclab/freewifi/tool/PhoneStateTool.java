package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.telephony.TelephonyManager;

/**获取手机相关信息
 * 
 * 注意获取IMEI要声明权限：
 * 		<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 * 
 * @author Eugene
 * @data 2015-1-25
 */
public class PhoneStateTool {
	
	public static String GetIMEI(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

}
