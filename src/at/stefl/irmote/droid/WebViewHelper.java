package at.stefl.irmote.droid;

import java.io.IOException;

import android.util.Base64;
import android.webkit.WebView;

public class WebViewHelper {

	private final WebView webView;

	public WebViewHelper(WebView webView) {
		this.webView = webView;
	}

	public void executeScriptUrl(String script) {
		webView.loadUrl("javascript:(function() {" + script + "})()");
	}

	public void executeScript(String script, String charset)
			throws IOException {
		String encoded = Base64.encodeToString(script.getBytes(charset),
				Base64.NO_WRAP);
		StringBuilder startScript = new StringBuilder();
		startScript.append("var parent = document.getElementsByTagName('head').item(0);");
		startScript.append("var script = document.createElement('script');");
		startScript.append("script.type = 'text/javascript';");
		startScript.append("script.innerHTML = window.atob('");
		startScript.append(encoded);
		startScript.append("');");
		startScript.append("parent.appendChild(script);");
		executeScriptUrl(startScript.toString());
	}

}