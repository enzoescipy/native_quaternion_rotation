package com.example.native_quaternion_rotation

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.reactivex.rxjava3.disposables.Disposable


/** NativeQuaternionRotationPlugin */
class NativeQuaternionRotationPlugin: FlutterPlugin, SensorEventListener {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var eventChannel : EventChannel
  private var disposable: Disposable? = null

  private lateinit var  mSensorManager: SensorManager
  private var mQuaternion: Sensor? = null

  private val NATIVE_QUATERNION_ROTATION_EVENVT_CHANNEL =  "native_quaternion_rotation_event_channel"

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    mSensorManager = flutterPluginBinding.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    mQuaternion = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, NATIVE_QUATERNION_ROTATION_EVENVT_CHANNEL)

    mSensorManager.registerListener(this, mQuaternion, SensorManager.SENSOR_DELAY_NORMAL)
  }

  private fun observeQuaternion(events: EventChannel.EventSink?) {
//    if (disposable == null) {
//      disposable =
//        }
//    }
  }

  private fun getQuaternion() {
    return Sensor.TYPE_ROTATION
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    mSensorManager.unregisterListener(this)
  }
}


class SensorActivity : Activity(), SensorEventListener {
  private val mSensorManager: SensorManager
  private val mQuaternion: Sensor?

  init {
    mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    mQuaternion = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
  }

  override fun onResume() {
    super.onResume()
    mSensorManager.registerListener(this, mQuaternion, SensorManager.SENSOR_DELAY_NORMAL)
  }

  override fun onPause() {
    super.onPause()
    mSensorManager.unregisterListener(this)
  }

  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
  override fun onSensorChanged(event: SensorEvent) {}
}


