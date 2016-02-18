package com.example.hollyn.jetx;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Game extends FragmentActivity {

    private Roche roche;
    private GameView v;
    private ImageButton b_pause, joge;
    private boolean pause, start = true;
    private TextView nmbrDiamand, nmbrCoin, setscore;
    ImageView imageLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.game_layout);

        v = (GameView)findViewById(R.id.gameView);

        b_pause = (ImageButton)findViewById(R.id.pause);
        nmbrCoin =(TextView)findViewById(R.id.coin);
        nmbrDiamand =(TextView)findViewById(R.id.diamand);
        setscore =(TextView)findViewById(R.id.setscore);
        imageLife = (ImageView) findViewById(R.id.imageLife);
        joge = (ImageButton) findViewById(R.id.joge);

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
       // showDialog();
    }

    public void showDialog(){
        FragmentManager manager = getSupportFragmentManager();
        Begin begin = new Begin();
        begin.show(manager, "touch_to_begin");

    }
/*
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\        Set Score Board      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void setNmbrDiamand(final String txt){
        Game.this.runOnUiThread(new Runnable() {
            public void run() {
                nmbrDiamand.setText(txt);
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

/*
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\        Set life player      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

public void setLife(){
    Game.this.runOnUiThread(new Runnable() {
        public void run() {
            switch (Joueur.getLife()){
                case 1:
                    imageLife.setImageResource(R.drawable.lifedone);
                    break;

                case 2:
                    imageLife.setImageResource(R.drawable.lifesemi);
                    break;

                case 3:
                    imageLife.setImageResource(R.drawable.life77);
                    break;
            }
        }
    });
}

/*
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\        Set life player      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

public void setJoge(){
    Game.this.runOnUiThread(new Runnable() {
        public void run() {
            switch (Joueur.getCoinGainedJoge()){
                case 5:
                    joge.setImageResource(R.drawable.progression1);
                    joge.setEnabled(true);
                    break;

                case 10:
                    joge.setImageResource(R.drawable.progression2);
                    joge.setEnabled(true);
                    break;

                case 15:
                    joge.setImageResource(R.drawable.progression3);
                    joge.setEnabled(true);
                    break;

                case 20:
                    joge.setImageResource(R.drawable.progression4);
                    joge.setEnabled(true);
                    break;

                case 25:
                    joge.setImageResource(R.drawable.progression5);
                    joge.setEnabled(true);
                    break;

                case 30:
                    joge.setImageResource(R.drawable.progression6);
                    joge.setEnabled(true);
                    break;

                case 35:
                    joge.setImageResource(R.drawable.progression7);
                    joge.setEnabled(true);
                    break;

                case 40:
                    joge.setImageResource(R.drawable.progression8);
                    joge.setEnabled(true);
                    break;

                case 45:
                    joge.setImageResource(R.drawable.progression9);
                    joge.setEnabled(true);
                    break;

                case 50:
                    joge.setImageResource(R.drawable.progression10);
                    joge.setEnabled(true);
                    break;

                case 55:
                    joge.setImageResource(R.drawable.progression11);
                    joge.setEnabled(true);
                    break;

                case 60:
                    joge.setImageResource(R.drawable.progression12);
                    joge.setEnabled(true);
                    joge.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Avion.ExplodeAll();
                            Joueur.setAllExpl();
                            joge.setEnabled(false);
                            joge.setImageResource(R.drawable.progression1);
                        }
                    });
                    break;
            }
        }
    });
}

/*
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\        Set Score Board      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
*  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    @Override
    protected void onPause() {
        super.onPause();
        onSaveInstanceState(new Bundle());
        v.onPause1();
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