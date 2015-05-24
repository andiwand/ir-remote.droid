package at.stefl.irmote.droid;

import com.google.gson.Gson;

public class GsonHelper {

	public static Gson getGson() {
		return new Gson();
	}

	private GsonHelper() {
	}

}