import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:native_quaternion_rotation/native_quaternion_rotation_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelNativeQuaternionRotation platform = MethodChannelNativeQuaternionRotation();
  const MethodChannel channel = MethodChannel('native_quaternion_rotation');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
      channel,
      (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
