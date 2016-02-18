package com.example.hollyn.jetx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hollyn on 3/10/15.
 */
public class GameView extends SurfaceView {

    private static GameSound sound;
    private final Bitmap expl;
    private Avion avion;
    private GameThread gameThread;
    private Bitmap bullet;
    private Bitmap bmp;
    private SurfaceHolder holder;
    private int a, b;
    private int x;
    private int y;
    private Random random = new Random();
    private int i = random.nextInt(4);
    private List<Roche> imRoche = new ArrayList<>();
    private Paint paint;
    private Context context;
    private Joueur joueur;
    private int bestScore;
    public boolean pause;
    private Background background;
    private Coin coin;
    private Bitmap coins;
    private List<Explosion> temps = new ArrayList<>();
    private List<Coin> tempsc = new ArrayList<>();
    private List<Diamand> tempsd = new ArrayList<>();
    private List<Projectile> tempsb = new ArrayList<>();
    private List<Indestructible> tempsi = new ArrayList<>();
    private int m;
    private int coingain;
    private int diamandgain;
    private Bitmap diamand;
    private Bitmap indestructible;
    Rock1 r1;
    Rock2 r2;
    Rock3 r3;
    Rock4 r4;
    boolean activateR1 = false;
    boolean activateR2 = false;
    boolean activateR3 = false;
    boolean activateR4 = false;
    private static boolean instance = false;

    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    * //////////////////////////////////////////////                 CONSTRUCTOR                   ///////////////////////////////////////////////////
    * ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if(!isTablet(getContext())) {
            paint = new Paint();
            joueur = new Joueur();
            background = new Background(context);
            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.redjet);
            bullet = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
            diamand = BitmapFactory.decodeResource(getResources(), R.drawable.diamnd);
            indestructible = BitmapFactory.decodeResource(getResources(), R.drawable.fire_ball);
            pause = false;

            gameThread = new GameThread(this);
            r1 = new Rock1(this, BitmapFactory.decodeResource(getResources(), R.drawable.r1));
            r2 = new Rock2(this, BitmapFactory.decodeResource(getResources(), R.drawable.r2));
            r3 = new Rock3(this, BitmapFactory.decodeResource(getResources(), R.drawable.r3));
            r4 = new Rock4(this, BitmapFactory.decodeResource(getResources(), R.drawable.r4));
            holder = getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    avion = new Avion(GameView.this, bmp);
                    gameThread.setRunning(true);
                    gameThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                }
            });
            expl = BitmapFactory.decodeResource(getResources(), R.drawable.exp);
            sound = new GameSound(context);
            instance = true;
            sound.init1(R.raw.game);
            sound.playMusic1();
        }else{
            paint = new Paint();
            joueur = new Joueur();
            background = new Background(context);
            paint.setColor(Color.WHITE);
            paint.setTextSize(20);
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.red);
            bullet = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
            coins = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
            diamand = BitmapFactory.decodeResource(getResources(), R.drawable.diamnd);
            indestructible = BitmapFactory.decodeResource(getResources(), R.drawable.fire_ball600x1024);
            pause = false;

            gameThread = new GameThread(this);
            r1 = new Rock1(this, BitmapFactory.decodeResource(getResources(), R.drawable.r1));
            r2 = new Rock2(this, BitmapFactory.decodeResource(getResources(), R.drawable.r2));
            r3 = new Rock3(this, BitmapFactory.decodeResource(getResources(), R.drawable.r3));
            r4 = new Rock4(this, BitmapFactory.decodeResource(getResources(), R.drawable.r4));
            holder = getHolder();
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    avion = new Avion(GameView.this, bmp);
                    gameThread.setRunning(true);
                    gameThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                }
            });
            expl = BitmapFactory.decodeResource(getResources(), R.drawable.exp);
            sound = new GameSound(context);
            instance = true;
            sound.init1(R.raw.game);
            sound.playMusic1();

        }
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  On Draw Canvas                  /////////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    protected void ondraw(Canvas canvas){
        background.mdraw(canvas);

        for (int k = tempsb.size() - 1; k>= 0 ; k--)
            tempsb.get(k).onDraw(canvas);

        for (int i = tempsc.size() - 1; i>= 0 ; i--)
            tempsc.get(i).ondraw(canvas);

        for (int z = temps.size() - 1; z>= 0 ; z--)
            temps.get(z).onDraw(canvas);

        for (int z1 = tempsd.size() - 1; z1>= 0 ; z1--)
            tempsd.get(z1).ondraw(canvas);

        for (int z2 = tempsi.size() - 1; z2>= 0 ; z2--)
            tempsi.get(z2).ondraw(canvas);

        drawrock(canvas);

        drawDiamand();

        drawIndestructible();

        avion.draw(canvas);
        scoreBoard();

        activateRock();

        gamePlay(canvas);
    }

    public static boolean isTablet(Context context){
        if((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE){
            return true;
        }
        return false;
    }

    public void activateRock(){
        switch ((int) joueur.getDistance()){
            case 500:
                activateR2 = true;
                break;

            case 1500:
                activateR3 = true;
                break;

            case 3000:
                activateR4 = true;
                break;
        }
    }

    public void drawDiamand(){
        if(joueur.getDistance() % 599 == 0)
            tempsd.add(new Diamand(tempsd, this, random.nextInt(getWidth()), diamand, sound));
    }

    public void drawIndestructible(){
        if(joueur.getDistance() % 150 == 0)
            tempsi.add(new Indestructible(tempsi, this, Avion.getX(), 0, indestructible, sound));
    }

    public void shoot(float x, float y){

        tempsb.add(new Projectile(tempsb, this, x, y, bullet, sound));
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////////                  Score Board                     /////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void scoreBoard(){
        ((Game) getContext()).setNmbrCoin((joueur.getCoinGained() + " "));
        ((Game) getContext()).setNmbrDiamand(joueur.getDiamandGained() + " ");
        //((Game) getContext()).setSpeedRock((imRoche.get(i).getYSpeed() + " "));
        joueur.setDistance();
        ((Game) getContext()).setSetscore((joueur.getScore() + ""));
        ((Game) getContext()).setLife();
        ((Game) getContext()).setJoge();
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* /////////////////////////////////////////////////                  DRAW ROCK                    /////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void drawrock(Canvas canvas){
        r1.ondraw(canvas);
        if(activateR2)
            r2.ondraw(canvas);
        if(activateR3)
            r3.ondraw(canvas);
        if(activateR4)
            r4.ondraw(canvas);
    }


/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* /////////////////////////////////////////////                  Array Rock                    ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/



/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  GAMEPLAY                     ///////////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void gamePlay(Canvas c){
        if(Roche.getY() + Roche.getHeight() >= Avion.getY() && Roche.getY() <= Avion.getY() + Avion.getHeight()
                && Roche.getX() + Roche.getWidth() >= Avion.getX() && Roche.getX() <= Avion.getX() + Avion.getWidth()) {
            c.drawColor(Color.RED);
            imRoche.get(i).isHit();
            imRoche.get(i).remove();
            joueur.looseLife(1);

        }else if(Rock1.getY() + Rock1.getHeight() >= Avion.getY() && Rock1.getY() <= Avion.getY() + Avion.getHeight()
                && Rock1.getX() + Rock1.getWidth() >= Avion.getX() && Rock1.getX() <= Avion.getX() + Avion.getWidth()){
            c.drawColor(Color.RED);
            r1.isHit();
            r1.remove();
            joueur.looseLife(1);
        }else if(Rock2.getY() + Rock2.getHeight() >= Avion.getY() && Rock2.getY() <= Avion.getY() + Avion.getHeight()
                && Rock2.getX() + Rock2.getWidth() >= Avion.getX() && Rock2.getX() <= Avion.getX() + Avion.getWidth()){
            c.drawColor(Color.RED);
            r2.isHit();
            r2.remove();
            joueur.looseLife(1);
        }else if(Rock3.getY() + Rock3.getHeight() >= Avion.getY() && Rock3.getY() <= Avion.getY() + Avion.getHeight()
                && Rock3.getX() + Rock3.getWidth() >= Avion.getX() && Rock3.getX() <= Avion.getX() + Avion.getWidth()){
            c.drawColor(Color.RED);
            r3.isHit();
            r3.remove();
            joueur.looseLife(1);
        }else if(Rock4.getY() + Rock4.getHeight() >= Avion.getY() && Rock4.getY() <= Avion.getY() + Avion.getHeight()
                && Rock4.getX() + Rock4.getWidth() >= Avion.getX() && Rock4.getX() <= Avion.getX() + Avion.getWidth()){
            c.drawColor(Color.RED);
            r4.isHit();
            r4.remove();
            joueur.looseLife(1);
        }

        if(joueur.getLife() <= 0) {
            bestScore = (int) load("bestScore");
            coingain = (int) (load("coin") + joueur.getCoinGained());
            diamandgain = (int) (load("coin") + joueur.getDiamandGained());
            joueur.died();

            save();



            Intent intent = new Intent().setClass(getContext(), GameOver.class);
            intent.putExtra("score", joueur.getScore()+"");
            intent.putExtra("coin", joueur.getCoinGained()+"");
            intent.putExtra("distance", joueur.getDistance()+"");
            intent.putExtra("rockdestroy", joueur.getRockdestroy()+"");

            joueur.setCoinGain();
            joueur.setDiamandGain();
            getContext().startActivity(intent);

            ((Activity) context).finish();
        }
    }


    public void boom (int rock) {

        //new SoundTask().execute();

        switch (rock){
            case 1:
                temps.add(new Explosion(temps, this, Rock1.getX(), Rock1.getY(), expl, sound));
                tempsc.add(new Coin(tempsc, this, Rock1.getX(), Rock1.getY(), coins, sound));
                Rock1.isHit();
                break;

            case 2:
                temps.add(new Explosion(temps, this, Rock2.getX(), Rock2.getY(), expl, sound));
                tempsc.add(new Coin(tempsc, this, Rock2.getX(), Rock2.getY(), coins, sound));
                Rock2.isHit();
                break;

            case 3:
                temps.add(new Explosion(temps, this, Rock3.getX(), Rock3.getY(), expl, sound));
                tempsc.add(new Coin(tempsc, this, Rock3.getX(), Rock3.getY(), coins, sound));
                Rock3.isHit();
                break;

            case 4:
                temps.add(new Explosion(temps, this, Rock4.getX(), Rock4.getY(), expl, sound));
                tempsc.add(new Coin(tempsc, this, Rock4.getX(), Rock4.getY(), coins, sound));
                Rock4.isHit();
                break;

        }

       /* new Thread(new Runnable() {
            @Override
            public void run() {*/

            /*}
        }).start();*/

        r1.remove();
        r2.remove();
        r3.remove();
        r4.remove();
        joueur.addRockDestroy();
    }


/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  On Touch Event                    ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/


     @Override
    public boolean onTouchEvent(MotionEvent event) {
         if(!pause) {
             m++;
             if(m == 10) {
                 shoot(Avion.getX(), Avion.getY());
                 m = 0;
             }
             switch (event.getAction()) {
                 case MotionEvent.ACTION_DOWN:

                     x = avion.getX();
                     //y = avion.getY();

                     a = (int) event.getX();
                     b = (int) event.getY();
                     break;

                 case MotionEvent.ACTION_UP:
                     break;

                 case MotionEvent.ACTION_MOVE:
                     avion.setX((int) ((event.getX() - a) + x));
                     //avion.setY((int) ((event.getY() - b) + y));
                     break;
             }
         }
        return true;
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* ///////////////////////////////////////////////                  On Pause                   ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void  onPause1(){
        if(gameThread != null) {
            pause = true;
            gameThread.setRunning(false);
            while (pause) {
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            gameThread = null;
            sound.pauseMusic1();
        }
    }

    public static void pause(){
        //sound = new GameSound(context1);
        if(instance)
        sound.pauseMusic1();
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* ////////////////////////////////////////////////                  On Resume                  ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void  onResume1(){
        pause = false;
        if(gameThread == null)
            gameThread = new GameThread(this);
        //sound.init1(R.raw.game);
        sound.playMusic1();
        gameThread.setRunning(true);
        gameThread.start();
    }


    public long load(String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences("mdata", Context.MODE_PRIVATE);
        long loaded = sharedPreferences.getInt(data, 0);
        return loaded;
    }

    public int loadPreferences(String data){
        SharedPreferences sharedPreferences = context.getSharedPreferences("mdata", Context.MODE_PRIVATE);
        int loaded = sharedPreferences.getInt(data, 0);
        return loaded;
    }


   public void save(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("mdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(joueur.getScore() > bestScore)
            editor.putInt("bestScore", (int) joueur.getScore());
        editor.putInt("coin", coingain);
        editor.putInt("diamand", diamandgain);

        //editor.putInt("coin", c);

        editor.commit();
    }



   /* class SoundTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            soundRoche = new GameSound(context);
            soundRoche.playFx("RocheExplosion", loadPreferences("progress_fx"), loadPreferences("progress_fx"));
            return null;
        }
    }*/
}
