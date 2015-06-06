package cn.edu.sjtu.dclab.freewifi.view;

import android.widget.AbsListView;

public interface ScrollTabHolder {

    void adjustScroll(int scrollHeight);

    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);
}
