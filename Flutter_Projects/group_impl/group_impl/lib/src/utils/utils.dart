
import 'dart:collection';

import 'package:group_impl/src/data/btdevice.dart';

enum MultiSpeakerState{
  normal, groupSlave, groupMaster, phoneConnected, unkonwn;
}

void replaceDeviceInSet(HashSet<BTDevice> set, BTDevice toRemoveDevice, BTDevice replaceWith) {
  set.remove(toRemoveDevice);
  set.add(replaceWith);
}