import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'native_quaternion_rotation_platform_interface.dart';

/// An implementation of [NativeQuaternionRotationPlatform] that uses method channels.
class MethodChannelNativeQuaternionRotation extends NativeQuaternionRotationPlatform {
  final EventChannel eventQuaterChannel = const EventChannel('native_quaternion_rotation_event_channel');

  @override
  Stream? getQuaternionStream() {
    return eventQuaterChannel.receiveBroadcastStream().map((event) => event);
  }
}
