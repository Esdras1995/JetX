package com.example.hollyn.jetx;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 3/24/15.
 */
public class GameSound{

    private static MediaPlayer music, music1;
    private MediaPlayer mediaPlayer;
    private Map<String, Integer> soundMap = new HashMap<>(5);
    private static boolean play;
    Context context;
    AudioManager am;
    int maxVol;
    public static final String SOUND_EXPLOSION_ROCHE = "RocheExplosion";
    public static final String SOUND_BUTTON = "ButtonSound";
    public static final String SOUND_COIN = "Coin";
    public static final String SOUND_PROJECTILE = "Projectile";
    public static final String SOUND_EXPLOSION_AVION = "AvionExplosion";
    public static final String SOUND_DIAMAND = "Diamand";


    public static final int SOUND_YOU_WIN = 2;

    public static final int SOUND_YOU_LOSE = 3;



    private SoundPool soundPool;

    private HashMap<String, Integer> soundPoolMap;

    public GameSound(Context context){

        this.context = context;

        /*am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        soundMap.put("RocheExplosion", Integer.valueOf(R.raw.explosion_roche));
        soundMap.put("ButtonSound", Integer.valueOf(R.raw.song));
        soundMap.put("AvionExplosion", Integer.valueOf(R.raw.katouche));
        */

        //  this.context = context;

        //am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        //maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

       /* soundMap.put("RocheExplosion", Integer.valueOf(R.raw.explosion_woch_game));
        soundMap.put("ButtonSound", Integer.valueOf(R.raw.boutton_sound));
        soundMap.put("AvionExplosion", Integer.valueOf(R.raw.katouche));
        soundMap.put("Coin", Integer.valueOf(R.raw.coin_sound));
        soundMap.put("Projectile", Integer.valueOf(R.raw.sound_katouch_avion));*/

        initSounds();
    }

    public void playMusic(){
        if (load("music")){
            music.start();
            music.setLooping(true);
        //play = true;
       }
    }

    public void playMusic1(){
        if (load("music")){
            music1.start();
            music1.setLooping(true);
            //play = true;
        }
    }

    public void pauseMusic(){
        music.pause();
        //play = false;
    }

    public void pauseMusic1(){
        music1.pause();
    }

    public static void stopMusic(){
        music.stop();
    }

    public void init(int sound){
        music = MediaPlayer.create(context, sound);
    }
    public void init1(int sound){
       music1  = MediaPlayer.create(context, sound);
    }

    public static boolean isPlayingMusic(){
        return music.isPlaying();
    }

    public static void setMusicVolume(float left, float right){
        music.setVolume(left, right);
    }


    /*public void playFx(String key, int left, int right){
        mediaPlayer = MediaPlayer.create(context, soundMap.get(key));
        mediaPlayer.start();
        mediaPlayer.setVolume((float) left / maxVol, (float) right / maxVol);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
        });
    }*/

    private void initSounds() {

        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

        soundPoolMap = new HashMap<String, Integer>();

        soundPoolMap.put(SOUND_EXPLOSION_ROCHE, soundPool.load(context, R.raw.explosion_roche, 1));
        soundPoolMap.put(SOUND_EXPLOSION_AVION, soundPool.load(context, R.raw.explosion_roche, 1));
        soundPoolMap.put(SOUND_BUTTON, soundPool.load(context, R.raw.boutton_sound, 1));
        soundPoolMap.put(SOUND_COIN, soundPool.load(context, R.raw.coin_sound, 1));
        soundPoolMap.put(SOUND_PROJECTILE, soundPool.load(context, R.raw.sound_katouch_avion, 1));
        soundPoolMap.put(SOUND_DIAMAND, soundPool.load(context, R.raw.diamand_sound, 1));

    }



    public void playSound(String sound) {
        if(load("sfx")){

            AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
            soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);
       }

    }
    public boolean load(String data){

        SharedPreferences sharedPreferences = context.getSharedPreferences("mdata", Context.MODE_PRIVATE);
        boolean loaded = sharedPreferences.getBoolean(data, false);

        return loaded;
    }



   /* public GameSound getInstanceSound(Context context){
        return new GameSound(context);
    }


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
//        music.setVolume(left, right);
    }


    public void playFx(String key, int left, int right){
        mediaPlayer = MediaPlayer.create(context, soundMap.get(key));
        mediaPlayer.start();
        mediaPlayer.setVolume((float)left/maxVol, (float)right/maxVol);
    }
*/
}
