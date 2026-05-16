import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:get/get.dart';
import 'package:unified_audio/core/localisation/app_translations.dart';
import 'package:unified_audio/core/localisation/locale_keys.dart';
import 'package:unified_audio/di/injector.dart';
import 'package:unified_audio/modules/app_main/unified_dashboard.dart';

Future<Map<String, String>> loadExpectedStrings(Locale locale) async {
  final assetKey = AppTranslations.assetKeyFor(locale);
  final jsonString = await rootBundle.loadString('assets/lang/$assetKey.json');
  final jsonMap = json.decode(jsonString) as Map<String, dynamic>;
  return jsonMap.map((key, value) => MapEntry(key, value.toString()));
}

Future<void> resetAppState() async {
  await Get.deleteAll(force: true);
  Get.reset();
}

Future<void> pumpLocalizedApp(WidgetTester tester, Locale locale) async {
  WidgetsFlutterBinding.ensureInitialized();
  await AppTranslations.init();
  initDependency();

  await tester.pumpWidget(UnifiedAudioDashboard(initialLocale: locale));
  await tester.pumpAndSettle();
}

Future<void> openDeviceControl(WidgetTester tester) async {
  await tester.tap(find.text('Device 1'));
  await tester.pumpAndSettle();
}

Future<void> openAppBarMenu(WidgetTester tester) async {
  await tester.tap(find.byType(PopupMenuButton<dynamic>));
  await tester.pumpAndSettle();
}

String translationFor(Map<String, String> strings, LocaleKeys key) {
  final value = strings[key.name];
  if (value == null) {
    fail('Missing translation for ${key.name}');
  }
  return value;
}

void expectLocalizedText(
  Map<String, String> strings,
  LocaleKeys key, {
  int expectedCount = 1,
}) {
  expect(
    find.text(translationFor(strings, key)),
    findsNWidgets(expectedCount),
  );
}
