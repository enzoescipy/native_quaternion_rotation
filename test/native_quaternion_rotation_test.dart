import 'package:flutter_test/flutter_test.dart';
import 'package:native_quaternion_rotation/native_quaternion_rotation.dart';
import 'package:native_quaternion_rotation/native_quaternion_rotation_platform_interface.dart';
import 'package:native_quaternion_rotation/native_quaternion_rotation_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockNativeQuaternionRotationPlatform
    with MockPlatformInterfaceMixin
    implements NativeQuaternionRotationPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final NativeQuaternionRotationPlatform initialPlatform = NativeQuaternionRotationPlatform.instance;

  test('$MethodChannelNativeQuaternionRotation is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelNativeQuaternionRotation>());
  });

  test('getPlatformVersion', () async {
    NativeQuaternionRotation nativeQuaternionRotationPlugin = NativeQuaternionRotation();
    MockNativeQuaternionRotationPlatform fakePlatform = MockNativeQuaternionRotationPlatform();
    NativeQuaternionRotationPlatform.instance = fakePlatform;

    expect(await nativeQuaternionRotationPlugin.getPlatformVersion(), '42');
  });
}
