import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:unified_audio/modules/app_main/dashboard/dashboard_controller.dart';

class Dashboard extends GetView<DashboardController> {
  const Dashboard({super.key});

  @override
  Widget build(BuildContext context) {
    controller.startBleScan();
    return Scaffold(
      appBar: AppBar(title: const Text("Dashboard")),
      body: Center(
        child: Obx(() {
          return GridView.builder(
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
            ),
            itemBuilder: (context, index) {
              return ListTile(
                title: Text(controller.discoveredDevices[index].name),
                subtitle: Text(controller.discoveredDevices[index].id),
                onTap: () {},
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                  side: const BorderSide(color: Colors.blueAccent),
                ),
                contentPadding: const EdgeInsetsDirectional.symmetric(
                    horizontal: 16.0, vertical: 8.0),
              );
            },
            itemCount: controller.discoveredDevices.length,
          );
        }),
      ),
    );
  }
}
