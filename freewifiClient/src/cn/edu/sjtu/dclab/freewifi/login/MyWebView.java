package cn.edu.sjtu.dclab.freewifi.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import cn.edu.sjtu.dclab.freewifi.R;


/**MyWebView extends Activity
 * Web App效果测试；
 * @author Eugene
 * @data 2015-1-24
 */
public class MyWebView extends Activity{
//	private static final String TAG = "MyWebView";
	
	private static final String URL = "file:///android_asset/index.html";
	private WebView webView = null;
	private ProgressDialog pd = null;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
	    public void handleMessage(Message msg) {
	        if (!Thread.currentThread().isInterrupted()){  
	            switch (msg.what) {  
	            case 0:  
	                pd.show();//显示进度对话框  
	                break;  
	            case 1:
	            	//隐藏进度对话框，不可使用dismiss()与cancel()，否则再次调用show()时显示的对话框小圆圈不会动 
	                pd.hide();
	                break;  
	            }  
	        }  
	        super.handleMessage(msg);  
	    }  
	};
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_mywebview);

		//ProgressDialog最好于网页加载前初始化，建议show()使用handler机制调用（避免加载过程中空指针问题）
		pd = new ProgressDialog(getApplicationContext());
		pd.setMessage("Loading...");
		
		webView = (WebView) findViewById(R.id.webview);
		webView.setScrollBarStyle(0);//滚动条风格，为0指滚动条不占用空间，直接覆盖在网页上
		WebSettings setting = webView.getSettings();
		setting.setJavaScriptEnabled(true);//支持js
		setting.setDefaultTextEncodingName("UTF-8");//设置字符编码
		setting.setSupportZoom(true);//缩放 default: true
		setting.setBuiltInZoomControls(false);//缩放 default: false
		//setWebChromeClient主要处理js对话框、图标、页面标题等
		webView.setWebChromeClient(new WebChromeClient(){
			@Override//载入进度改变而触发
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 0) {
					handler.sendEmptyMessage(0);//初始载入时，显示进度对话框
				}else if (newProgress == 100) {  
                    handler.sendEmptyMessage(1);//载入完毕，则隐藏进度对话框  
				}  
				super.onProgressChanged(view, newProgress);
			}
		});
		
		webView.loadUrl(URL);
	}

	//回退按键事件处理
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
			webView.goBack();
			return true;//已经处理完，返回true，无需系统再处理
		}
		return super.onKeyDown(keyCode, event);
	}
	
	//创建菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.addSubMenu(0, 0, 0, "Refresh");
		menu.addSubMenu(0, 0, 1, "Back");
		menu.addSubMenu(0, 0, 2, "Forward");
		return true;
		//return super.onCreateOptionsMenu(menu);
	}
	
	//菜单项点击事件处理
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getOrder()) {
		case 0:
			webView.reload();
			break;
		case 1:
			if (webView.canGoBack()) webView.goBack();
			else Toast.makeText(getApplicationContext(), "Can not go back.", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			if (webView.canGoForward()) webView.goForward();
			else Toast.makeText(getApplicationContext(), "Can not go forward.", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
