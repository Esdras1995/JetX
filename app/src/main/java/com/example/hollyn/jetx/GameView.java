package com.example.hollyn.jetx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private List<Projectile> tempsb = new ArrayList<>();
    private int m;
    private int coingain;
    private GameSound soundRoche;

    /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    * //////////////////////////////////////////////                 CONSTRUCTOR                   ///////////////////////////////////////////////////
    * ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
        joueur = new Joueur();
        background = new Background(context);
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.war_plane);
        bullet = BitmapFactory.decodeResource(getResources(), R.drawable.rocket);
        coins = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        pause = false;

        gameThread = new GameThread(this);
        addRoche();
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                avion = new Avion(GameView.this, bmp);
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });
        expl = BitmapFactory.decodeResource(getResources(), R.drawable.exp);
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  On Draw Canvas                  /////////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    protected void ondraw(Canvas canvas){
        background.ondraw(canvas);

        for (int k = tempsb.size() - 1; k>= 0 ; k--)
            tempsb.get(k).onDraw(canvas);

        for (int i = tempsc.size() - 1; i>= 0 ; i--)
            tempsc.get(i).ondraw(canvas);

        for (int z = temps.size() - 1; z>= 0 ; z--)
            temps.get(z).onDraw(canvas);

        if(imRoche.get(i).getY() >= getHeight()) {
            i = random.nextInt(4);
            imRoche.get(i).ondraw(canvas);
        }
        else
            imRoche.get(i).ondraw(canvas);
        avion.draw(canvas);
        scoreBoard(canvas);

        gamePlay(canvas);
    }

    public void shoot(float x, float y){
            tempsb.add(new Projectile(tempsb, this, x, y, bullet));
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////////                  Score Board                     /////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void scoreBoard(Canvas canvas){
        ((Game) getContext()).setNmbrLife((joueur.getLife() + " "));
        ((Game) getContext()).setNmbrCoin((joueur.getCoinGained() + " "));
        ((Game) getContext()).setSpeedRock((imRoche.get(i).getYSpeed() + " "));
        ((Game) getContext()).setSetscore((joueur.setAndGetDistance() + ""));
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  DRAW PROJECTILE                     /////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void drawProjectile(Canvas canvas){
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* /////////////////////////////////////////////                  Array Rock                    ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void addRoche(){
        imRoche.add(createRoche(R.drawable.r1));
        imRoche.add(createRoche(R.drawable.r2));
        imRoche.add(createRoche(R.drawable.r3));
        imRoche.add(createRoche(R.drawable.r4));
    }

    public Roche createRoche(int res){
        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), res);
        return new Roche(this, bmp1);
    }


/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* //////////////////////////////////////////                  GAMEPLAY                     ///////////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public void gamePlay(Canvas c){
        if(Roche.getY() + Roche.getHeight() >= Avion.getY() && Roche.getY() <= Avion.getY() + Avion.getHeight()
                && Roche.getX() + Roche.getWidth() >= Avion.getX() && Roche.getX() <= Avion.getX() + Avion.getWidth()) {
            c.drawColor(Color.RED);
            imRoche.get(i).remove();
            joueur.looseLife();

        }

        if(joueur.getLife() == 0) {
            bestScore = (int) load("bestScore");
            coingain = (int) (load("coin") + joueur.getCoinGained());
            joueur.died();

            save();



            Intent intent = new Intent().setClass(getContext(), GameOver.class);
            intent.putExtra("score", joueur.getScore()+"");
            intent.putExtra("coin", joueur.getCoinGained()+"");
            intent.putExtra("distance", joueur.getDistance()+"");
            intent.putExtra("rockdestroy", joueur.getRockdestroy()+"");

            joueur.setCoinGain();
            getContext().startActivity(intent);

            ((Activity) context).finish();
        }
    }


    public void boom () {

        new SoundTask().execute();

        temps.add(new Explosion(temps, this, Roche.getX(), Roche.getY(), expl));
        tempsc.add(new Coin(tempsc, this, Roche.getX(), Roche.getY(), coins));

       /* new Thread(new Runnable() {
            @Override
            public void run() {*/

            /*}
        }).start();*/

        imRoche.get(i).remove();
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
                     y = avion.getY();

                     a = (int) event.getX();
                     b = (int) event.getY();
                     break;

                 case MotionEvent.ACTION_UP:
                     break;

                 case MotionEvent.ACTION_MOVE:
                     avion.setX((int) ((event.getX() - a) + x));
                     avion.setY((int) ((event.getY() - b) + y));
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
        }
    }

/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* ////////////////////////////////////////////////                  On Resume                  ///////////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    public void  onResume1(){
        pause = false;
        if(gameThread == null)
            gameThread = new GameThread(this);
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

        //editor.putInt("coin", c);

        editor.commit();
    }/* */

    class SoundTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            soundRoche = new GameSound(context);
            soundRoche.playFx("RocheExplosion", loadPreferences("progress_fx"), loadPreferences("progress_fx"));
            return null;
        }
    }
}
