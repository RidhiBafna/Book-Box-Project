package com.example.ridzi.helpteens1;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.ridzi.helpteens1.Login;
import com.example.ridzi.helpteens1.R;


public class MainActivity extends Activity {

    private static final int SPLASH_TIMER = 3000;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar pb_colored = (ProgressBar) findViewById(R.id.pb_colored);


        // Set the progress status zero on each button click
        progressStatus = 0;
        // Start the lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    // Update the progress status
                    progressStatus +=1;

                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {


                            pb_colored.setProgress(progressStatus);
                            // Show the progress on TextView

                        }
                    });
                }
            }
        }).start(); // Start the operation


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        }, SPLASH_TIMER);

    }


}
