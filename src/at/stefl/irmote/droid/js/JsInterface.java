package at.stefl.irmote.droid.js;

import android.webkit.JavascriptInterface;

public class JsInterface {

	public static final JsInterface INSTANCE = new JsInterface();

	private JsInterface() {
	}

	@JavascriptInterface
	public JsConsole getConsole() {
		return JsConsole.INSTANCE;
	}

	@JavascriptInterface
	public JsRemote getRemote() {
		return JsRemote.INSTANCE;
	}

}