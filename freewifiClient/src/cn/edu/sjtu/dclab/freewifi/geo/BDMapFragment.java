package cn.edu.sjtu.dclab.freewifi.geo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import cn.edu.sjtu.dclab.freewifi.MainApplication;
import cn.edu.sjtu.dclab.freewifi.R;

public class BDMapFragment extends Fragment implements View.OnClickListener{
	static final String TAG = "BDMapFragment";
	
	MainApplication mApplication;//全局应用
	
	MapView mMapView;//百度地图视图控件
	BaiduMap mMapController;//百度地图控制器
	
	LocationClient mLocationClient;//定位服务的客户端
	LocationClientOption locationOption;//定位参数
	BDLocation mCurLocation;//当前位置
	
	View view = null;
//	TextView tvPetName, tvDistance, tvBattery;
	Button locateBtn;

	int radius  = 100;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_bdmap, container, false);
		return view;
	}
	
	@SuppressLint("HandlerLeak")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		/**全局Application初始化，尽量在setContentView之前初始化 */
		mApplication = MainApplication.GetInstance();
		//设置LoactionHandler
		mApplication.setLoactionHandler(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == MainApplication.NEW_LOC_MSG){
					mCurLocation = (BDLocation) msg.obj;
//					if(mMapController != null){
//						mMapController.clear();
//					}
					locatingOnReceiveLocation();
					drawCircleOnReceiveLocation(radius);
//					drawPetIcon(petLocation);
				}
			}
		});
		
		/**UI初始化*/
		locateBtn = (Button) view.findViewById(R.id.btn_locate);
		locateBtn.setOnClickListener(this);

		/**地图视图、控制器初始化 */
		mMapView = (MapView) view.findViewById(R.id.mapView);//百度地图视图控件
		mMapController = mMapView.getMap();//百度地图控制器
		initMapStatus();
		//开启定位图层
		mMapController.setMyLocationEnabled(true);//setMyLocationEnabled设置是否允许定位图层
		
		/**定位初始化 */
		//定位服务的客户端，从BDApplication获取全局LocationClient
		mLocationClient = ((MainApplication)getActivity().getApplication()).mLocationClient;
		//定义定位参数
		locationOption = new LocationClientOption();
		initLocationOptions(locationOption);//设置定位参数
		mLocationClient.setLocOption(locationOption);
		//启动定位sdk
		mLocationClient.start();

	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_locate) {
			//请求定位，异步返回，结果在locationListener中获取
			mLocationClient.requestLocation();
		}
	}
	
	/**设置地图状态
	 */
	private void initMapStatus() {
		mMapView.showZoomControls(false);//取消缩放控件
		
		//MapStatusUpdateFactory生成地图状态将要发生的变化
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);//设置缩放级别
		//setMapStatus更新地图状态
		mMapController.setMapStatus(msu);
		
		//MapStatus定义地图状态，MapStatus.Builder为地图状态构造器
		MapStatus mapStatus = new MapStatus.Builder(mMapController.getMapStatus()).rotate(0).build();
		MapStatusUpdate u = MapStatusUpdateFactory.newMapStatus(mapStatus);//newMapStatus设置地图新状态
		//animateMapStatus以动画方式更新地图状态，动画耗时 300 ms
		mMapController.animateMapStatus(u);
	}
	
	/**当获取新的位置信息时，进行相关处理
	 */
	private void locatingOnReceiveLocation() {
		//MyLocationData定位数据，MyLocationData.Builder定位数据建造器
		MyLocationData locData = new MyLocationData.Builder()
			.accuracy(0)//设置定位数据的精度信息，单位：米（设置为0则不显示精度覆盖图层）
//			.direction(100)//设置定位数据的方向信息，顺时针0-360
			.latitude(mCurLocation.getLatitude())
			.longitude(mCurLocation.getLongitude()).build();
		mMapController.setMyLocationData(locData);
		//MyLocationConfiguration配置定位图层显示方式
		mMapController.setMyLocationConfigeration(
				new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.NORMAL, //定位图层显示方式
					false, //是否允许显示方向信息
					BitmapDescriptorFactory.fromResource(R.drawable.me_small)));//用户自定义定位图标
		//以动画方式更新地图状态到当前位置
		LatLng ll = new LatLng(mCurLocation.getLatitude(), mCurLocation.getLongitude());
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);//newLatLng设置地图新中心点
		mMapController.animateMapStatus(u);//animateMapStatus以动画方式更新地图状态，动画耗时 300 ms
	}
	
	/**绘制以当前位置为圆心的圆
	 */
	private void drawCircleOnReceiveLocation(int radius) {
		LatLng llCircle = new LatLng(mCurLocation.getLatitude(), mCurLocation.getLongitude());
		//CircleOptions创建圆的选项
		OverlayOptions circleOverlayOptions = new CircleOptions()
				.fillColor(0x7F89aee5)//设置圆填充颜色
				.center(llCircle)//设置圆心坐标
				.stroke(new Stroke(2, 0xDD89aee5))//设置圆边框信息，边框的宽度默认为 5（像素）
				.radius(radius);//设置圆半径（米）
		mMapController.addOverlay(circleOverlayOptions);//向地图添加一个 Overlay
	}
	
	/**在给定坐标绘制宠物头像
	 * @param petLocation
	 */
	private void drawPetIcon(LatLng petLocation) {
//		if (toggleBtnPetGPS.getToggleState()) {//只有开启宠物GPS才绘制
//			if (petLocation != null) {
//				mMapController.addOverlay(
//						new MarkerOptions()//MarkerOptions标记覆盖物
//						.position(petLocation)//设置 marker 覆盖物的位置坐标
//						//设置 Marker覆盖物的图标，相同图案的 icon的 marker最好使用同一个 BitmapDescriptor对象以节省内存空间。
//						.icon(BitmapDescriptorFactory.fromResource(R.drawable.pet_median)));
//			}
//		}
	}
	
	/**绘制模拟的点
	 */
//	private void drawDotOnReceiveLocation(double lat, double lng) {
//		LatLng llDot = new LatLng(lat, lng);
//		OverlayOptions ooDot = new DotOptions()
//			.center(llDot)//设置圆点的圆心坐标
//			.radius(15)//设置圆点的半径（像素）, 默认为 5px
//			.color(0xFF0000FF);//设置圆点的颜色
//		mMapController.addOverlay(ooDot);
//	}
	
	/**设置定位参数
	 * @param option
	 */
	private void initLocationOptions(LocationClientOption option) {
		option.setOpenGps(true);//打开gps
		option.setCoorType("bd09ll");//设置坐标类型Baidu encoded latitude & longtitude
//		option.setScanSpan(1000 * 60 * 5);//扫描间隔5min
		option.setScanSpan(30 * 1000);//扫描间隔30s
		option.setLocationMode(LocationMode.Hight_Accuracy);//GPS + Network locating
		option.setAddrType("all");//locating results include all address infos
		option.setIsNeedAddress(true);//include address infos
	}
	
	@Override
    public void onStart() {
    	super.onStart();
    }
	
    @Override
	public void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView.onResume()，实现地图生命周期管理  
        mMapView.onResume();  
    }
    
    @Override
	public void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView.onPause()，实现地图生命周期管理  
        mMapView.onPause();  
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    }

    @Override
    public void onDestroy() {  
    	// 退出时停止定位sdk
    	mLocationClient.stop();
    	// 关闭定位图层
    	mMapController.setMyLocationEnabled(false);
    	
    	mMapView.onDestroy();
    	mMapView = null;
    	super.onDestroy();
    }
}
