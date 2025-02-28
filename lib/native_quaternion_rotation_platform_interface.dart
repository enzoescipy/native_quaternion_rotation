import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'native_quaternion_rotation_method_channel.dart';

abstract class NativeQuaternionRotationPlatform extends PlatformInterface {
  /// Constructs a NativeQuaternionRotationPlatform.
  NativeQuaternionRotationPlatform() : super(token: _token);

  static final Object _token = Object();

  static NativeQuaternionRotationPlatform _instance = MethodChannelNativeQuaternionRotation();

  /// The default instance of [NativeQuaternionRotationPlatform] to use.
  ///
  /// Defaults to [MethodChannelNativeQuaternionRotation].
  static NativeQuaternionRotationPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [NativeQuaternionRotationPlatform] when
  /// they register themselves.
  static set instance(NativeQuaternionRotationPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }


  void setStreamSpeedToFastest() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  void setStreamSpeedToGame() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  void setStreamSpeedToUI() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  void setStreamSpeedToNormal() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  Stream? getGameQuaternionEventStream() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  Stream? getQuaternionEventStream() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  Stream? getGameRotationVectorEventStream() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }

  Stream? getRotationVectorEventStream() {
    throw UnimplementedError('getQuaternionStream() has not been implemented.');
  }


}
