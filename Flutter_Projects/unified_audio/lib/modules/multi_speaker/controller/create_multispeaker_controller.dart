import 'package:get/get.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/core/values/enums.dart';
import 'package:unified_audio/model/device.dart';
import 'package:unified_audio/modules/multi_speaker/service/multi_speaker_service.dart';

class CreateMultiSpeakerController extends GetxController {
  final Device hostDevice;
  final RxList<Device> discoveredDevices = <Device>[].obs;
  final RxList<Device> existingMultiSpeakers = <Device>[].obs;
  final RxList<Device> selectedDevices = <Device>[].obs;
  final RxBool canGroup = false.obs;
  final RxBool canStereo = false.obs;

  final MultiSpeakerConfigService multiSpeakerConfigService;
  final MultiSpeakerDiscoveryService multiSpeakerDiscoveryService;
  final MultiSpeakerInfoService multiSpeakerInfoService;

  CreateMultiSpeakerController(
      this.hostDevice, this.multiSpeakerConfigService, this.multiSpeakerDiscoveryService, this.multiSpeakerInfoService);
  @override
  onInit() {
    super.onInit();
    selectedDevices.add(hostDevice);
    discoverDevices();
    // TODO: from host device identify existing multi speaker setups
  }

  discoverDevices() {
    // Logic to discover available speakers
    multiSpeakerDiscoveryService.discoverDevices(hostDevice.id);
  }

  select(Device device) {
    if (selectedDevices.contains(device)) {
      selectedDevices.remove(device);
    } else {
      selectedDevices.add(device);
    }
    checkCanGroupAndStereo();
  }

  createSetup(MultiSpeakerConfigType configType) {
    final deviceIds = selectedDevices.map((d) => d.id).toList();
    multiSpeakerConfigService.createMultiSpeaker(deviceIds, configType);
    // TODO : on Success update existingMultiSpeakers list
  }

  void checkCanGroupAndStereo() {
    if (selectedDevices.length < 2) {
      canGroup.value = false;
      canStereo.value = false;
      Log.d("Cannot form Group/Stereo with less than 2 devices");
      return;
    } else if (selectedDevices.length == 2) {
      canGroup.value = false;
      // TODO: check if two devices can form stereo
    } else {
      canStereo.value = false;
      // More than 2 devices cannot form stereo
      // TODO: check if multiple devices can form group
      for (var device in selectedDevices) {
        // TODO:
        // if device models are same
        // canGroup.add(device.canGroup);
        // canStereo.add(device.canStereo);
      }
    }
  }
}
