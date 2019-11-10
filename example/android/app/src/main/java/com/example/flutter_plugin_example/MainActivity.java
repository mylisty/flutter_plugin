package com.example.flutter_plugin_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  private MethodChannel mMethodChannel;
  //记着要在 onCreat方法中调用
  private void methodChannelFunction() {
    mMethodChannel = new MethodChannel(getFlutterView(), "flutter_plugin");
    //设置监听
    mMethodChannel.setMethodCallHandler(
            new MethodChannel.MethodCallHandler() {
              @Override
              public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                String lMethod = call.method;
                if (lMethod.equals("getName")) {
                  //测试通过Flutter打开Android Activity
                  Intent lIntent = new Intent(MainActivity.this, SecondActivity.class);
                  MainActivity.this.startActivity(lIntent);
                } else {
                  result.notImplemented();
                }
              }
            }
    );
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    methodChannelFunction();
  }
}
