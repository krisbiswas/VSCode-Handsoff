import 'dart:collection';

import 'package:group_impl/src/data/btdevice.dart';

class GroupCubitState {
  bool isEnabled;
  String masterAddress;
  // Use List instead of HashSet to maintain order and item can be replaced in list with new updated BTDevice
  List<BTDevice> scanlist;
  HashSet<BTDevice> selectedDevices;
  HashSet<BTDevice> groupedDevices;

  GroupCubitState({this.isEnabled=false, required this.masterAddress, required this.scanlist, required this.selectedDevices, required this.groupedDevices});

  static init(){
    return GroupCubitState(masterAddress: '', scanlist: [], selectedDevices: HashSet(), groupedDevices: HashSet());
  }

  GroupCubitState copyWith({
    bool? isEnabled,
    String? masterAddress,
    List<BTDevice>? scanlist,
    HashSet<BTDevice>? selectedDevices,
    HashSet<BTDevice>? groupedDevices,
  }){
    return GroupCubitState(
      isEnabled: isEnabled ?? this.isEnabled,
      masterAddress: masterAddress ?? this.masterAddress, 
      scanlist: scanlist ?? this.scanlist, 
      selectedDevices: selectedDevices ?? this.selectedDevices, 
      groupedDevices: groupedDevices ?? this.groupedDevices
    );
  }
}