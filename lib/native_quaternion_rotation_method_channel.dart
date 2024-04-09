import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'native_quaternion_rotation_platform_interface.dart';

/// An implementation of [NativeQuaternionRotationPlatform] that uses method channels.
class MethodChannelNativeQuaternionRotation extends NativeQuaternionRotationPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodVersionChannel = const MethodChannel('native_quaternion_rotation');

  final EventChannel eventQuaterChannel = const EventChannel('native_quaternion_rotation_event_channel');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodVersionChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Stream? getQuaternionStream() {
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }
}
