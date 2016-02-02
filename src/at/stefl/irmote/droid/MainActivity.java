package at.stefl.irmote.droid;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import at.stefl.irmote.droid.js.JsInterface;
import at.stefl.irremote.droid.R;

public class MainActivity extends ActionBarActivity {

	private WebView webView;

	// TODO: remove suppress
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) 
			webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

		webView.addJavascriptInterface(JsInterface.INSTANCE, "Android");
		webView.loadUrl("file:///android_asset/html/index.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;
		return super.onOptionsItemSelected(item);
	}

}
