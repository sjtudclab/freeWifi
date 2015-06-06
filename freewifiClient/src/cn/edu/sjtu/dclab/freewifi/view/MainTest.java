package cn.edu.sjtu.dclab.freewifi.view;

import android.annotation.TargetApi;
import android.graphics.RectF;
import android.os.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;
import cn.edu.sjtu.dclab.freewifi.MainApplication;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.*;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.nineoldandroids.view.ViewHelper;

import java.util.Map;
import java.util.Set;

public class MainTest extends ActionBarActivity implements ScrollTabHolder, ViewPager.OnPageChangeListener {
    private final String TAG="tagtest";

    private static AccelerateDecelerateInterpolator sSmoothInterpolator = new AccelerateDecelerateInterpolator();

    private KenBurnsSupportView mHeaderPicture;
    private View mHeader;

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private int mActionBarHeight;
    private int mMinHeaderHeight;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private ImageView mHeaderLogo;

    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();

    private TypedValue mTypedValue = new TypedValue();
    private SpannableString mSpannableString;
    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
    ////////////////////////////////////////////////////////////
    private AsyncTask<String, Void, Object[]> getWifiTask;

    MainApplication mApplication;//全局应用
    LocationClient mLocationClient;//定位服务的客户端
    BDLocation mCurLocation;//当前位置
    LatLng followPoint = null;//待跟踪的点坐标
    int radius = 2000;//检测半径，默认2000米
    boolean isOutBound = false;//是否离开限定区域

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
                    followPoint = new LatLng(mCurLocation.getLatitude() + 0.005,
                            mCurLocation.getLongitude() + 0.005);//默认模拟点位置
                }
            }
        });
    /**定位初始化 */
        //定位服务的客户端，从BDApplication获取全局LocationClient
        mLocationClient = ((MainApplication)getApplication()).mLocationClient;
        //定义定位参数
        LocationClientOption option = new LocationClientOption();
        initLocationOptions(option);//设置定位参数
        mLocationClient.setLocOption(option);
        //启动定位sdk
        mLocationClient.start();

        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mMinHeaderHeight + getActionBarHeight();

        setContentView(R.layout.aty_maintest);

        mHeaderPicture = (KenBurnsSupportView) findViewById(R.id.header_picture);
        mHeaderPicture.setResourceIds(R.drawable.pic0, R.drawable.pic1);
        mHeaderLogo = (ImageView) findViewById(R.id.header_logo);
        mHeader = findViewById(R.id.header);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(4);


        mPagerSlidingTabStrip.setOnPageChangeListener(this);
        mSpannableString = new SpannableString(getString(R.string.actionbar_title));
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(0xffffffff);

        ViewHelper.setAlpha(getActionBarIconView(), 0f);

        getSupportActionBar().setBackgroundDrawable(null);

        initTask();
        getWifiTask.execute();

    }

    @Override
    protected void onDestroy() {
        // 退出时停止定位sdk
        mLocationClient.stop();

        super.onDestroy();
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


    private void initTask(){
        getWifiTask = new AsyncTask<String, Void, Object[]>() {
            @Override
            protected Object[] doInBackground(String... params) {
                Object[]  datas = new Object[4];
                mLocationClient.requestLocation();
                while(mCurLocation == null){}
                String res1 = HTTPTool.sendRequestForWifiList(MainTest.this, ""+mCurLocation.getLongitude(),""+mCurLocation.getLatitude());

                ClassParse parser = new ClassParse();
                Map<String,Object> map = parser.string2Map(res1);
                Object data = null;
                if (map != null && map.get("data") != null){
                    data = map.get("data");
                }
                datas[0] = data;
                String deviceId = SharedDataTool.GetIMEI(MainTest.this);

                String device_Id = deviceId;
                String wifi_Id = "19";
                setAlias(device_Id);

                UserService userService = new UserService(MainTest.this,getResources().getString(R.string.server)+"/user/notification");
                userService.notification(deviceId,wifi_Id);

                String res2 = HTTPTool.sendRequestForAdList(MainTest.this,deviceId);
                Map<String,Object> adMap = parser.string2Map(res2);
                Object adData = null;
                if (adMap != null && adMap.get("data") != null){
                    adData = adMap.get("data");
                }
                datas[1] = adData;


                String res3 = HTTPTool.sendRequestForMerchantList(MainTest.this,deviceId);
                Map<String,Object> merMap = parser.string2Map(res3);
                Object merData = null;
                if (merMap != null && merMap.get("data") != null){
                    merData = merMap.get("data");
                }
                datas[2] = merData;

                datas[3] = SharedDataTool.GetAllInfoList(MainTest.this);

                return datas;
            }

            @Override
            protected void onPostExecute( Object[] args) {
                /*ClassParse parser = new ClassParse();
                Map<String,Object> map = parser.string2Map(args[0]);
                Object data = null;
                if (map != null && map.get("data") != null){
                    data = map.get("data");
                }*/

                /*Map<String,Object> adMap = parser.string2Map(args[1]);
                Object adData = null;
                if (adMap != null && adMap.get("data") != null){
                    adData = adMap.get("data");
                }

                Map<String,Object> merMap = parser.string2Map(args[2]);
                Object merData = null;
                if (merMap != null && merMap.get("data") != null){
                    merData = merMap.get("data");
                }*/

                //List<String> info = parser.string2StringList(args[2]);

                //Object[] datas = new Object[]{data,args[1],args[2],args[3]};
                mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),args);
                mPagerAdapter.setTabHolderScrollingContent(MainTest.this);
                mViewPager.setAdapter(mPagerAdapter);
                mPagerSlidingTabStrip.setViewPager(mViewPager);
            }
        };

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // nothing
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // nothing
    }

    @Override
    public void onPageSelected(int position) {
        SparseArrayCompat<ScrollTabHolder> scrollTabHolders = mPagerAdapter.getScrollTabHolders();
        ScrollTabHolder currentHolder = scrollTabHolders.valueAt(position);

        currentHolder.adjustScroll((int) (mHeader.getHeight() + ViewHelper.getTranslationY(mHeader)));
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int scrollY = getScrollY(view);
            ViewHelper.setTranslationY(mHeader, Math.max(-scrollY, mMinHeaderTranslation));
            float ratio = clamp(ViewHelper.getTranslationY(mHeader) / mMinHeaderTranslation, 0.0f, 1.0f);
            interpolate(mHeaderLogo, getActionBarIconView(), sSmoothInterpolator.getInterpolation(ratio));
            setTitleAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        // nothing
    }

    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mHeaderHeight;
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }

    private void interpolate(View view1, View view2, float interpolation) {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));

        ViewHelper.setTranslationX(view1, translationX);
        ViewHelper.setTranslationY(view1, translationY - ViewHelper.getTranslationY(mHeader));
        ViewHelper.setScaleX(view1, scaleX);
        ViewHelper.setScaleY(view1, scaleY);
    }

    private RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        } else {
            getTheme().resolveAttribute(R.attr.actionBarSize, mTypedValue, true);
        }

        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());

        return mActionBarHeight;
    }

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(mSpannableString);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ImageView getActionBarIconView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return (ImageView) findViewById(android.R.id.home);
        }

        return (ImageView) findViewById(android.support.v7.appcompat.R.id.home);
        //return (ImageView)findViewById(android.R.id.home);

    }

    public class PagerAdapter extends FragmentPagerAdapter {

        private SparseArrayCompat<ScrollTabHolder> mScrollTabHolders;
        private final String[] TITLES = {"Wifi列表", "广告","商家",  "个人信息"};
        private ScrollTabHolder mListener;
        private Object[] contents;

        public PagerAdapter(FragmentManager fm,Object[] contents) {
            super(fm);
            mScrollTabHolders = new SparseArrayCompat<ScrollTabHolder>();
            this.contents = contents;
        }

        public void setTabHolderScrollingContent(ScrollTabHolder listener) {
            mListener = listener;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            ScrollTabHolderFragment fragment = (ScrollTabHolderFragment) SampleListFragment.newInstance(contents[position],position,mCurLocation.getLatitude(),mCurLocation.getLongitude());
            mScrollTabHolders.put(position, fragment);
            if (mListener != null) {
                fragment.setScrollTabHolder(mListener);
            }
            return fragment;
        }

        public SparseArrayCompat<ScrollTabHolder> getScrollTabHolders() {
            return mScrollTabHolders;
        }

    }

    private void setAlias(String alias){
        if (TextUtils.isEmpty(alias)) {
            return;
        }
        boolean debug = Boolean.parseBoolean(getResources().getString(R.string.debug));
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            if (debug) {
                Toast.makeText(MainTest.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        //调用JPush API设置Alias
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private static final int MSG_SET_ALIAS = 1001;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (ExampleUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

            ExampleUtil.showToast(logs, getApplicationContext());
        }

    };

}