import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import 'package:unified_audio/core/localisation/app_translations.dart';
import 'package:unified_audio/core/localisation/locale_keys.dart';

import 'helpers/localization_test_harness.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  group('translation assets', () {
    for (final locale in AppTranslations.supportedLocales) {
      testWidgets(
        'include every LocaleKeys entry for ${locale.languageCode}_${locale.countryCode}',
        (tester) async {
          final strings = await loadExpectedStrings(locale);

          for (final key in LocaleKeys.values) {
            expect(
              strings.containsKey(key.name),
              isTrue,
              reason: 'assets/lang/${AppTranslations.assetKeyFor(locale)}.json '
                  'is missing "${key.name}"',
            );
            expect(strings[key.name], isNotEmpty);
            // expect(strings[key.name], matching menutree for the locale);
          }
        },
      );
    }
  });

  group('localized UI', () {
    tearDown(() async {
      await resetAppState();
    });

    for (final locale in AppTranslations.supportedLocales) {
      final tag = '${locale.languageCode}_${locale.countryCode ?? ''}';

      testWidgets('dashboard and device control strings for $tag', (tester) async {
        final strings = await loadExpectedStrings(locale);

        await pumpLocalizedApp(tester, locale);

        expectLocalizedText(strings, LocaleKeys.DASHBOARD);

        await openDeviceControl(tester);

        expectLocalizedText(strings, LocaleKeys.SOUND_SOURCE);
        expectLocalizedText(strings, LocaleKeys.AMBIENT_SOUND);
        expectLocalizedText(strings, LocaleKeys.SPACE_FIT);
        expectLocalizedText(strings, LocaleKeys.OFF, expectedCount: 2);

        await openAppBarMenu(tester);

        expectLocalizedText(strings, LocaleKeys.SETTINGS);
        expectLocalizedText(strings, LocaleKeys.INFORMATION);
      });
    }
  });
}
