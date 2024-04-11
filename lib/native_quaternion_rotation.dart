
import 'native_quaternion_rotation_platform_interface.dart';

class NativeQuaternionRotation {
  Stream? getQuaternionStream() {
    return NativeQuaternionRotationPlatform.instance.getQuaternionStream();
  }
}
