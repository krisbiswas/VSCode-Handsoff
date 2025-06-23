enum ApiStatus {
  request, success, failure;
}

enum ApiType {
  scanDevices("scanDevices"),
  connectSlave("connectSlave"),
  disconnectSlave("disconnectSlave"),
  writeToSlave("writeToSlave"),
  undefined("undefined");

  final String val;
  const ApiType(this.val);
  static ApiType from(String val){
    return ApiType.values.firstWhere((type)=> type.val == val, orElse: ()=>ApiType.undefined);
  }
}