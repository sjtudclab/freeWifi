package cn.edu.sjtu.dclab.freewifi.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

import cn.edu.sjtu.dclab.freewifi.R;
import cn.edu.sjtu.dclab.freewifi.tool.HTTPTool;
import cn.edu.sjtu.dclab.freewifi.tool.SharedDataTool;

/**UserInfoWeb extends Activity
 * WebView测试：与本地资源（系统照相机）的交互；创建菜单示例；按键事件处理；ProgressDialog示例；
 * 
 * 改善浏览器用户体验可以调用setWebViewClient和setWebChromeClient，
 * setWebViewClient主要用于处理webView的控制问题（如加载、关闭、错误处理等），
 * setWebChromeClient主要处理js对话框、图标、页面标题等：
 * 		1. setWebChromeClient可以处理progress的加载、js对话框、显示icon图标等，改善用户体检；
 * 		2. setWebViewClient一般用来处理HTML的加载与关闭；
 * 			HTML的加载：需重载onPageStarted(WebView view, String url, Bitmap favicon)；
 * 			HTML的关闭：需要重载onPageFinished(WebViewview, String url)；
 * 
 * 知识点：获取assets绝对路径的格式"file:///android_asset/filename"
 * 知识点：java调用js函数，调用格式javascript:functionName('param0', 'param1', ...)
 * 知识点：js调用java方法，调用格式window.objectName.functionName()
 * 
 * 注意：如果将targetSdkVersion 设置为17或者更高，必须给暴露的js接口加@JavascriptInterface注释
 * 
 * @author Eugene
 * @data 2015-1-23
 */
public class LoginWeb extends Activity{
	private static final String TAG = "LoginWeb";

	static final int REQUEST_CODE = 100;
	private static final String URL_LOCAL_LOGIN = "file:///android_asset/login.html";

	boolean isFromCamera = false;//是否是从系统拍照界面返回
	private File file = null;
	
	private WebView webView = null;
	private ProgressDialog pd = null;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
	    public void handleMessage(Message msg) {
//	        if (!Thread.currentThread().isInterrupted()){
//	            switch (msg.what) {
//	            case 0:
//	                pd.show();//显示进度对话框
//	                break;
//	            case 1:
//	            	//隐藏进度对话框，不可使用dismiss()与cancel()，否则再次调用show()时显示的对话框小圆圈不会动
//	                pd.hide();
//	                break;
//	            }
//	        }
//	        super.handleMessage(msg);
	    }  
	};
	
	@SuppressLint({ "SetJavaScriptEnabled"})
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_mywebview);

		//仅供测试
//		HTTPTool.SendRequestForWifiList(getApplicationContext(), "116.31347892381", "39.989511138466");
//		HTTPTool.SetHandler(new Handler(){
//			@Override
//			public void handleMessage(Message msg) {
//				if(msg.what == HTTPTool.RECEIVE_JSON_STRING){
//					Log.i(TAG, (String)msg.obj);
//				}
//			}
//		});
		
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
//			@Override//载入进度改变而触发
//			public void onProgressChanged(WebView view, int newProgress) {
//				if (newProgress == 0) {
//					handler.sendEmptyMessage(0);//初始载入时，显示进度对话框
//				}else if (newProgress == 100) {
//                    handler.sendEmptyMessage(1);//载入完毕，则隐藏进度对话框
//				}
//				super.onProgressChanged(view, newProgress);
//			}
//			@Override//实现js中的alert弹窗在Activity中显示
//	        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//	            result.confirm();
//	            return true;
//	        }
		});
		//setWebViewClient主要用于处理webView的控制问题（如加载、关闭、错误处理等）
		//webView.setWebViewClient(new WebViewClient());
		
		//获取assets绝对路径的格式"file:///android_asset/filename"
		webView.loadUrl(URL_LOCAL_LOGIN);
		
		//添加js需调用执行的java函数
		//如果将targetSdkVersion 设置为17或者更高，必须给暴露的js接口加@JavascriptInterface注释
		webView.addJavascriptInterface(new Object(){
			//如果将targetSdkVersion 设置为17或者更高，必须给暴露的js接口加@JavascriptInterface注释
			@JavascriptInterface//将被js调用
			public void sendLoginInfo(final String account, final String password) {
	            handler.post(new Runnable() {
	                public void run() {
	                	Log.i(TAG, "sendLoginInfo()");
						Log.i(TAG, account + " " + password + " ");
						SharedDataTool.WriteLoginInfo(getApplicationContext(), account, password);//先保存一份到本地
						//发送注册信息到服务器
						HTTPTool.SendLoginInfo(getApplicationContext(), account, password);
	                }
	            });
	        }
		}, "javaSender");//the name used to expose the object in JavaScript
		webView.addJavascriptInterface(new Object(){
			@JavascriptInterface//将被js调用
			public void callRegister() {
				handler.post(new Runnable() {
					public void run() {
						Log.i(TAG, "callRegister()");
						//跳到注册界面
						startActivity(new Intent(getApplicationContext(), UserInfoWeb.class));
						//finish();
					}
				});
			}
		}, "javaSender");//the name used to expose the object in JavaScript
	}

	/**调用系统相机拍照
	 */
//	private String takePhoto(String fileName){
//		Log.i(TAG, "takePhoto()");
//		isFromCamera = true;
//		Intent intent = new Intent();
//		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//指定拍照的意图MediaStore.ACTION_IMAGE_CAPTURE
//		file = new File(Environment.getExternalStorageDirectory(), fileName);
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); //指定保存文件的路径
//		startActivityForResult(intent, REQUEST_CODE);
//		if (file != null) return file.getAbsolutePath();
//		return null;
//	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(requestCode == REQUEST_CODE && isFromCamera){
//			//java调用js函数，调用格式javascript:functionName('param0', 'param1', ...)
//			webView.loadUrl("javascript:setPhotoPathAndSrc('" + file.getAbsolutePath() + "')");
//		} else {
//			webView.loadUrl("javascript:alert('Please take a photo.')");
//		}
//		isFromCamera = false;
//		super.onActivityResult(requestCode, resultCode, data);
//	}
	
	//回退按键事件处理
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
//			webView.goBack();
			finish();
			return true;//已经处理完，返回true，无需系统再处理
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

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
