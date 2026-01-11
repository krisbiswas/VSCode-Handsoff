import 'package:logger/logger.dart';

class Log {
  static final logger = Logger(
    printer: PrettyPrinter(methodCount: 0, lineLength: 50, noBoxingByDefault: true),
  );
  static const String _tag = 'UNIFIED_AUDIO';

  static void d(String message) {
    // Implement your logging mechanism here
    // StackTrace.current;
    logger.d('$_tag: $message');
  }

  static void i(String message) {
    // Implement your logging mechanism here
    logger.i('$_tag: $message');
  }

  static void e(String message) {
    // Implement your error logging mechanism here
    logger.e('$_tag: $message');
  }
}
