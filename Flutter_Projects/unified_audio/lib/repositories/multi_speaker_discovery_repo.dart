import 'package:unified_audio/core/requests/msf_request_response.dart';
import 'package:unified_audio/core/values/enums.dart';
import 'package:unified_audio/repositories/ocf_repository.dart';

abstract class MultiSpeakerDiscoveryRequirement {
  Future<void> discoverSpeakers(String deviceId);
}

const _uri = 'groupDiscovery';

class MultiSpeakerDiscoveryRepo extends OcfRepository
    implements MultiSpeakerDiscoveryRequirement {
  @override
  Future<void> discoverSpeakers(String deviceId) async {
    final request = GetMultiSpeakerDiscoveryRequest();
    sendMessage(deviceId, request);
  }
}

class GetMultiSpeakerDiscoveryRequest extends MsfRequest {
  GetMultiSpeakerDiscoveryRequest()
      : super(
          uri: _uri,
          type: MSFRequestType.get,
        );
  static MultiSpeakerDiscoveryRequirement get instance =>
      MultiSpeakerDiscoveryRepo();
}
