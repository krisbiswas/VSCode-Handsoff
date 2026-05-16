import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:unified_audio/core/base_ui_components/base_page.dart';
import 'package:unified_audio/core/localisation/locale_keys.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/modules/app_main/dashboard/device_status_widget.dart';
import 'package:unified_audio/modules/app_main/device_control/device_control_controller.dart';

class DeviceControlPage extends BasePage<IDeviceControl> {
  DeviceControlPage({super.key});

  @override
  Widget build(BuildContext context) {
    contents = Scaffold(
      appBar: AppBar(
        title: Text(controller.deviceName),
        actions: [
          PopupMenuButton(
            itemBuilder: (context) {
              return [
                PopupMenuItem(child: Text(LocaleKeys.SETTINGS.name.tr)),
                PopupMenuItem(child: Text(LocaleKeys.INFORMATION.name.tr))
              ];
            },
          )
        ],
      ),
      body: body(),
    );
    return super.build(context);
  }

  Widget body() {
    return Stack(
      children: [
        SingleChildScrollView(
          child: Column(
            children: [
              DeviceStatusWidget(controller),
              Card(
                child: ListTile(
                  contentPadding: const EdgeInsets.all(10),
                  title: Text(LocaleKeys.SOUND_SOURCE.name.tr),
                  subtitle: const Text("BT"),
                ),
              ),
              Card(
                child: ListTile(
                  contentPadding: const EdgeInsets.all(10),
                  title: Text(LocaleKeys.AMBIENT_SOUND.name.tr),
                  subtitle: Text(LocaleKeys.OFF.name.tr),
                ),
              ),
              Card(
                child: ListTile(
                  contentPadding: const EdgeInsets.all(10),
                  title: Text(LocaleKeys.SPACE_FIT.name.tr),
                  subtitle: Text(LocaleKeys.OFF.name.tr),
                ),
              ),
            ],
          ),
        ),
        _buildQuickChange()
      ],
    );
  }

  Widget _buildQuickChange() {
    return ObxValue<RxBool>((isChanging) {
      if (!isChanging.value) return const SizedBox.shrink();
      return Container(
          width: double.infinity,
          color: Colors.black54,
          child: CarouselSlider.builder(
              itemCount: controller.devices.length,
              itemBuilder: (context, index, realIndex) {
                final item = controller.devices[index];
                return GestureDetector(
                  onTap: () {
                    controller.changeDevices(false);
                    controller.swapDevice(index);
                  },
                  child: Container(
                    // height: 100,
                    color: Colors.blueAccent,
                    alignment: AlignmentDirectional.center,
                    child: Text(item.name),
                  ),
                );
              },
              options: CarouselOptions(
                height: 100,
                enableInfiniteScroll: false,
                enlargeCenterPage: true,
                onPageChanged: (index, reason) {
                  Log.d("index: $index");
                },
              )));
    }, controller.isChangingDevice);
  }
}
