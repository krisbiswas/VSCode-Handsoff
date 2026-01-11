import 'package:flutter/material.dart';

class SoundTowerHome extends StatelessWidget {
  const SoundTowerHome({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Sound Tower Home")),
      body: Center(
        child: ElevatedButton(
          onPressed: () => {
            // Navigator.pushNamed(context, '/dashboard'),
          },
          child: const Text('back To Dashboard'),
        ),
      ),
    );
  }
}
