package com.example.joannapacia.newsreader.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ProgressBar;

import com.example.joannapacia.newsreader.R;

/**
 * Created by joannapacia on 12/03/17.
 */

public class SplashScreen extends android.support.v7.app.AppCompatActivity {

    // splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    private ProgressBar spinner;
    private String TAG = SplashScreen.class.getSimpleName();

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        spinner=(ProgressBar)findViewById(R.id.circular_progress_bar);
        spinner.setVisibility(android.view.View.VISIBLE);

        ObjectAnimator anim = ObjectAnimator.ofInt(spinner, "progress", 0, 100);
        anim.setDuration(SPLASH_TIME_OUT);
        anim.setInterpolator(new android.view.animation.DecelerateInterpolator());
        anim.start();

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // close this activity
        finish();
    }
}
