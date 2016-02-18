package com.example.hollyn.jetx;

import java.util.Random;

/**
 * Created by hollyn on 3/27/15.
 */
public class Joueur{

    // static int bestScore;

    private static int life;
    private static int score;
    private static int rockdestroy;
    private long distance;
    private static int coinGain;
    private static int diamandGain;
    private static int allExpl;

    public Joueur(){
        score = 0;
        rockdestroy = 0;
        life = 4;
        distance = 0;
        coinGain = 0;
        diamandGain = 0;
        allExpl = 0;
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

    public static void looseLife(int i){
        life -= i;
    }

    public static int getRockdestroy(){
        return rockdestroy;
    }

    public static void addRockDestroy(){
        rockdestroy ++;
    }

    public void setDistance(){
        ++distance;
    }

    public long getDistance(){
        return distance;
    }

    public long getScore(){
        return distance + rockdestroy * 25 + coinGain * 2;
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

    public static void setAllExpl(){
        allExpl = getCoinGained();
    }

    public static int getCoinGainedJoge() {
        return getCoinGained() - allExpl;
    }



    public static void gainDiamand(){
        diamandGain ++;
    }

    public static int getDiamandGained(){
        return diamandGain;
    }

    public static void setDiamandGain(){
        diamandGain = 0;
    }
}
