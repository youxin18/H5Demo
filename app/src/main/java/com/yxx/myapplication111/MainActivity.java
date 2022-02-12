package com.yxx.myapplication111;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private TextView textView;
    private TextView back;
    private TextView show;
    String name;
    String data;
    JSONObject json= null;
    JSONObject backJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=findViewById(R.id.webview);
        textView=findViewById(R.id.text);
        show=findViewById(R.id.show);
        back=findViewById(R.id.back);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://hgm.test.ikeyik.com/sy_sdktest/");
        webView.addJavascriptInterface(this,"ZKNWKT");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               webView.loadUrl("javascript:getAllInfo('"+backJson+"')");
                Toast.makeText(MainActivity.this,"回调成功",Toast.LENGTH_LONG).show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(name+data);
            }
        });
    }


    @JavascriptInterface
    public JSONObject postMessage(String jsondata) throws JSONException {
        json = new JSONObject(jsondata);
        name=json.getString("name");
        data=json.getString("data");
        if (name.equals("a")){
            Log.d("MainActivity12345","初始化");
            Toast.makeText(MainActivity.this,"正在准备初始化===",Toast.LENGTH_LONG).show();
            backJson=new JSONObject();
            backJson.put("level","1");
            return backJson;
        }else if(name.equals("b")){
            Log.d("MainActivity12345","关闭游戏");
            Toast.makeText(MainActivity.this,"关闭游戏",Toast.LENGTH_LONG).show();
            finish();
            return null;
        }
        return backJson;
    }
}




