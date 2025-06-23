import 'dart:typed_data';

import 'package:group_impl/src/utils/utils.dart';

class BTDevice {
  String name;
  String address;
  Uint8List? adv;
  String advAddress = "";
  ConnectStatus connectStatus = ConnectStatus.unkonwn;
  bool isPaired = false;
  MultiSpeakerState multiSpeakerState = MultiSpeakerState.unkonwn;

  BTDevice({required this.name, required this.address, this.adv});

  @override
  bool operator == (Object other) {
    if(other is! BTDevice) return false;
    if(other.address == address) return true;
    return false;
  }

  @override
  int get hashCode => address.hashCode;
}

enum ConnectStatus{
  unkonwn, connecting, connnected, disconnecting, disconnected;
}