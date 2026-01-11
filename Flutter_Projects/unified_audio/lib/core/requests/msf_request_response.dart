import 'package:unified_audio/core/values/enums.dart';

class MsfRequest {
  final String event; // m2s.av.{uri}
  final String type; // get/set.{uri}
  final int seqId;
  final Map<String, dynamic> data;

  MsfRequest({
    required String uri,
    required MSFRequestType type,
    this.data = const {},
  })  : event = 'm2s.av.$uri',
        type = '${type.value}.$uri',
        seqId = DateTime.now().millisecondsSinceEpoch;

  Map<String, dynamic> toJson() {
    return {
      'event': event,
      'type': type,
      'seqId': seqId,
      'data': data,
    };
  }

  @override
  String toString() {
    return 'MsfRequest(event: $event, type: $type, seqId: $seqId, data: $data)';
  }
}

class MsfResponse {
  final String event; // s2m.av.{uri}
  final String type; // notify.{uri}
  final int seqId;
  final Map<String, dynamic> data;

  MsfResponse({
    required this.event,
    required this.type,
    required this.seqId,
    required this.data,
  });

  factory MsfResponse.fromJson(Map<String, dynamic> json) {
    return MsfResponse(
      event: json['event'],
      type: json['type'],
      seqId: json['seqId'],
      data: Map<String, dynamic>.from(json['data'] ?? {}),
    );
  }
}
