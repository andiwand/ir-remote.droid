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

	private static class StationBox {
		private final Station station;

		public StationBox(Station station) {
			this.station = station;
		}

		@JavascriptInterface
		public String getName() {
			return station.getName();
		}

		@JavascriptInterface
		public String getAddress() {
			return station.getAddress().getHostAddress();
		}

		@JavascriptInterface
		public int getPort() {
			return station.getPort();
		}
	}
	
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
		}

		return null;
	}
	
	@JavascriptInterface
	public void send(String station, String raw) {
		try {
			System.out.println(station);
			System.out.println(raw);
			Gson gson = GsonHelper.getGson();
			Station s = gson.fromJson(station, Station.class);
			RawFrame r = gson.fromJson(raw, RawFrame.class);
			System.out.println(s);
			System.out.println(r);
			remote.setStation(s);
			remote.sendRaw(r);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: report
		}
	}

}