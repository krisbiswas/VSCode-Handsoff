import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/core/values/enums.dart';
import 'package:unified_audio/model/device.dart';

sealed class MultiSpeakerConfigService {
  createMultiSpeaker(List<String> deviceIds, MultiSpeakerConfigType configType);
}

sealed class MultiSpeakerDiscoveryService {
  Future<List<Device>> discoverDevices(String deviceId);
}

sealed class MultiSpeakerInfoService {
  getMultiSpeakerInfo(String deviceId);
}

class MultiSpeakerService implements MultiSpeakerConfigService, MultiSpeakerDiscoveryService, MultiSpeakerInfoService {
  @override
  createMultiSpeaker(List<String> deviceIds, MultiSpeakerConfigType configType) {
    Log.d('Creating multi-speaker setup with devices: $deviceIds and config type: $configType');
  }

  @override
  Future<List<Device>> discoverDevices(String deviceId) async {
    Log.d('Discovering devices for device ID: $deviceId');
    return Future.delayed(const Duration(seconds: 2), () {
      return [
        Device(
          name: "dummy1",
          id: "id1",
          addr: "addr1",
        ),
        Device(
          name: "dummy2",
          id: "id2",
          addr: "addr2",
        ),
        Device(
          name: "dummy3",
          id: "id3",
          addr: "addr3",
        ),
        Device(
          name: "dummy4",
          id: "id4",
          addr: "addr4",
        ),
        Device(
          name: "dummy5",
          id: "id5",
          addr: "addr5",
        ),
      ];
    });
  }

  @override
  getMultiSpeakerInfo(String deviceId) {
    Log.d('Getting multi-speaker info for device ID: $deviceId');
  }
}
