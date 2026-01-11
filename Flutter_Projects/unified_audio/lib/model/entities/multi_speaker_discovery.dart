class MultiSpeakerDiscoveryEntity {
  final List<CandidateDevice> devices;

  MultiSpeakerDiscoveryEntity(this.devices);
}

class CandidateDevice {
  final String id;
  final String model;

  CandidateDevice({
    required this.id,
    required this.model,
  });
}
