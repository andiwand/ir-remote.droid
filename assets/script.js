var console = Android.getConsole();
var remote = Android.getRemote();

IR.__impl = IR.__impl || {};

IR.__impl.station_discover = function(port, timeout, maxPacketSize) {
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

IR.__impl.station_receive = function(station) {
  var s = JSON.stringify(station.serialize());
  var raw = remote.receive(s);
  return IR.RawFrame.deserialize(raw);
};
