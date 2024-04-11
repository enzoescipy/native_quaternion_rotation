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
import io.reactivex.rxjava3.observables.ConnectableObservable


/** NativeQuaternionRotationPlugin */
class NativeQuaternionRotationPlugin: FlutterPlugin {
  private lateinit var eventChannel : EventChannel

  private lateinit var  mSensorManager: SensorManager
  private var mQuaternion: Sensor? = null
  private var mListener: SensorEventListener? = null

  private val NATIVE_QUATERNION_ROTATION_EVENVT_CHANNEL =  "native_quaternion_rotation_event_channel"

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {

    mSensorManager = flutterPluginBinding.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    mQuaternion = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) // this section is the sensor-defining part.

    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, NATIVE_QUATERNION_ROTATION_EVENVT_CHANNEL)
    eventChannel.setStreamHandler(object : EventChannel.StreamHandler {

      override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        mListener = RotationSensorListener(events!!);
        mSensorManager.registerListener(mListener, mQuaternion, SensorManager.SENSOR_DELAY_NORMAL)

      }
      override fun onCancel(arguments: Any?) {
        mSensorManager.unregisterListener(mListener)
      }

    })
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    eventChannel.setStreamHandler(null)
  }

}

class RotationSensorListener(events: EventChannel.EventSink) : SensorEventListener {
  var events = events
  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
  override fun onSensorChanged(event: SensorEvent) {
    events.success(event.values)
  }

}


