import 'package:flutter/material.dart';
import 'package:unified_audio/modules/multi_speaker/create_multi_speaker.dart';
import '../modules/app_main/dashboard/dashboard.dart';
import '../modules/soundbar/home/soundbar_home.dart';
import '../modules/soundtower/home/sound_tower_home.dart';

class AppRouter {
  static const dashboard = "/";
  static const soundbar = "/soundbar";
  static const soundTower = "/soundTower";
  static const createMultiSpeaker = "/createMultiSpeaker";

  static Route<dynamic> generateRoute(RouteSettings settings) {
    switch (settings.name) {
      case dashboard:
        return MaterialPageRoute(builder: (_) => const Dashboard());
      case createMultiSpeaker:
        return MaterialPageRoute(builder: (_) => const CreateMultiSpeaker());
      case soundbar:
        return MaterialPageRoute(builder: (_) => const SoundBarHome());
      case soundTower:
        return MaterialPageRoute(builder: (_) => const SoundTowerHome());
      default:
        return MaterialPageRoute(
          builder: (_) => Scaffold(
            body: Center(child: Text('No route defined for ${settings.name}')),
          ),
        );
    }
  }
}
