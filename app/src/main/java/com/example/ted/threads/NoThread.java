package com.example.ted.threads;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoThread extends AppCompatActivity {

  private static final String TAG = "NoThread";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_no_thread);

    Button btn = (Button) findViewById(R.id.button);
    assert btn != null;
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

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

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Hello World");

      }
    });

  }
}
