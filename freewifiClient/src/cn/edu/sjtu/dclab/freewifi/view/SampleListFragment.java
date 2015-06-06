package cn.edu.sjtu.dclab.freewifi.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;
import cn.edu.sjtu.dclab.freewifi.geo.BDMapFragment;
import cn.edu.sjtu.dclab.freewifi.tool.ClassParse;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class SampleListFragment extends ScrollTabHolderFragment implements OnScrollListener {

    private static final String ARG_LIST = "wifiList";
    private static final String ARG_POSITION = "position";
    private static final String ARG_LON = "lon";
    private static final String ARG_LAT = "lat";

    private ListView mListView;

    private List<WIFI> wifiList;
    private double lat;
    private double lon;

    private int mPosition;

    public static Fragment newInstance(Object wifiList,int position,double lat,double lon) {
        SampleListFragment f = new SampleListFragment();
        Bundle b = new Bundle();
        b.putString(ARG_LIST, new ClassParse().obj2String(wifiList));
        b.putInt(ARG_POSITION, position);
        b.putDouble(ARG_LON, lon);
        b.putDouble(ARG_LAT, lat);
        f.setArguments(b);
        return f;
    }

    public SampleListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String content  = getArguments().getString(ARG_LIST);
        mPosition = getArguments().getInt(ARG_POSITION);
        lon = getArguments().getDouble(ARG_LON);
        lat = getArguments().getDouble(ARG_LAT);
        wifiList = new ClassParse().string2WifiList(content);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);

        mListView = (ListView) v.findViewById(R.id.listView);

        View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(placeHolderView);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView.setOnScrollListener(this);
//        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, android.R.id.text1, mListItems));

        List<Bitmap> picList = new ArrayList<Bitmap>();
        for (WIFI wifi : wifiList) {
            if (wifi.getMerchant().getIcon() != null){
                byte[] icon = wifi.getMerchant().getIcon().getBytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(icon, 0, icon.length);
                picList.add(bitmap);
            }else{
                picList.add(null);
            }



        }
        mListView.setAdapter(new WifiItemAdapter(getActivity(), wifiList, picList));
        mListView.setOnItemClickListener(new ItemClickListner());


    }

    @Override
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
            return;
        }

        mListView.setSelectionFromTop(1, scrollHeight);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null)
            mScrollTabHolder.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, mPosition);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // nothing
    }

    // 点击项监听
    private class ItemClickListner implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(mPosition == 0){
                WIFI wifi = wifiList.get(i);
                LatLng start = new LatLng(lat,lon);
                LatLng latLng = new LatLng(wifi.getLatitude(),wifi.getLongitude());
                BDMapFragment.naviByBDMap(start,latLng,getActivity());
            }
        }
    }
}