
import 'native_quaternion_rotation_platform_interface.dart';

class NativeQuaternionRotation {
  Future<String?> getPlatformVersion() {
    return NativeQuaternionRotationPlatform.instance.getPlatformVersion();
  }

  Stream? getQuaternionStream() {
    return NativeQuaternionRotationPlatform.instance.getQuaternionStream();
  }
}
