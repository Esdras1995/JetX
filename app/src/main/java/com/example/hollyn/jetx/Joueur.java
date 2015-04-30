package com.example.hollyn.jetx;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Random;

/**
 * Created by hollyn on 3/27/15.
 */
public class Joueur extends Activity{

   // static int bestScore;

    private static int life;
    private static int score;
    private static int rockdestroy;
    private long distance;
    private static int coinGain;

    public Joueur(){
        score = 0;
        rockdestroy = 0;
        life = 3;
        distance = 0;
        coinGain = 0;
    }

    public void getBonus(){
        Random r = new Random();

        switch (r.nextInt(3)){
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;
        }
    }

    public void died(){
        life = 0;
    }

    public static int getLife(){
        return life;
    }

    public static void setLife(int life){
        Joueur.life = life;
    }

    public void looseLife(){
        life --;
    }

    public static int getRockdestroy(){
        return rockdestroy;
    }

    public static void addRockDestroy(){
        rockdestroy ++;
    }

    public long  setAndGetDistance(){
        return ++distance;
    }

    public long getDistance(){
        return distance;
    }

    public long getScore(){
        return distance + rockdestroy + coinGain;
    }

    public static void gainCoin(){
        coinGain ++;
    }

    public static int getCoinGained(){
        return coinGain;
    }

    public static void setCoinGain(){
        coinGain = 0;
    }
}
