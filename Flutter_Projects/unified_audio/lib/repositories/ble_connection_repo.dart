import 'package:unified_audio/datasources/ble_datasource.dart';
import 'package:unified_audio/model/device.dart';

class BleConnectionRepo {
  final BleDatasource _datasource;
  BleConnectionRepo(this._datasource);

  Stream<Device> get startScan => _datasource.startScan.map((dataDevice) {
        return Device(name: dataDevice.name, id: dataDevice.id, addr: dataDevice.id, adv: dataDevice.manufacturerData);
      });

  // Stream<List<Device>> get startScan =>
  // _datasource.startScan().map((scanResults) {
  //   return scanResults.map((result) {
  //     Log.d(result.advertisementData.manufacturerData);
  //     return Device(
  //       id: result.device.remoteId.toString(),
  //       name: result.device.advName.isNotEmpty
  //           ? result.device.advName
  //           : result.device.platformName,
  //       // rssi: result.rssi,
  //       addr: result.device.remoteId.toString(),
  //       adv: Uint8List.fromList(
  //           result.advertisementData.manufacturerData[0] ?? []),
  //     );
  //   }).toList();
  // });
}
