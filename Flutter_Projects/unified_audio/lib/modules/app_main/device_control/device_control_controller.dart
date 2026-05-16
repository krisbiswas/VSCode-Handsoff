import 'package:get/get.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/model/dashboard_card_entity.dart';

abstract class IDeviceControl {
  String get deviceName;
  RxList<DashboardCardEntity> get devices;
  RxBool get isChangingDevice;
  void changeDevices(bool state);
  void swapDevice(int index);
}

class DeviceControlController extends GetxController implements IDeviceControl {
  @override
  String get deviceName => currentDevice.name;

  DeviceControlController(this.currentDevice);

  @override
  RxList<DashboardCardEntity> devices = <DashboardCardEntity>[].obs;
  @override
  RxBool isChangingDevice = false.obs;

  DashboardCardEntity currentDevice;

  @override
  void changeDevices(bool state) {
    isChangingDevice.value = state;
  }

  @override
  void swapDevice(int index) {
    Log.d("Selected device for swapping = ${devices[index]}");
  }
}
