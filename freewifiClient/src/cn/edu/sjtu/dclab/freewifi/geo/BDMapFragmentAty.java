package cn.edu.sjtu.dclab.freewifi.geo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import cn.edu.sjtu.dclab.freewifi.R;

public class BDMapFragmentAty extends Activity{
	
	BDMapFragment mapFragment = null;
	FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_bdmapfragment);
		
		fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		mapFragment = new BDMapFragment();
		transaction.add(R.id.mapcontent, mapFragment);
		transaction.commit();
	}

}
