import 'package:flutter/material.dart';

class AppTheme {
  static ThemeData materialTheme = ThemeData(
    primarySwatch: Colors.blue,
    brightness: Brightness.light,
  );

  static ThemeData customTheme = ThemeData(
    primaryColor: Colors.deepPurple,
    brightness: Brightness.dark,
    scaffoldBackgroundColor: Colors.black,
    textTheme: const TextTheme(
      bodyMedium: TextStyle(color: Colors.white, fontSize: 18),
    ),
    appBarTheme: const AppBarTheme(
      backgroundColor: Colors.deepPurple,
      foregroundColor: Colors.white,
    ),
  );
}
