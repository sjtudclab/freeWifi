package cn.edu.sjtu.dclab.freewifi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.sjtu.dclab.freewifi.test.*;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), WifiActivity.class);
        startActivity(intent);
        this.finish();
    }
}
