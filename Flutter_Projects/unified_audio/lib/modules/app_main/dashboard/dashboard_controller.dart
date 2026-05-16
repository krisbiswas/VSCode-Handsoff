import 'dart:async';

import 'package:get/get.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/model/dashboard_card_entity.dart';
import 'package:unified_audio/model/device.dart';
import 'package:unified_audio/repositories/ble_connection_repo.dart';

class DashboardController extends GetxController {
  final BleConnectionRepo _bleConnectionRepo;

  StreamSubscription<Device>? _scanSubs;
  RxList<Device> discoveredDevices = <Device>[].obs;

  DashboardController(this._bleConnectionRepo) {
    devices.add(DashboardCardEntity(1, "Device 1"));
    devices.add(DashboardCardEntity(2, "Device 2"));
    devices.add(DashboardCardEntity(3, "Device 3"));
    devices.add(DashboardCardEntity(4, "Device 4"));
    devices.add(DashboardCardEntity(5, "Device 5"));
  }

  void startBleScan() {
    _scanSubs = _bleConnectionRepo.startScan.listen((device) {
      Log.d('Discovered device: $device');
      discoveredDevices.value = [...discoveredDevices.where((d) => d.id != device.id), device];
    });
  }

  void stopBleScan() {
    _scanSubs?.cancel();
  }

  // ////////
  RxList<DashboardCardEntity> devices = <DashboardCardEntity>[].obs;
}
