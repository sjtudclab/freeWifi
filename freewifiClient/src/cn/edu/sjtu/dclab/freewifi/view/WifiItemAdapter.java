package cn.edu.sjtu.dclab.freewifi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.domain.WIFI;

import java.util.ArrayList;
import java.util.List;

public class WifiItemAdapter extends BaseAdapter {
	private Context context;
	private List<WIFI> list = new ArrayList<WIFI>();
	private List<Bitmap> bitmaps = new ArrayList<Bitmap>();

	public WifiItemAdapter(Context context, List<WIFI> list,
						   List<Bitmap> bitmaps) {
		this.context = context;
		this.list = list;
		this.bitmaps = bitmaps;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		WIFI wifi = list.get(position);
		Item item = null;
		if (view == null) {
			item = new Item();
			view = LayoutInflater.from(context).inflate(R.layout.item, parent,
					false);
			item.icon = (ImageView) view.findViewById(R.id.img_icon);
			item.name = (TextView) view.findViewById(R.id.txt_name);
			item.address = (TextView) view.findViewById(R.id.txt_address);
			item.distance = (TextView) view.findViewById(R.id.txt_distance);

			view.setTag(item);
		} else {
			item = (Item) view.getTag();
		}
		Bitmap bitmap = bitmaps.get(position);
		if (bitmap != null) {
			item.icon.setImageBitmap(bitmap);
		} else {
			item.icon.setImageResource(R.drawable.ic_launcher);
		}

		item.name.setText(wifi.getMerchant().getName());
		item.address.setText(wifi.getMerchant().getAddress());
		item.distance.setText(wifi.getLatitude()+":"+wifi.getLongitude());

		return view;
	}

	class Item {
		ImageView icon;
		TextView name;
		TextView address;
		TextView distance;
	}
}
