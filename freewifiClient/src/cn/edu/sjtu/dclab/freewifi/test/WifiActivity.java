package cn.edu.sjtu.dclab.freewifi.test;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.WifiAdmin;

import java.util.List;

/**
 * Created by Yang on 2015/5/22.
 */
public class WifiActivity extends Activity {
    private Button scan;
    private Button start;
    private Button stop;
    private Button status;
    private TextView textView;
    private WifiAdmin wifiAdmin;

    private StringBuffer sb = new StringBuffer();
    private ScanResult result;
    private List<ScanResult> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifilogout);
        wifiAdmin = new WifiAdmin(this);
        init();
    }

    public void init() {
        scan = (Button) findViewById(R.id.scan);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        status = (Button) findViewById(R.id.status);
        textView = (TextView) findViewById(R.id.textView);
        MyListener listener = new MyListener();
        scan.setOnClickListener(listener);
        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        status.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.scan:
                    getAllNetWorkList();
                    break;
                case R.id.start:
                    wifiAdmin.openWifi();
                    Toast.makeText(WifiActivity.this, "当前Wifi状态" + wifiAdmin.getWifiState(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.stop:
                    wifiAdmin.closeWifi();
                    Toast.makeText(WifiActivity.this, "当前wifi状态" + wifiAdmin.getWifiState(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.status:
                    Toast.makeText(WifiActivity.this, "当前wifi状态" + wifiAdmin.getWifiState(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    public void getAllNetWorkList(){
        if(sb!=null){
            sb=new StringBuffer();
        }
        wifiAdmin.wifiScan();
        list=wifiAdmin.getWifiList();
        if(list!=null){
            for(int i=0;i<list.size();i++){
                result =list.get(i);
                sb=sb.append(result.BSSID+"  ").append(result.SSID+"   ")
                        .append(result.capabilities+"   ").append(result.frequency+"   ")
                        .append(result.level+"\n\n");
            }
            textView.setText("wifi列表\n" + sb.toString());
        }
    }
}