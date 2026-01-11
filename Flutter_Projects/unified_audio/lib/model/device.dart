import 'dart:typed_data';

class Device {
  String name;
  String id;
  String addr;
  Uint8List? adv;

  Device({required this.name, required this.id, required this.addr, this.adv});
}
