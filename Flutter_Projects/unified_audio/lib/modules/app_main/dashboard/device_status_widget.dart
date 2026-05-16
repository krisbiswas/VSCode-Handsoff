import 'package:flutter/material.dart';
import 'package:unified_audio/modules/app_main/device_control/device_control_controller.dart';

class DeviceStatusWidget extends StatelessWidget {
  final IDeviceControl controller;
  const DeviceStatusWidget(this.controller, {super.key});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onLongPress: () => controller.changeDevices(true),
      child: Container(
        width: double.infinity,
        alignment: Alignment.center,
        child: const Icon(
          Icons.devices,
          size: 100,
        ),
      ),
    );
  }
}
