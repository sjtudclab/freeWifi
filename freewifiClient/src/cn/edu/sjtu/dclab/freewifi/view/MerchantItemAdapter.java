package cn.edu.sjtu.dclab.freewifi.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.domain.Merchant;

import java.util.ArrayList;
import java.util.List;

public class MerchantItemAdapter extends BaseAdapter {
	private Context context;
	private List<Merchant> list = new ArrayList<Merchant>();

	public MerchantItemAdapter(Context context, List<Merchant> list) {
		this.context = context;
		this.list = list;
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
		Merchant merchant = list.get(position);
		Item item = null;
		if (view == null) {
			item = new Item();
			view = LayoutInflater.from(context).inflate(R.layout.ad_item, parent,
					false);
			item.name = (TextView) view.findViewById(R.id.txt_name);
			item.address = (TextView) view.findViewById(R.id.txt_address);

			view.setTag(item);
		} else {
			item = (Item) view.getTag();
		}

		item.name.setText(merchant.getName());
		item.address.setText(merchant.getAddress());

		return view;
	}

	class Item {
		TextView name;
		TextView address;
	}
}
