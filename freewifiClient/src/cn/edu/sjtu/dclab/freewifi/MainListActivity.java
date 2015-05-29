<<<<<<< HEAD
package cn.edu.sjtu.dclab.freewifi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.edu.sjtu.dclab.freewifi.geo.BDLocating;
import cn.edu.sjtu.dclab.freewifi.test.JsonTestAty;
import cn.edu.sjtu.dclab.freewifi.test.WifiActivity;
import cn.edu.sjtu.dclab.freewifi.web.WebViewInteractJS;

/**MainListActivity extends ListActivity
 * @author Eugene
 * @date 2014-12-3
 */
public class MainListActivity extends ListActivity{
	
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				getResources().getStringArray(R.array.aty_list)));
		mListView = getListView();
		mListView.setTextFilterEnabled(true);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplicationContext(), WifiActivity.class));
					break;
				case 1:
					startActivity(new Intent(getApplicationContext(), WebViewInteractJS.class));
					break;
				case 2:
					startActivity(new Intent(getApplicationContext(), BDLocating.class));
					break;
				case 3:
					startActivity(new Intent(getApplicationContext(), JsonTestAty.class));
					break;
				default:
					break;
				}
			}
		});
	}

}
=======
package cn.edu.sjtu.dclab.freewifi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.edu.sjtu.dclab.freewifi.geo.BDLocating;
import cn.edu.sjtu.dclab.freewifi.web.MyWebView;
import cn.edu.sjtu.dclab.freewifi.test.WifiActivity;

/**MainListActivity extends ListActivity
 * @author Eugene
 * @date 2014-12-3
 */
public class MainListActivity extends ListActivity{
	
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				getResources().getStringArray(R.array.aty_list)));
		mListView = getListView();
		mListView.setTextFilterEnabled(true);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getApplicationContext(), WifiActivity.class));
					break;
				case 1:
					startActivity(new Intent(getApplicationContext(), MyWebView.class));
					break;
				case 2:
					startActivity(new Intent(getApplicationContext(), BDLocating.class));
					break;
				default:
					break;
				}
			}
		});
	}

}
>>>>>>> ae5c3b9537856d45590da70c239a35fb582fe88b
