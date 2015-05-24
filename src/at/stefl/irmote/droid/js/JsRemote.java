package at.stefl.irmote.droid.js;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Set;

import android.webkit.JavascriptInterface;
import at.stefl.irmote.droid.GsonHelper;
import at.stefl.irmote.java.Discovery;
import at.stefl.irmote.java.Remote;
import at.stefl.irmote.java.Station;
import at.stefl.irmote.java.frame.RawFrame;

import com.google.gson.Gson;

public class JsRemote {

	public static final JsRemote INSTANCE = new JsRemote();

	private final Remote remote = new Remote();

	private JsRemote() {
	}

	@JavascriptInterface
	public String discover(int port, int timeout, int maxPacketSize) {
		try {
			Set<Station> stations = Discovery.discover(port, maxPacketSize,
					timeout);
			stations.add(new Station("test", InetAddress.getLocalHost(), 1234));
			return GsonHelper.getGson().toJson(stations);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: report
			return null;
		}
	}

	@JavascriptInterface
	public void send(String station, String raw) {
		try {
			Gson gson = GsonHelper.getGson();
			Station s = gson.fromJson(station, Station.class);
			RawFrame r = gson.fromJson(raw, RawFrame.class);
			remote.setStation(s);
			remote.sendRaw(r);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: report
		}
	}

	@JavascriptInterface
	public String receive(String station) {
		try {
			Gson gson = GsonHelper.getGson();
			Station s = gson.fromJson(station, Station.class);
			remote.setStation(s);
			RawFrame raw = remote.receiveRaw();
			return gson.toJson(raw);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: report
			return null;
		}
	}

}