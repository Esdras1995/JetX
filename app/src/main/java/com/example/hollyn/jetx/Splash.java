package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;


public class Splash extends Activity {
    LinearLayout splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        splash = (LinearLayout) findViewById(R.id.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSplash(R.drawable.esih_splash);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSplash(R.drawable.vibe_splash);
            }
        }, 6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSplash(R.drawable.game_splash);
            }
        }, 9000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent = new Intent(Splash.this, Menu.class);
                startActivity(menuIntent);
                finish();
            }
        }, 12000);
    }

    public void loadSplash(int image){
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            splash.setBackgroundDrawable(getResources().getDrawable(image));
        } else {
            splash.setBackground(getResources().getDrawable(image));
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
    }

}
