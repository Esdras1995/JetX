package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by root on 3/29/15.
 */
public class Option extends Activity {
    ImageButton checkMusic, checkFx, credits;
    private boolean mu, sfx;
    public static boolean PAUSE = true;
    GameSound sound = new GameSound(ContentMenu.getMenuContext());


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);


        checkMusic = (ImageButton)findViewById(R.id.music);
        checkFx = (ImageButton)findViewById(R.id.sfx);
        credits = (ImageButton)findViewById(R.id.credits);

        loadPreferences();
        checkSound();

        checkMusic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int sdk = android.os.Build.VERSION.SDK_INT;

                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    if (mu == true){
                        checkMusic.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_none_check));
                        savePreferences("music", false);
                       // if(!sound.isPlayingMusic())
                            //ContentMenu.play();
                        ContentMenu.pause();
                        mu = false;

                    }else{
                        checkMusic.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_check));
                        savePreferences("music", true);
                        mu = true;
                        ContentMenu.play(true);
                    }
                }else {

                    if (mu == true){
                        checkMusic.setBackground(getResources().getDrawable(R.drawable.left_none_check));
                        savePreferences("music", false);
                        ContentMenu.pause();
                        mu = false;
                    }
                    else{
                        checkMusic.setBackground(getResources().getDrawable(R.drawable.left_check));
                        savePreferences("music", true);
                        ContentMenu.play(true);
                        mu = true;
                    }
                }


            }
        });

        checkFx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    if (sfx == true) {
                        checkFx.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_none_check));
                        savePreferences("sfx", false);
                        sfx = false;
                    }
                    else {
                        checkFx.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_check));
                        savePreferences("sfx", true);
                        sfx = true;
                    }
                } else {

                    if (sfx == true){
                        checkFx.setBackground(getResources().getDrawable(R.drawable.right_none_check));
                        savePreferences("sfx", false);
                        sfx = false;
                    }
                    else{
                        checkFx.setBackground(getResources().getDrawable(R.drawable.right_check));
                        savePreferences("sfx", true);
                        sfx = true;
                    }
                }
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(Option.this, Credits.class);
                startActivity(menuIntent);
            }
        });
    }

    public void checkSound(){

        int sdk = android.os.Build.VERSION.SDK_INT;

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            if (mu == true)
                checkMusic.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_check));
            else
                checkMusic.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_none_check));
        } else {

            if (mu == true)
                checkMusic.setBackground(getResources().getDrawable(R.drawable.left_check));
            else
                checkMusic.setBackground(getResources().getDrawable(R.drawable.left_none_check));
        }

        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            if (sfx == true)
                checkFx.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_check));
            else
                checkFx.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_none_check));
        } else {

            if (sfx == true)
                checkFx.setBackground(getResources().getDrawable(R.drawable.right_check));
            else
                checkFx.setBackground(getResources().getDrawable(R.drawable.right_none_check));
        }
    }
    public void loadPreferences(){

        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);

        mu = sharedPreferences.getBoolean("music", false);

        sfx = sharedPreferences.getBoolean("sfx", false);

    }

    private void savePreferences(String key, boolean value){

        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);

        editor.commit();
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
