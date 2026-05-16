import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:unified_audio/core/base_ui_components/base_page.dart';
import 'package:unified_audio/core/localisation/locale_keys.dart';
import 'package:unified_audio/modules/app_main/dashboard/dashboard_controller.dart';
import 'package:unified_audio/navigation/routes.dart';

class Dashboard extends BasePage<DashboardController> {
  Dashboard({super.key});

  @override
  Widget build(BuildContext context) {
    contents = Scaffold(
      appBar: AppBar(title: Text(LocaleKeys.DASHBOARD.name.tr)),
      body: ui1(),
    );
    return super.build(context);
  }

  ui1() {
    return GridView.builder(
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
      ),
      itemBuilder: (context, index) {
        return ListTile(
          title: Text(controller.devices[index].name),
          subtitle: Text(controller.devices[index].id.toString()),
          onTap: () {
            Get.toNamed(AppRoutes.deviceControl, arguments: {"device": controller.devices[index]});
          },
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8.0),
            side: const BorderSide(color: Colors.blueAccent),
          ),
          contentPadding: const EdgeInsetsDirectional.symmetric(horizontal: 16.0, vertical: 8.0),
        );
      },
      itemCount: controller.devices.length,
    );
  }
}
