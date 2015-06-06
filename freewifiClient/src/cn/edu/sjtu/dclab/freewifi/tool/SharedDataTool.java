package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

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
    public final static String ENGAGE = "engage";
    public final static String BABY = "baby";
    public final static String JOB = "job";
    public final static String ACCOUNT = "account";
    public final static String PASSWORD = "password";

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

    public static List<String> GetAllInfoList(Context context){
        List<String> list = new ArrayList<String>();
        initSharedData(context);
        list.add("手机号" + ":" + mSharedPreferences.getString(TEL, null));
        list.add("性别" + ":" + mSharedPreferences.getString(GENDER, null));
        list.add("婚姻" + ":" + mSharedPreferences.getString(ENGAGE, null));
        list.add("生日" + ":" + mSharedPreferences.getString(BIRTHDAY, null));
        list.add("育儿状态" + ":" + mSharedPreferences.getString(BABY, null));
        list.add("职位角色" + ":" + mSharedPreferences.getString(JOB, null));
        list.add("教育水平" + ":" + mSharedPreferences.getString(EDUCATION, null));
        list.add("收入水平" + ":" + mSharedPreferences.getString(INCOME, null));
        return list;
    }


    public static boolean WriteRegisterInfo(Context context, String gender, String birthday,
                                            String tel, String pwd, String education, String income,
                                            String engage, String baby, String job){
        initSharedData(context);
        WriteIMEI(context);
        mEditor.putString(GENDER, gender);
        mEditor.putString(BIRTHDAY, birthday);
        mEditor.putString(TEL, tel);
        mEditor.putString(PASSWORD, pwd);
        mEditor.putString(EDUCATION, education);
        mEditor.putString(INCOME, income);
        mEditor.putString(ENGAGE, engage);
        mEditor.putString(BABY, baby);
        mEditor.putString(JOB, job);
        return mEditor.commit();
    }

    public static boolean WriteLoginInfo(Context context, String account, String password){
        initSharedData(context);
        WriteIMEI(context);
        mEditor.putString(ACCOUNT, account);
        mEditor.putString(PASSWORD, password);
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
    public static int GetEngageIndex(String engage){
        int index = -1;
        switch (engage){
            case "已婚": index = 0; break;
            case "未婚": index = 1; break;
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
    public static int GetJobIndex(String job){
        int index = -1;
        switch (job){
            case "学生": index = 0; break;
            case "工业": index = 1; break;
            case "服务业": index = 2; break;
            case "IT技术": index = 3; break;
            case "教育行业": index = 4; break;
            case "中小企业主": index = 5; break;
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
    public static int GetBabyIndex(String baby){
        int index = -1;
        switch (baby){
            case "无": index = 0; break;
            case "孕期": index = 1; break;
            case "0-3岁": index = 2; break;
            case "3-6岁": index = 3; break;
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
