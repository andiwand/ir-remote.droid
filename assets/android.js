var console = Android.getConsole();
var remote = Android.getRemote();

IR.__impl = IR.__impl || {};

IR.__impl.station_discovery = function(port, timeout, maxPacketSize) {
  var stations = remote.discover(port, timeout, maxPacketSize);
  stations = JSON.parse(stations);
  var result = [];
  for (var i = 0; i < stations.length; i++) {
    var station = IR.Station.deserialize(stations[i]);
    result.push(station);
  }
  return result;
};

IR.__impl.station_send = function(station, raw) {
  var s = JSON.stringify(station.serialize());
  var r = JSON.stringify(raw.serialize());
  remote.send(s, r);
};

var stations = IR.__impl.station_discovery(8888, 2000, 50);
var nec = new IR.NecFrame(1234);
var raw = nec.encode(IR.default.error_settings);
IR.__impl.station_send(stations[0], raw);
