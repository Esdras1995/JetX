package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by root on 3/29/15.
 */
public class Option extends Activity {

    SeekBar smu, sfx;
    AudioManager am;
    Button btnSave;
    TextView log;
    private int progrssSmu, progrssSfx, maxVol = 15;
    GameSound music, fx;
    //public static boolean ischeckedMusic, checkFx;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);

        btnSave = (Button)findViewById(R.id.save);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int curVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        progrssSmu = progrssSfx = maxVol;

        log = (TextView)findViewById(R.id.log);

        smu = (SeekBar)findViewById(R.id.volumeMusic);

        sfx = (SeekBar)findViewById(R.id.fxVolume);

        smu.setMax(maxVol);

        sfx.setMax(maxVol);

        smu.setProgress(curVol);

        sfx.setProgress(curVol);

        smu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                progrssSmu = smu.getProgress();

                float volume = (float)progrssSmu/maxVol;

                log.setText(volume+"");

                music.setMusicVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        sfx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                progrssSfx = sfx.getProgress();

                //float volume = (float)progrssSfx/maxVol;

                log.setText(progrssSfx+"");

                // music.setMusicVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savePreferences("progress_music", progrssSmu);

                savePreferences("progress_fx", progrssSfx);

                finish();
            }
        });

        loadPreferences();
    }

    public void loadPreferences(){

        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);

        progrssSmu = sharedPreferences.getInt("progress_music", maxVol);

        progrssSfx = sharedPreferences.getInt("progress_fx", maxVol);

        //log.setText(progrss+"");

        smu.setProgress(progrssSmu);
        sfx.setProgress(progrssSfx);
    }

    private void savePreferences(String key, int value){

        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, value);

        editor.commit();
    }

   /* public int getProgrss(){
        return progrss;
    }*/

}
