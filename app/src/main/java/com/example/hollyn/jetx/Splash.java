package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity {

    //GameSound music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);



        Thread logoTimer = new Thread(){

            public void run(){
                try{
                    sleep(2000);
                    Intent menuIntent = new Intent(Splash.this, Menu.class);
                    startActivity(menuIntent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };

        logoTimer.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

}
