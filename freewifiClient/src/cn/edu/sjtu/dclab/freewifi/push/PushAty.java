package cn.edu.sjtu.dclab.freewifi.push;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import cn.edu.sjtu.dclab.freewifi.R;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Yang on 2015/5/29.
 */
public class PushAty extends Activity {
    private Button initBtn;
    private Button stopBtn;
    private Button resumeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_push);

        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void initView(){
        initBtn = (Button)findViewById(R.id.initPush);
        stopBtn = (Button)findViewById(R.id.stopPush);
        resumeBtn = (Button)findViewById(R.id.resumePush);

        ButtonClickListener listener = new ButtonClickListener();
        initBtn.setOnClickListener(listener);
        stopBtn.setOnClickListener(listener);
        resumeBtn.setOnClickListener(listener);
    }

    class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.initPush:
                    JPushInterface.init(getApplicationContext());
                    break;

                case R.id.stopPush:
                    JPushInterface.stopPush(getApplicationContext());
                    break;

                case R.id.resumePush:
                    JPushInterface.resumePush(getApplicationContext());
                    break;

                default:
                    break;
            }
        }
    }
}