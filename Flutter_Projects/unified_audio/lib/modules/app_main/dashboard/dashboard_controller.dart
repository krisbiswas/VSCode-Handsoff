import 'dart:async';

import 'package:get/get.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/model/device.dart';
import 'package:unified_audio/repositories/ble_connection_repo.dart';

class DashboardController extends GetxController {
  final BleConnectionRepo _bleConnectionRepo;

  StreamSubscription<Device>? _scanSubs;
  RxList<Device> discoveredDevices = <Device>[].obs;

  DashboardController(this._bleConnectionRepo) : super();

  void startBleScan() {
    _scanSubs = _bleConnectionRepo.startScan.listen((device) {
      Log.d('Discovered device: $device');
      discoveredDevices.value = [...discoveredDevices.where((d) => d.id != device.id), device];
    });
    // _scanSubs = _bleConnectionRepo.startScan.listen((devices) {
    //   Log.d('Discovered devices: ${devices}');
    //   discoveredDevices.value = devices;
    // });
  }

  void stopBleScan() {
    _scanSubs?.cancel();
  }
}
