package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

/**SharedDataTool
 * Created by Ernest on 2015/5/29.
 */
public class SharedDataTool {
    private final static String TAG = "SharedDataUtil";

    private final static String SHAREDPREFERENCES_FILE = "shareddata";

    public final static String IMEI = "device_id";
    public final static String GENDER = "gender";
    public final static String BIRTHDAY = "birthdate";
    public final static String TEL = "tel";
    public final static String EDUCATION = "education";
    public final static String INCOME = "income";
    public final static String WIFIID = "wifi_id";
    public final static String LONGITUDE = "longitude";
    public final static String LATITUDE = "latitude";
    public final static String ADID = "ad_id";

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private static void initSharedData(Context context){
        mSharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES_FILE, Context.MODE_WORLD_WRITEABLE);
        mEditor = mSharedPreferences.edit();
    }

    public static boolean WriteIMEI(Context context){
        initSharedData(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mEditor.putString(IMEI, telephonyManager.getDeviceId());
        return mEditor.commit();
    }

    public static String GetIMEI(Context context){
        initSharedData(context);
        return mSharedPreferences.getString(IMEI, null);
    }

    public static boolean WriteRegisterInfo(Context context, String gender, String birthday,
                                            String tel, String education, String income){
        initSharedData(context);
        WriteIMEI(context);
        mEditor.putString(GENDER, gender);
        mEditor.putString(BIRTHDAY, birthday);
        mEditor.putString(TEL, tel);
        mEditor.putString(EDUCATION, education);
        mEditor.putString(INCOME, income);
        return mEditor.commit();
    }

    public static int GetGenderIndex(String gender){
        int index = -1;
        switch (gender){
            case "男": index = 0; break;
            case "女": index = 1; break;
            default: break;
        }
        return index;
    }
    public static int GetEducationIndex(String education){
        int index = -1;
        switch (education){
            case "高中以下": index = 0; break;
            case "高中": index = 1; break;
            case "大专": index = 2; break;
            case "本科": index = 3; break;
            case "研究生及以上": index = 4; break;
            default: break;
        }
        return index;
    }
    public static int GetIncomeIndex(String income){
        int index = -1;
        switch (income){
            case "3000元以下": index = 0; break;
            case "3000-7000元": index = 1; break;
            case "7000-10000元": index = 2; break;
            case "10000元以上": index = 3; break;
            default: break;
        }
        return index;
    }

//    public static List<NameValuePair> GetRegisterInfoNVPairList(Context context) {
//        initSharedData(context);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(IMEI, mSharedPreferences.getString(IMEI, "N/A")));
//        params.add(new BasicNameValuePair(GENDER, mSharedPreferences.getString(GENDER, "N/A")));
//        params.add(new BasicNameValuePair(BIRTHDAY, mSharedPreferences.getString(BIRTHDAY, "N/A")));
//        params.add(new BasicNameValuePair(TEL, mSharedPreferences.getString(TEL, "N/A")));
//        params.add(new BasicNameValuePair(EDUCATION, mSharedPreferences.getString(EDUCATION, "N/A")));
//        params.add(new BasicNameValuePair(INCOME, mSharedPreferences.getString(INCOME, "N/A")));
//        return params;
//    }
}
