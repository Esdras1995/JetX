package com.example.hollyn.jetx;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Game extends Activity {

    private Roche roche;
    private GameView v;
    private ImageButton b_pause;
    private boolean pause, start = true;
    private TextView nmbrLife, speedRock, nmbrCoin, setscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //v.getHolder().setFixedSize(200, 300);
        setContentView(R.layout.game_layout);

        v = (GameView)findViewById(R.id.gameView);

        b_pause = (ImageButton)findViewById(R.id.pause);
        nmbrLife =(TextView)findViewById(R.id.life);
        nmbrCoin =(TextView)findViewById(R.id.coin);
        speedRock =(TextView)findViewById(R.id.speed);
        setscore =(TextView)findViewById(R.id.setscore);

        nmbrLife.setText(Integer.toString(Joueur.getLife()));

        pause = false;

        b_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pause) {
                    onPause();
                    pause = true;
                    ((ImageButton)v).setImageResource(R.drawable.play);
                } else {
                    onResume();
                    pause = false;
                    ((ImageButton)v).setImageResource(R.drawable.pause);
                }
            }
        });
    }

    public void setNmbrLife(final String txt){
        Game.this.runOnUiThread(new Runnable() {
            public void run() {
                nmbrLife.setText(txt);
            }
        });
    }

    public void setSpeedRock(final String txt){
        Game.this.runOnUiThread(new Runnable() {
            public void run() {
                speedRock.setText(txt);
            }
        });
    }

    public void setNmbrCoin(final String txt){
        Game.this.runOnUiThread(new Runnable() {
            public void run() {
                nmbrCoin.setText(txt);
            }
        });
    }
    public void setSetscore(final String txt){
        Game.this.runOnUiThread(new Runnable() {
            public void run() {
                setscore.setText(txt);
            }
        });
    }

    @Override
    protected void onPause() {
        if(!v.pause) {
            super.onPause();
            onSaveInstanceState(new Bundle());
            v.onPause1();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!start)
            v.onResume1();
        start = false;
    }



}

/*
*
* //////////////////      onRestoreInstanceState and onSaveInstanceState          //////////////////////
*
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Avion.setX(savedInstanceState.getInt("avionX"));
        Avion.setY(savedInstanceState.getInt("avionY"));
        Joueur.setScore(savedInstanceState.getInt("score"));
        Roche.setYSpeed(savedInstanceState.getInt("rocheYspeed"));
        Roche.setY(savedInstanceState.getInt("rocheY"));
        Roche.setX(savedInstanceState.getInt("rocheX"));
        Joueur.setLife(savedInstanceState.getInt("joueurLife"));
        Projectile.setX(savedInstanceState.getInt("projectileX"));
        Projectile.setY(savedInstanceState.getInt("projectileY"));


        Toast.makeText(this, "restored", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("avionX", Avion.getX());
        savedInstanceState.putInt("avionY", Avion.getY());
        savedInstanceState.putInt("score", Joueur.getScore());
        savedInstanceState.putInt("rocheYspeed", Roche.getYSpeed());
        savedInstanceState.putInt("rocheY", Roche.getY());
        savedInstanceState.putInt("rocheX", Roche.getX());
        savedInstanceState.putInt("joueurLife", Joueur.getLife());
        savedInstanceState.putInt("projectileX", Projectile.getX());
        savedInstanceState.putInt("projectileY", Projectile.getY());
        super.onSaveInstanceState(savedInstanceState);

        //Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }*/