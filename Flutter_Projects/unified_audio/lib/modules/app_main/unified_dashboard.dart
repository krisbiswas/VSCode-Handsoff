import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:get/get.dart';
import 'package:unified_audio/core/localisation/app_translations.dart';
import 'package:unified_audio/core/logging/logger.dart';
import 'package:unified_audio/navigation/app_router.dart';
import 'package:unified_audio/navigation/routes.dart';
import 'package:unified_audio/themes/app_theme.dart';

class UnifiedAudioDashboard extends StatelessWidget {
  /// When set (e.g. in integration tests), overrides [Get.deviceLocale].
  final Locale? initialLocale;

  const UnifiedAudioDashboard({super.key, this.initialLocale});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'Unified Audio',
      theme: AppTheme.materialTheme,
      darkTheme: AppTheme.customTheme,
      initialRoute: AppRoutes.dashboard,
      getPages: AppRouter.pages,
      translations: AppTranslations(),
      locale: initialLocale ?? Get.deviceLocale ?? AppTranslations.fallbackLocale,
      supportedLocales: AppTranslations.supportedLocales,
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      fallbackLocale: AppTranslations.fallbackLocale,
      localeResolutionCallback: (locale, supportedLocales) {
        Log.d("locale=$locale");
        try {
          final systemLocale = supportedLocales.firstWhere(
            (element) {
              return element.languageCode == locale?.languageCode &&
                  element.countryCode == locale?.countryCode /*  && element.scriptCode == locale?.scriptCode */;
            },
          );
          return systemLocale;
        } catch (e) {
          return AppTranslations.fallbackLocale;
        }
      },
    );
  }
}
