package cn.edu.sjtu.dclab.freewifi.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import cn.edu.sjtu.dclab.freewifi.R;

public class SampleListFragment extends ScrollTabHolderFragment implements OnScrollListener {

    private static final String ARG_POSITION = "position";

    private ListView mListView;
    //private ArrayList<String> mListItems;

    private int mPosition;

    public static Fragment newInstance(int position) {
        SampleListFragment f = new SampleListFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);

//        mListItems = new ArrayList<String>();
//
//        for (int i = 1; i <= 100; i++) {
//            mListItems.add(i + ". item - currnet page: " + (mPosition + 1));
//        }

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
        //TODO

//        HTTPTool httpTool = new HTTPTool();

//        String lon = "116.31347892381";
//        String lat = "39.989511138466";
////        httpTool.SendRequestForWifiList(getActivity(), lon, lat);
//        List<WIFI> wifiList = httpTool.SendRequestForWifiList(getActivity(), lon, lat);
//        List<Bitmap> picList = new ArrayList<Bitmap>();
//        for (WIFI wifi : wifiList) {
//            byte[] icon = wifi.getMerchant().getIcon().getBytes();
//            Bitmap bitmap = BitmapFactory.decodeByteArray(icon, 0, icon.length);
//            picList.add(bitmap);
//        }
//        mListView.setAdapter(new WifiItemAdapter(getActivity(), wifiList, picList));
//        mListView.setOnItemClickListener(new ItemClickListner());
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

//    // 点击项监听
//    private class ItemClickListner implements AdapterView.OnItemClickListener{
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            //TODO
//            Toast.makeText(getActivity(),"Click: "+i, Toast.LENGTH_SHORT);
//        }
//    }
}