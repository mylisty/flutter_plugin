package com.example.flutter_plugin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
/** FlutterPlugin */
public class FlutterPlugin implements MethodCallHandler {
  /** Plugin registration. */
  static EventChannel.EventSink sink;
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_plugin");
    final   EventChannel eventChannel = new  EventChannel(registrar.messenger(),"flutter_Channel");
    channel.setMethodCallHandler(new FlutterPlugin());
    eventChannel.setStreamHandler(new EventChannel.StreamHandler() {
      @Override
      public void onListen(Object o, EventChannel.EventSink eventSink) {
        sink= eventSink;
      }
      @Override
      public void onCancel(Object o) {

      }
    });
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if (call.method.equals("getName")){
      HashMap<String, String> map = new HashMap<>();
      map.put("a","5");
      map.put("b","3");
      map.put("c","2");
      sink.success(map);
    }else if (call.method.equals("setName")){
     String a = (String) call.arguments;
      Log.d("aaaaaaaaa",a);
    }else {
      result.notImplemented();
    }
  }

}
