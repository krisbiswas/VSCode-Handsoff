import 'package:flutter/material.dart';
import 'package:unified_audio/navigation/routes.dart';
import 'package:unified_audio/themes/app_theme.dart';

class SoundbarApp extends StatelessWidget {
  const SoundbarApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Soundbar Unified Audio',
      theme: AppTheme.materialTheme,
      darkTheme: AppTheme.customTheme,
      initialRoute: AppRoutes.soundbar,
    );
  }
}
