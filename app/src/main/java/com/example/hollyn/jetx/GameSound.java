package com.example.hollyn.jetx;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 3/24/15.
 */
public class GameSound extends Thread{

    private static MediaPlayer music;
    private MediaPlayer mediaPlayer;
    private Map<String, Integer> soundMap = new HashMap<>(3);
    Context context;
    AudioManager am;
    int maxVol;

    public GameSound(Context context){

        this.context = context;

        am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        soundMap.put("RocheExplosion", Integer.valueOf(R.raw.explosion_roche));
        soundMap.put("ButtonSound", Integer.valueOf(R.raw.song));
        soundMap.put("AvionExplosion", Integer.valueOf(R.raw.katouche));

    }

   /* public GameSound getInstanceSound(Context context){
        return new GameSound(context);
    }*/


    public static void playMusic(Context context){
        music = MediaPlayer.create(context, R.raw.music);
        music.start();
        music.setLooping(true);
    }

    public static void stopMusic(){
       music.stop();
    }

    public void init(){

    }

    public static boolean isPlayingMusic(){
        return music.isPlaying();
    }

    public static void setMusicVolume(float left, float right){
        music.setVolume(left, right);
    }


    public void playFx(String key, int left, int right){
        mediaPlayer = MediaPlayer.create(context, soundMap.get(key));
        mediaPlayer.start();
        mediaPlayer.setVolume((float)left/maxVol, (float)right/maxVol);
    }

}
