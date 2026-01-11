import 'package:get/get.dart';
import 'package:unified_audio/datasources/ble_datasource.dart';
import 'package:unified_audio/modules/app_main/dashboard/dashboard_controller.dart';
import 'package:unified_audio/repositories/ble_connection_repo.dart';

initDependency() {
  Get.lazyPut<BleDatasource>(() {
    return BleDatasource();
  });
  Get.lazyPut<BleConnectionRepo>(() {
    return BleConnectionRepo(Get.find<BleDatasource>());
  });
  Get.lazyPut<DashboardController>(() {
    return DashboardController(Get.find<BleConnectionRepo>());
  });
}
