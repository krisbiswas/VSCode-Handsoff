import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:unified_audio/core/values/enums.dart';
import 'package:unified_audio/model/device.dart';
import 'package:unified_audio/modules/multi_speaker/controller/create_multispeaker_controller.dart';

class CreateMultiSpeaker extends GetView<CreateMultiSpeakerController> {
  const CreateMultiSpeaker({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Create Multi-Speaker Setup'),
      ),
      body: Column(
        children: [
          Text('Select Devices to include in the Multi-Speaker Setup'),
          Expanded(
            child: _buildDeviceList(),
          )
        ],
      ),
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            ObxValue<RxBool>(
                (data) => ElevatedButton(
                    onPressed: () =>
                        controller.createSetup(MultiSpeakerConfigType.stereo),
                    child: const Text('Create Stereo')),
                controller.canStereo),
            ObxValue<RxBool>(
                (data) => ElevatedButton(
                    onPressed: () =>
                        controller.createSetup(MultiSpeakerConfigType.group),
                    child: const Text('Create Group')),
                controller.canGroup)
          ],
        ),
      ),
    );
  }

  Widget _buildDeviceList() {
    return ObxValue<RxList<Device>>(
        (data) => ListView.builder(
              itemCount: data.length,
              itemBuilder: (context, index) {
                final device = data[index];
                final isSelected = controller.selectedDevices.contains(device);
                return ListTile(
                  title: Text(device.name),
                  trailing: isSelected
                      ? const Icon(Icons.check_box)
                      : const Icon(Icons.check_box_outline_blank),
                  onTap: () => controller.select(device),
                );
              },
            ),
        controller.discoveredDevices);
  }
}
