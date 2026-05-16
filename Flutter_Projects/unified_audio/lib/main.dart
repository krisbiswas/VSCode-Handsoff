import 'package:flutter/material.dart';
import 'package:unified_audio/core/localisation/app_translations.dart';
import 'package:unified_audio/di/injector.dart';
import 'package:unified_audio/modules/app_main/unified_dashboard.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await AppTranslations.init();
  initDependency();
  runApp(const UnifiedAudioDashboard());
}
