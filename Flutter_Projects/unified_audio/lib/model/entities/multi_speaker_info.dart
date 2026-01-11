class MultiSpeakerInfoEntity {
  final String model;
  final String name;
  final List<String> deviceIds;
  final String configType; // e.g., "stereo", "group"

  MultiSpeakerInfoEntity({
    required this.model,
    required this.name,
    required this.deviceIds,
    required this.configType,
  });
}

class SlaveDeviceInfo {
  final String id;
  final String role; // e.g., "left", "right", "member"

  SlaveDeviceInfo({
    required this.id,
    required this.role,
  });
}
