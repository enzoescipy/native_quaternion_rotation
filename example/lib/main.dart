import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:developer';

import 'package:flutter/services.dart';
import 'package:native_quaternion_rotation/native_quaternion_rotation.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final _native_quaternion_rotation = NativeQuaternionRotation();
  dynamic? _quaternionRotation;
  StreamSubscription? _quaternionRotationStreamSubscription;

  @override
  void initState() {
    super.initState();

    _native_quaternion_rotation.setStreamSpeedToGame();

    _quaternionRotationStreamSubscription = _native_quaternion_rotation.getGameQuaternionEventStream()?.listen((event) {
      log(event.toString());
      setState(() {
        _quaternionRotation = event;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: ${_quaternionRotation.toString()}\n'),
        ),
      ),
    );
  }
}
