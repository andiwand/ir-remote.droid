package at.stefl.irmote.droid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.content.Context;
import at.stefl.commons.io.CharStreamUtil;

public class Util {

	public static String readAsset(Context context, String path, String charset)
			throws IOException {
		InputStream json = context.getAssets().open(path);
		Reader in = new InputStreamReader(json, charset);
		String result = CharStreamUtil.readString(in);
		in.close();
		return result;
	}

	private Util() {
	}

}
