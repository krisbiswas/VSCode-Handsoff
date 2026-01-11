import 'package:flutter_reactive_ble/flutter_reactive_ble.dart';

class BleDatasource {
  final ble = FlutterReactiveBle();

  Stream<DiscoveredDevice> get startScan =>
      ble.scanForDevices(withServices: []);

  // final FlutterBluePlus _flutterBlue = FlutterBluePlus();

  // Stream<List<ScanResult>> startScan() {
  //   FlutterBluePlus.startScan();
  //   return FlutterBluePlus.onScanResults;
  // }

  // void stopScan() {
  //   FlutterBluePlus.stopScan();
  // }
}
