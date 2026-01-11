import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/core/requests/msf_request_response.dart';

abstract class IRepository {
  Stream<T> onserve<T>(String deviceId, Stream<T> Function() entiotyCreator);
  dispose();
}

class OcfRepository implements IRepository {
  void sendMessage(String deviceId, MsfRequest request) {
    // Implementation of sendMessage method
    Log.d('Sending message to $deviceId: $request');
  }

  @override
  Stream<T> onserve<T>(String deviceId, Stream<T> Function() entityCreator) {
    return entityCreator();
  }

  @override
  void dispose() {
    // Implementation of dispose method
  }
}
