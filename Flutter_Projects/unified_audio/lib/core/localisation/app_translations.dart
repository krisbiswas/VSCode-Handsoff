import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get/get.dart';

class AppTranslations extends Translations {
  static const fallbackLocale = Locale("en", "US");
  static const List<Locale> supportedLocales = [
    Locale("en", "US"),
    Locale("ru", "RU"),
    Locale("zh", "CN"),
  ];

  // Static map to hold all loaded language keys
  static final Map<String, Map<String, String>> _keys = {};

  // Load JSON files for your supported languages asynchronously
  static Future<void> init() async {
    for (Locale locale in supportedLocales) {
      // 1. Load string from assets
      String jsonString;
      String localeString;
      try {
        localeString = "${locale.languageCode}${locale.countryCode == null ? "" : "_${locale.countryCode}"}";
        jsonString = await rootBundle.loadString('assets/lang/$localeString.json');
      } catch (e) {
        localeString = "en_US";
        jsonString = await rootBundle.loadString('assets/lang/en_US.json');
      }
      // 2. Decode file
      final Map<String, dynamic> jsonMap = json.decode(jsonString);

      // 3. Convert dynamically typed maps to pure String maps
      final Map<String, String> stringMap = jsonMap.map(
        (key, value) => MapEntry(key, value.toString()),
      );

      // 4. Save into our static keys storage
      _keys[localeString] = stringMap;
    }
  }

  // GetX overrides this getter to read localized text
  @override
  Map<String, Map<String, String>> get keys => _keys;
}
