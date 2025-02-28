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




class NativeQuaternionRotationPlugin: FlutterPlugin, MethodCallHandler {
    private lateinit var channel : MethodChannel


    private lateinit var eventChannel : EventChannel
  
    private lateinit var  mSensorManager: SensorManager
    private var mQuaternion: Sensor? = null
    private var mGameQuaternion: Sensor? = null
    private var mListener: SensorEventListener? = null

    private lateinit var GAME_ROTATION_QUATERNION_eventChannel: EventChannel
    private lateinit var ROTATION_QUATERNION_eventChannel: EventChannel
    private lateinit var GAME_ROTATION_VECTOR_eventChannel: EventChannel
    private lateinit var ROTATION_VECTOR_eventChannel: EventChannel
  
    private val GAME_ROTATION_QUATERNION =  "game_rotation_quaternion_event_channel"
    private val ROTATION_QUATERNION =  "rotation_quaternion_event_channel"
    private val GAME_ROTATION_VECTOR =  "game_rotation_vector_event_channel"
    private val ROTATION_VECTOR =  "rotation_vector_event_channel"

    private var EMIT_SPEED = SensorManager.SENSOR_DELAY_GAME

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "SENSOR_DELAY_GAME" -> {EMIT_SPEED = SensorManager.SENSOR_DELAY_GAME}
            "SENSOR_DELAY_FASTEST" -> {EMIT_SPEED = SensorManager.SENSOR_DELAY_FASTEST}
            "SENSOR_DELAY_NORMAL" -> {EMIT_SPEED = SensorManager.SENSOR_DELAY_NORMAL}
            "SENSOR_DELAY_UI" -> {EMIT_SPEED = SensorManager.SENSOR_DELAY_UI}
        }
    }
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "native_quaternion_rotation")
        channel.setMethodCallHandler(this)

      mSensorManager = flutterPluginBinding.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
      mQuaternion = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) // this section is the sensor-defining part.
      mGameQuaternion = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR) // this section is the sensor-defining part.
  
      GAME_ROTATION_QUATERNION_eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, GAME_ROTATION_QUATERNION)
      ROTATION_QUATERNION_eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, ROTATION_QUATERNION)
      GAME_ROTATION_VECTOR_eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, GAME_ROTATION_VECTOR)
      ROTATION_VECTOR_eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, ROTATION_VECTOR)

      GAME_ROTATION_QUATERNION_eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
          mListener = GAME_ROTATION_QUATERNION_RotationSensorListener(events!!);
          mSensorManager.registerListener(mListener, mQuaternion, EMIT_SPEED)
  
        }
        override fun onCancel(arguments: Any?) {
          mSensorManager.unregisterListener(mListener)
        }
      })

      ROTATION_QUATERNION_eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
          mListener = ROTATION_QUATERNION_RotationSensorListener(events!!);
          mSensorManager.registerListener(mListener, mQuaternion, EMIT_SPEED)
  
        }
        override fun onCancel(arguments: Any?) {
          mSensorManager.unregisterListener(mListener)
        }
      })

      GAME_ROTATION_VECTOR_eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
          mListener = GAME_ROTATION_VECTOR_RotationSensorListener(events!!);
          mSensorManager.registerListener(mListener, mQuaternion, EMIT_SPEED)
  
        }
        override fun onCancel(arguments: Any?) {
          mSensorManager.unregisterListener(mListener)
        }
      })

      ROTATION_VECTOR_eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
        override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
          mListener = ROTATION_VECTOR_RotationSensorListener(events!!);
          mSensorManager.registerListener(mListener, mQuaternion, EMIT_SPEED)
  
        }
        override fun onCancel(arguments: Any?) {
          mSensorManager.unregisterListener(mListener)
        }
      })
    }
  
    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
      GAME_ROTATION_QUATERNION_eventChannel.setStreamHandler(null)
      ROTATION_QUATERNION_eventChannel.setStreamHandler(null)
      GAME_ROTATION_VECTOR_eventChannel.setStreamHandler(null)
      ROTATION_VECTOR_eventChannel.setStreamHandler(null)
    }
  
}










class GAME_ROTATION_QUATERNION_RotationSensorListener(events: EventChannel.EventSink) : SensorEventListener {
  var events = events
  override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
  override fun onSensorChanged(event: SensorEvent) {
    var quater = FloatArray(4)
    SensorManager.getQuaternionFromVector(quater, event.values.clone())
    events.success(quater)
  }
}

class ROTATION_QUATERNION_RotationSensorListener(events: EventChannel.EventSink) : SensorEventListener {
    var events = events
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        var quater = FloatArray(4)
        SensorManager.getQuaternionFromVector(quater, event.values.clone())
        events.success(quater)
    }
}


class GAME_ROTATION_VECTOR_RotationSensorListener(events: EventChannel.EventSink) : SensorEventListener {
    var events = events
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        events.success(event.values)
    }
}
  

class ROTATION_VECTOR_RotationSensorListener(events: EventChannel.EventSink) : SensorEventListener {
    var events = events
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        events.success(event.values)
    }
}
  