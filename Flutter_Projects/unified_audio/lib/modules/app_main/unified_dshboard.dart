import 'package:flutter/material.dart';
import 'package:unified_audio/navigation/app_router.dart';
import 'package:unified_audio/themes/app_theme.dart';

class UnifiedAudioDasboard extends StatelessWidget {
  const UnifiedAudioDasboard({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Unified Audio',
      theme: AppTheme.materialTheme,
      darkTheme: AppTheme.customTheme,
      initialRoute: AppRouter.dashboard,
      onGenerateRoute: AppRouter.generateRoute,
    );
  }
}
