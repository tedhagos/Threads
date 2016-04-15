package com.example.ted.threads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WithThread extends AppCompatActivity {

  public final static String TAG = "WithThread";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_with_thread);

    Button btn = (Button) findViewById(R.id.button);
    assert btn != null;
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new Thread(new Backgrounder()).start();
      }
    });

  }

  // INNER CLASS DEFINITIONS


  // BACKGROUNDER CLASS
  class Backgrounder implements Runnable {
    public void run() {
      // time consuming task
      long future_time = System.currentTimeMillis() + 5000;
      while(System.currentTimeMillis() < future_time) {
        synchronized(this) {
          try {
            wait(future_time - System.currentTimeMillis());
          }
          catch(InterruptedException ie){
            Log.e(TAG, String.format("Yikes! %s", ie.toString()));
          }
        }
      }
      // end time consuming task
      handler.sendEmptyMessage(0);
    }
  }

  // HANDLER CLASS

  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      TextView tv = (TextView) findViewById(R.id.textView);
      tv.setText("Hello World");
    }
  };


  
}
