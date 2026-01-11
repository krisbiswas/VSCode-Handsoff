import 'package:flutter/material.dart';
import 'package:unified_audio/di/injector.dart';
import 'package:unified_audio/modules/app_main/unified_dshboard.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  initDependency();
  runApp(const UnifiedAudioDasboard());
}
