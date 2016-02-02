//var console = Android.getConsole();
//var remote = Android.getRemote();

$(function() {
  IR.interface = IR.interface || {};
  
  IR.interface.discover = function(port, timeout, maxPacketSize) {
    var stations = remote.discover(port, timeout, maxPacketSize);
    stations = JSON.parse(stations);
    var result = [];
    for (var i = 0; i < stations.length; i++) {
      var station = IR.Station.deserialize(stations[i]);
      result.push(station);
    }
    return result;
  };
  
  IR.interface.send = function(station, raw) {
    var s = JSON.stringify(station.serialize());
    var r = JSON.stringify(raw.serialize());
    remote.send(s, r);
  };
  
  IR.interface.receive = function(station) {
    var s = JSON.stringify(station.serialize());
    var raw = remote.receive(s);
    return IR.RawFrame.deserialize(raw);
  };
  
  IR.interface.configure = function(station, name, ssid, password) {
    
  };
  
  IR.interface.load = function(callback) {
    callback({});
  };
  
  IR.interface.save = function(config) {
    
  };
  
  IR.remote.init();
});
