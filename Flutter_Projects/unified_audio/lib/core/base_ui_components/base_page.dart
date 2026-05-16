import 'package:flutter/material.dart';
import 'package:get/get.dart';

class BasePage<C> extends GetView<C> {
  late Widget contents;
  BasePage({super.key});

  @override
  Widget build(BuildContext context) {
    return SafeArea(child: contents);
  }
}
