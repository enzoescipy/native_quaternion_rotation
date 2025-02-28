import 'package:flutter/services.dart';

import 'native_quaternion_rotation_platform_interface.dart';

/// An implementation of [NativeQuaternionRotationPlatform] that uses method channels.
class MethodChannelNativeQuaternionRotation extends NativeQuaternionRotationPlatform {
  late final MethodChannel methodChannel;
  late final EventChannel eventQuaterChannel;

  @override
  void setStreamSpeedToFastest() async {
    methodChannel = const MethodChannel('native_quaternion_rotation');
    final version = await methodChannel.invokeMethod<String>('SENSOR_DELAY_FASTEST');
  }

  @override
  void setStreamSpeedToGame() async {
    methodChannel = const MethodChannel('native_quaternion_rotation');
    final version = await methodChannel.invokeMethod<String>('SENSOR_DELAY_GAME');
  }

  @override
  void setStreamSpeedToUI() async {
    methodChannel = const MethodChannel('native_quaternion_rotation');
    final version = await methodChannel.invokeMethod<String>('SENSOR_DELAY_UI');
  }

  @override
  void setStreamSpeedToNormal() async {
    methodChannel = const MethodChannel('native_quaternion_rotation');
    final version = await methodChannel.invokeMethod<String>('SENSOR_DELAY_NORMAL');
  }

  @override
  Stream? getGameQuaternionEventStream() {
    eventQuaterChannel = const EventChannel('game_rotation_quaternion_event_channel');
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }

  @override
  Stream? getQuaternionEventStream() {
    eventQuaterChannel = const EventChannel('rotation_quaternion_event_channel');
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }

  @override
  Stream? getGameRotationVectorEventStream() {
    eventQuaterChannel = const EventChannel('game_rotation_vector_event_channel');
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }

  @override
  Stream? getRotationVectorEventStream() {
    eventQuaterChannel = const EventChannel('rotation_vector_event_channel');
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }

}
