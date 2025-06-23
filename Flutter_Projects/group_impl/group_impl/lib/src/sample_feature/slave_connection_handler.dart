import 'dart:collection';
import 'dart:typed_data';

import 'package:group_impl/src/data/btdevice.dart';
import 'package:group_impl/src/enums/api.dart';

class SlaveConnectionHandler {
  static String tag = "SlaveConnectionHandler";

  static final SlaveConnectionHandler _instance = SlaveConnectionHandler._init();
  static _init(){
    return SlaveConnectionHandler();
  }

  factory SlaveConnectionHandler(){
    return _instance;
  }

  Map<String, bool> isSlaveInMode = {};
  bool isBusy = false;
  late String master;
  late HashSet<BTDevice> _toGroup;
  late HashSet<BTDevice> _toUngroup;
  late Queue<BTDevice> queue;
  late Function(BTDevice device, bool isDone, bool toJoin) onSlaveStatusChangeListener;

  bool init(String masterAddress, 
    HashSet<BTDevice> toGroup, HashSet<BTDevice> toUngroup, 
    Function(BTDevice device, bool isDone, bool toJoin) onSlaveStatusChangeListener){
      if(isBusy){
        return false;
      }
      master = masterAddress;
      _toGroup = toGroup;
      _toUngroup = toUngroup;
      this.onSlaveStatusChangeListener = onSlaveStatusChangeListener;
      queue = Queue.from(toGroup);
      queue.addAll(toUngroup);
      start(queue.removeFirst());
      return true;
  }

  void start(BTDevice removeFirst) {
    // Connectslave
  }

  onNotification(ApiType type, ApiStatus status, dynamic data){
    switch(type){
      case ApiType.connectSlave:
        BTDevice slave = data;
        onSlaveConnected(slave);
        break;
      case ApiType.writeToSlave:
        String address = data.first;
        bool isModeChange = isModeChanged(data.elementAt(1) as Uint8List);
        isSlaveInMode[address] = isModeChange;
        // disconnectSlave(address);
        break;
      case ApiType.disconnectSlave:
        onSlaveDisconnected(data as String);
        break;
      default:
    }
  }
  
  onSlaveConnected(BTDevice slave){

  }

  onSlaveWrite(){

  }

  onSlaveDisconnected(String slaveAddress){
    if(!(isSlaveInMode[slaveAddress] ?? false)){
      
    }
  }
  
  bool isModeChanged(Uint8List data) {
    return (data[0] == 0x41 && data[1] == 0x54 && data[2] == 0x00);
  }
  
}