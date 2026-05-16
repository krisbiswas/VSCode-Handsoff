import 'package:flutter/material.dart';
import 'package:unified_audio/navigation/app_router.dart';
import 'package:unified_audio/navigation/routes.dart';
import 'package:unified_audio/themes/app_theme.dart';

class SoundTowerApp extends StatelessWidget {
  const SoundTowerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Unified Audio',
      theme: AppTheme.materialTheme,
      darkTheme: AppTheme.customTheme,
      initialRoute: AppRoutes.soundTower,
    );
  }
}
