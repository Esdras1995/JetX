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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by root on 3/28/15.
 */
public class Menu extends Activity{

    //GameSound music;
    TextView bestScore, coins;
    GameSound sound;
    AudioManager am;
   // int maxVol;
    float volumeFx;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

       // am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       // maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //sound = new GameSound(this);

        //sound.playFx("RocheExplosion", (float)load("progress_fx")/maxVol, (float)load("progress_fx")/maxVol);

        /*music.playFx("AvionExplosion");
        music.playFx("ButtonSound");*/

        sound.playMusic(this);

        sound.setMusicVolume(load("progress_music"), load("progress_music"));

        TextView bestScore =(TextView)findViewById(R.id.bestScore);
        TextView coins =(TextView)findViewById(R.id.coins);
        Button btnPlay = (Button)findViewById(R.id.play);
        Button btnOption = (Button)findViewById(R.id.option);
        Button btnHelp = (Button)findViewById(R.id.help);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Game.class));
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Option.class));
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Help.class));
            }
        });

        bestScore.setText(" : "+load("bestScore"));
        coins.setText(" : "+load("coin"));
    }

    public int load(String data){
        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);
        int loaded = sharedPreferences.getInt(data, 0);
        return loaded;
    }

  /*  public void load(){

        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);

        int activeVolMusic = sharedPreferences.getInt("progress_music", 0);

        int activeVolFx = sharedPreferences.getInt("progress_fx", 0);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volumeMusic = (float)activeVolMusic/maxVol;

        volumeFx = (float)activeVolFx/maxVol;

        music.setMusicVolume(volumeMusic, volumeMusic);

        text.setText(volumeFx+" "+volumeMusic);
    }*/

    @Override
    protected void onPause(){
        super.onPause();
       // music.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}