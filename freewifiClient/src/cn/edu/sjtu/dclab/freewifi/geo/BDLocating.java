package cn.edu.sjtu.dclab.freewifi.geo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import cn.edu.sjtu.dclab.freewifi.MainApplication;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.HTTPTool;

/**
 * Created by Ernest on 2015/5/27.
 */
public class BDLocating extends Activity {
    //	private static final String TAG = "BDLocating";

    MainApplication mApplication;//全局应用

    LocationClient mLocationClient;//定位服务的客户端
    BDLocation mCurLocation;//当前位置

    LatLng followPoint = null;//待跟踪的点坐标
    int radius = 2000;//检测半径，默认2000米
    boolean isOutBound = false;//是否离开限定区域

    TextView tvGeoInfo, tvDistance;
    Button btnLocating, btnChangeloc;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**全局Application初始化，尽量在setContentView之前初始化 */
        mApplication = MainApplication.GetInstance();
        //设置LoactionHandler
        mApplication.setLoactionHandler(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MainApplication.NEW_LOC_MSG) {
                    mCurLocation = (BDLocation) msg.obj;
                    locatingOnReceiveLocation();
                    followPoint = new LatLng(mCurLocation.getLatitude() + 0.005,
                            mCurLocation.getLongitude() + 0.005);//默认模拟点位置
                }
            }
        });

        /**UI初始化*/
        setContentView(R.layout.aty_bdlocation);
        tvGeoInfo = (TextView) findViewById(R.id.tv_geoinfo);
        tvDistance = (TextView) findViewById(R.id.tv_distance);
        btnLocating = (Button) findViewById(R.id.btn_locate);
        btnChangeloc = (Button) findViewById(R.id.btn_changeloc);

        /**定位初始化 */
        //定位服务的客户端，从BDApplication获取全局LocationClient
        mLocationClient = ((MainApplication)getApplication()).mLocationClient;
        //定义定位参数
        LocationClientOption option = new LocationClientOption();
        initLocationOptions(option);//设置定位参数
        mLocationClient.setLocOption(option);
        //启动定位sdk
        mLocationClient.start();
    }

    /**按钮单击事件处理
     * @param v
     */
    public void onClickProcess(View v) {
        if (v.getId() == R.id.btn_locate) {
            //请求定位，异步返回，结果在locationListener中获取
            mLocationClient.requestLocation();
        } else if (v.getId() == R.id.btn_changeloc) {
            //变更模拟点位置
            followPoint = new LatLng(followPoint.latitude + 0.005, followPoint.longitude + 0.005);
            //距离计算（单位：米），错误时返回-1；DistanceUtil为测距工具
            LatLng curPoint = new LatLng(mCurLocation.getLatitude(), mCurLocation.getLongitude());
            double distance = DistanceUtil.getDistance(followPoint, curPoint);//传入参数为百度经纬度坐标

            isOutBound = distance > (double)radius;
            if (isOutBound) {//超出限定区域
                Toast.makeText(getApplicationContext(), "超出范围：" + isOutBound + " !", Toast.LENGTH_SHORT).show();
                HTTPTool.SendRequestForWifiList(getApplicationContext(),
                        String.valueOf(mCurLocation.getLongitude()), String.valueOf(mCurLocation.getLatitude()));
            }
            tvDistance.setText("模拟点位置：lat = " + followPoint.latitude + " , lng = " + followPoint.longitude +
                    "\n测距结果：" + (int) distance + "米" +
                    "\n当前半径阈值：" + radius + "米" +
                    "\n是否超出范围：" + isOutBound);
        }
    }

    /**当获取新的位置信息时，进行相关处理
     */
    private void locatingOnReceiveLocation() {
        tvGeoInfo.setText(getLocationInfo(mCurLocation));
    }

    /**设置定位参数
     * @param option
     */
    private void initLocationOptions(LocationClientOption option) {
        option.setOpenGps(true);//打开gps
        option.setCoorType("bd09ll");//设置坐标类型Baidu encoded latitude & longtitude
        option.setScanSpan(1000 * 60 * 5);//扫描间隔5min
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//GPS + Network locating
        option.setAddrType("all");//locating results include all address infos
        option.setIsNeedAddress(true);//include address infos
    }

    /**记录获取的位置信息
     * @param location
     */
    private String getLocationInfo(BDLocation location) {
        StringBuffer sb = new StringBuffer(256);
        sb.append(" time : ");
        sb.append(location.getTime());
        sb.append("\n error code : ");
        sb.append(location.getLocType());
        sb.append("\n latitude : ");
        sb.append(location.getLatitude());
        sb.append("\n lontitude : ");
        sb.append(location.getLongitude());
        sb.append("\n radius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation){
            sb.append("\n speed : ");
            sb.append(location.getSpeed());
            sb.append("\n satellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append("\n direction : ");
            sb.append(location.getDirection());
            sb.append("\n addr : ");
            sb.append(location.getAddrStr());
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
            sb.append("\n addr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append("\n operationers : ");
            sb.append(location.getOperators());
        }
        return sb.toString();
    }

    @Override
    protected void onDestroy() {
        // 退出时停止定位sdk
        mLocationClient.stop();

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
