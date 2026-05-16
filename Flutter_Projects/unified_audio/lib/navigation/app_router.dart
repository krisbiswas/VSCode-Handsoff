import 'package:get/get.dart';
import 'package:unified_audio/model/dashboard_card_entity.dart';
import 'package:unified_audio/modules/app_main/dashboard/dashboard_controller.dart';
import 'package:unified_audio/modules/app_main/device_control/device_control_controller.dart';
import 'package:unified_audio/modules/app_main/device_control/device_control_page.dart';
import 'package:unified_audio/modules/multi_speaker/create_multi_speaker.dart';
import 'package:unified_audio/repositories/ble_connection_repo.dart';
import '../modules/app_main/dashboard/dashboard.dart';
import '../modules/soundbar/home/soundbar_home.dart';
import '../modules/soundtower/home/sound_tower_home.dart';
import './routes.dart';

class AppRouter {
  static List<GetPage<dynamic>> pages = [
    GetPage<void>(
      name: AppRoutes.dashboard,
      page: () => Dashboard(),
      preventDuplicates: true,
      binding: BindingsBuilder(
        () => DashboardController(Get.find<BleConnectionRepo>()),
      ),
    ),
    GetPage<int>(
        name: AppRoutes.deviceControl,
        page: () => DeviceControlPage(),
        binding: BindingsBuilder<IDeviceControl>(
          () {
            final args = Get.arguments as Map<String, dynamic>;
            final device = args["device"] as DashboardCardEntity;
            Get.lazyPut<IDeviceControl>(
              () => DeviceControlController(device),
            );
          },
        )),
    GetPage<int>(
      name: AppRoutes.createMultiSpeaker,
      page: () => const CreateMultiSpeaker(),
    ),
    GetPage<int>(
      name: AppRoutes.soundbar,
      page: () => const SoundBarHome(),
    ),
    GetPage<int>(
      name: AppRoutes.soundTower,
      page: () => const SoundTowerHome(),
    ),
  ];
}
