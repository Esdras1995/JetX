package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;


public class Credits extends Activity {
    public static boolean PAUSE = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits_layout);
    }
    @Override
    public void onPause(){
        super.onPause();
        PAUSE = true;
    }

    @Override
    public void onResume(){
        super.onPause();
        ContentMenu.play(true);
    }

}
