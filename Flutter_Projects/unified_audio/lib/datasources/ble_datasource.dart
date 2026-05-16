class BleDatasource {
  // final ble = FlutterReactiveBle();

  // Stream<DiscoveredDevice> get startScan =>
  //     ble.scanForDevices(withServices: []);

  // final FlutterBluePlus _flutterBlue = FlutterBluePlus();

  Stream<dynamic> startScan() {
    // FlutterBluePlus.startScan();
    // return FlutterBluePlus.onScanResults;
    return const Stream.empty();
  }

  // void stopScan() {
  //   FlutterBluePlus.stopScan();
  // }
}
