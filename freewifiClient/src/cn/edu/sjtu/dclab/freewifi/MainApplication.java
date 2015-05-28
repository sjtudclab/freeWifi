package cn.edu.sjtu.dclab.freewifi;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;

/**BDApplication全局设置
 * 注意：在SDK各功能组件使用之前都需要调用SDKInitializer.initialize(getApplicationContext());
 * 因此建议该方法放在Application的初始化方法中
 * Created by Ernest on 2015/5/27.
 */
public class MainApplication extends Application {
    static final String TAG = "MainApplication";
    public static final int NEW_LOC_MSG = 0;

    private static MainApplication mBDApplicationInstance;

    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    //地理围栏
//	public GeofenceClient mGeofenceClient;

    public Handler mLocationHandler;

    public TextView mLocationResult;

    @Override
    public void onCreate() {
        super.onCreate();

        //注意：实现GetInstance()，此行代码易漏写
        mBDApplicationInstance = this;

        //百度地图
        //在使用百度地图SDK各组件之前初始化context信息，传入ApplicationContext。注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(this);

        //百度定位
        //LocationClient类必须在主线程中声明。需要Context类型的参数，且需全进程有效的context，推荐用getApplicationConext获取全进程有效的context
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        //注册监听函数，当没有注册监听函数时，无法发起网络请求
        mLocationClient.registerLocationListener(mMyLocationListener);

        //地理围栏服务提供的是基于位置的提醒服务
        //GeofenceClient类必须在主线程中声明。需要Context类型的参数，且需全进程有效的context，推荐用getApplicationConext获取全进程有效的context
//		mGeofenceClient = new GeofenceClient(getApplicationContext());

        //获取震动服务
//		mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }

    /**获取BDApplication实例
     * 注意：一定要在onCreate()获得自身的引用 mBDApplicationInstance = this;
     * @return
     */
    public static MainApplication GetInstance() {
        return mBDApplicationInstance;
    }

    /**设置mLoactionHandler全局参数
     * @param locationHandler
     */
    public void setLoactionHandler(Handler locationHandler){
        this.mLocationHandler = locationHandler;
    }

    /**实现位置回调监听
     * BDLocationListener接口需要实现方法：接收异步返回的定位结果，参数是BDLocation类型参数。
     * @author Eugene
     * @data 2014-12-24
     */
    public class MyLocationListener implements BDLocationListener {
        //定位请求回调函数
        //BDLocation类，封装了定位SDK的定位结果，在BDLocationListener的onReceive方法中获取。通过BDLocation用户可以获取error code，位置的坐标，精度半径等信息。
        @Override
        public void onReceiveLocation(BDLocation location) {
            //记录获取的位置信息
//			logLocationInfo(location);
            if (location == null) return;
            //发送新位置信息
            Message msg = new Message();
            msg.obj = location;
            msg.what = NEW_LOC_MSG;
            if(null != mLocationHandler) mLocationHandler.sendMessage(msg);
        }

    }

    /**记录获取的位置信息
     * @param location
     */
//	private void logLocationInfo(BDLocation location) {
//		StringBuffer sb = new StringBuffer(256);
//		sb.append(" time : ");
//		sb.append(location.getTime());
//		sb.append("\n error code : ");
//		sb.append(location.getLocType());
//		sb.append("\n latitude : ");
//		sb.append(location.getLatitude());
//		sb.append("\n lontitude : ");
//		sb.append(location.getLongitude());
//		sb.append("\n radius : ");
//		sb.append(location.getRadius());
//		if (location.getLocType() == BDLocation.TypeGpsLocation){
//			sb.append("\n speed : ");
//			sb.append(location.getSpeed());
//			sb.append("\n satellite : ");
//			sb.append(location.getSatelliteNumber());
//			sb.append("\n direction : ");
//			sb.append(location.getDirection());
//			sb.append("\n addr : ");
//			sb.append(location.getAddrStr());
//		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//			sb.append("\n addr : ");
//			sb.append(location.getAddrStr());
//			//运营商信息
//			sb.append("\n operationers : ");
//			sb.append(location.getOperators());
//		}
////		logMsg(sb.toString());
//		Log.i(TAG, sb.toString());
//	}

    /**显示请求字符串
     * @param str
     */
//	private void logMsg(String str) {
//		try {
//			if (mLocationResult != null)
//				mLocationResult.setText(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
