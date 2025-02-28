
import 'native_quaternion_rotation_platform_interface.dart';

class NativeQuaternionRotation {
  void setStreamSpeedToFastest() {
    return NativeQuaternionRotationPlatform.instance.setStreamSpeedToFastest();
  }

  void setStreamSpeedToGame() {
    return NativeQuaternionRotationPlatform.instance.setStreamSpeedToGame();
  }

  void setStreamSpeedToUI() {
    return NativeQuaternionRotationPlatform.instance.setStreamSpeedToUI();
  }

  void setStreamSpeedToNormal() {
    return NativeQuaternionRotationPlatform.instance.setStreamSpeedToNormal();
  }

  Stream? getGameQuaternionEventStream() {
    return NativeQuaternionRotationPlatform.instance.getGameQuaternionEventStream();
  }

  Stream? getQuaternionEventStream() {
    return NativeQuaternionRotationPlatform.instance.getQuaternionEventStream();
  }

  Stream? getGameRotationVectorEventStream() {
    return NativeQuaternionRotationPlatform.instance.getGameRotationVectorEventStream();
  }

  Stream? getRotationVectorEventStream() {
    return NativeQuaternionRotationPlatform.instance.getRotationVectorEventStream();
  }
}
