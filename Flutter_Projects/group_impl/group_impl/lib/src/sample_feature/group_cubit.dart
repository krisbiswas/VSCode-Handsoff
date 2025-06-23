import 'dart:collection';
import 'dart:typed_data';

import 'package:bloc/bloc.dart';
import 'package:group_impl/src/data/btdevice.dart';
import 'package:group_impl/src/enums/api.dart';
import 'package:group_impl/src/sample_feature/group_cubit_state.dart';
import 'package:group_impl/src/sample_feature/slave_connection_handler.dart';
import 'package:group_impl/src/utils/utils.dart';

class GroupCubit extends Cubit<GroupCubitState> {

  final String TAG = "GroupCubit";
  HashSet<BTDevice> devicesToUngroup = HashSet();

  GroupCubit(): super(GroupCubitState.init());

  listenEvents(event){
    if(event is String){
      _handleEvents(event);
    } else {
      _handleCommands(event as Uint8List);
    }
  }

  _handleEvents(event){
    ApiType type = ApiType.from(event.type);
    switch(type){
      case ApiType.scanDevices:
        if(event.data is int){
          // emit(state.copyWith(isScanning: ));
        } else {
          _onScanDeviceReceived(event.data);
        }
        break;
      case ApiType.connectSlave:
      case ApiType.disconnectSlave:
      case ApiType.writeToSlave:
        SlaveConnectionHandler().onNotification(type, ApiStatus.success, event.data);
        break;
      default:
    }
  }

  _handleCommands(Uint8List event){
    switch(event[0]) {
      case 0x06:// MULTI_SPEAKER_STATUS_INFO
        _handleMultiSpeakerCommand(event);
        break;
      case 0xF9:// GROUP_STEREO_COMPLETE_INFO
        _handleGroupStereoCompletion(event);
        break;
    }
  }

  _handleMultiSpeakerCommand(Uint8List event){
    emit(state.copyWith(isEnabled: event[1] == 0x02));
  }

  _handleGroupStereoCompletion(Uint8List event){
    // TODO Now, scan devices and update the devices from ADV.
    HashSet<BTDevice> groupedDevices = state.groupedDevices;
    HashSet<BTDevice> selectedDevices = state.selectedDevices;
    if(event[1] == 0x01){
      // Success received from HOST
      // Assumption that all the selectedDevices are grouped, until the device found while scanning with correct status
      groupedDevices = state.selectedDevices;
    } else {
      // Since failure, reverting the selectedDevices to last groupedDevices.
      selectedDevices = state.groupedDevices;
    }
    devicesToUngroup.clear();
    // emit(state.copyWith(waitForGroupFormation: false, selectedDevices: selectedDevices, groupedDevices: groupedDevices));
  }

/////////////////////////////////////////////////
/////////   Scanned Device processing   /////////
/////////////////////////////////////////////////
  _onScanDeviceReceived(BTDevice device){
    if(!isValidGroupDeviceState(device)){
      return ;
    }
    var (selected, grouped, isGroupableToThisHost) = getUpdatedGroupedAndSelectedList(device);
    // update scanlist
    List<BTDevice> scanlist = isGroupableToThisHost ? getUpdatedScanList(device) : state.scanlist;
    emit(state.copyWith(scanlist: scanlist, selectedDevices: selected, groupedDevices: grouped));
  }

  bool isValidGroupDeviceState(BTDevice device){
    // device found is NOT master itself and device state is normal | groupSlave
    return device.address != state.masterAddress &&
    (device.multiSpeakerState == MultiSpeakerState.normal || 
    device.multiSpeakerState == MultiSpeakerState.groupSlave);
  }

  List<BTDevice> getUpdatedScanList(BTDevice device) {
    List<BTDevice> scanlist = List.from(state.scanlist);
    int deviceIndex = scanlist.indexOf(device);
    if(deviceIndex != -1){
      scanlist[deviceIndex] = device;
    } else {
      scanlist.add(device);
    }
    return scanlist;
  }

  (HashSet<BTDevice>, HashSet<BTDevice>, bool) getUpdatedGroupedAndSelectedList(BTDevice device){
    HashSet<BTDevice> selected = HashSet.from(state.selectedDevices);
    HashSet<BTDevice> grouped = HashSet.from(state.groupedDevices);
    BTDevice? foundDevice = grouped.lookup(device);
    bool isGroupableToThisHost = false;

    // if device is grouped to this HOST and not present in groupedList, then add to groupedList and selectedList
    // if device is slave but not this HOST, remove from groupedList and selectedList
    if(device.multiSpeakerState == MultiSpeakerState.groupSlave && device.advAddress == state.masterAddress){
      // device is groupedSlave to this HOST
      isGroupableToThisHost = true;
      if(foundDevice == null){
        grouped.add(device);
        selected.add(device);
      } else {
        replaceDeviceInSet(grouped, foundDevice, device);
        // if updated the selected: everytime user unselect the device, it will select itself again and again whenever device is discovered
        // replaceDeviceInSet(selected, foundDevice, device);
      }
    } else if(device.multiSpeakerState == MultiSpeakerState.normal){
      // device is normal and available for grouping
      isGroupableToThisHost = true;
      if(foundDevice != null){
        grouped.remove(foundDevice);
        selected.remove(foundDevice);
      }
    } else {
      // device is groupedSlave to other HOST: no use
      isGroupableToThisHost = false;
    }
    return (selected, grouped, isGroupableToThisHost);
  }

/////////////////////////////////////////////
/////////   Slave Status Handling   /////////
/////////////////////////////////////////////

  onSlaveStatusChangeListener(BTDevice device, bool isDone, bool toJoin){
    int deviceIndex = state.scanlist.indexOf(device);
    BTDevice d = state.scanlist[deviceIndex];

    HashSet<BTDevice> groupedDevices = HashSet.from(state.groupedDevices);
    HashSet<BTDevice> selectedDevices = HashSet.from(state.selectedDevices);
    if(isDone){
      // AT cmd success
      // TODO remove temporary code
      if(toJoin){
        groupedDevices.add(d);
      } else {
        groupedDevices.remove(d);
        devicesToUngroup.remove(d);// unnecessory
      }
      // temporary code
    } else {
      // AT cmd failed/BLE connection unsuccessful
      selectedDevices.remove(d);
      devicesToUngroup.remove(d);
    }
    emit(state.copyWith(selectedDevices: selectedDevices, groupedDevices: groupedDevices));
  }

//////////////////////////////////
/////////   API for UI   /////////
//////////////////////////////////
  selectDevice(BTDevice device, bool isSelected) {
    isSelected? state.selectedDevices.add(device) : state.selectedDevices.remove(device);
    isSelected? devicesToUngroup.remove(device) : devicesToUngroup.add(device);
    emit(state.copyWith(selectedDevices: state.selectedDevices));
  }

  apply(){
    for(BTDevice device in devicesToUngroup){
      if(!state.groupedDevices.contains(device)){
        devicesToUngroup.remove(device);
      }
    }
    SlaveConnectionHandler().init("", HashSet(), HashSet(), onSlaveStatusChangeListener);
    // emit(state.copyWith(waitForGroupFormation: true));
  }
}