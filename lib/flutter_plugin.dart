import 'dart:async';

import 'package:flutter/services.dart';


typedef void s(Object o);
class FlutterPlugin {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin');
  //异步监听
  static const EventChannel _eventChannel = const EventChannel('flutter_Channel');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
  static void getName() {
    _channel.invokeListMethod('getName');
  }
  static onReceive(s success,s error) {
    _eventChannel.receiveBroadcastStream().listen(success,onError: error);
  }
  static void setName(String str) {
    _channel.invokeListMethod('setName',str);
  }
}
