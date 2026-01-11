import 'package:flutter/material.dart';

class SoundBarHome extends StatelessWidget {
  const SoundBarHome({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Sound Bar Home")),
      body: Center(
        child: ElevatedButton(
          onPressed: () => {
            // Navigator.pushNamed(context, '/dashboard'),
          },
          child: const Text('Dashboard'),
        ),
      ),
    );
  }
}
