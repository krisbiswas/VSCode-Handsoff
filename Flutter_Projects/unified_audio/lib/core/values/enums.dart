enum MSFRequestType {
  get('get'),
  set('set');

  final String value;
  const MSFRequestType(this.value);
}

enum MSFUris {
  multiSpeakerDiscovery('groupDiscovery'),
  multiSpeakerConf('groupConf'),
  multiSpeakerInfo('groupInfo');

  final String value;
  const MSFUris(this.value);
}

enum MultiSpeakerConfigType {
  single,
  group,
  stereo;
}
