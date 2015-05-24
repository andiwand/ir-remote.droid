package at.stefl.irmote.droid.js;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsConsole extends JsObject {

	public static final JsConsole INSTANCE = new JsConsole();

	private JsConsole() {
	}

	@JavascriptInterface
	public void log(String message) {
		Log.d("javascript", message);
	}

}
