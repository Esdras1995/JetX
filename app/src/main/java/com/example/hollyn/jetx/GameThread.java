package com.example.hollyn.jetx;

import android.graphics.Canvas;

/**
 * Created by hollyn on 3/10/15.
 */
public class GameThread extends Thread {

    private GameView view;
    private boolean running = false;
    private final int FPS = 60;

    public GameThread(GameView view){
        this.view = view;
    }

    public void setRunning(boolean run){
        running = run;
    }

    public boolean getRunning(){
        return running;
    }

    public void run(){
        long tickPS = 100/FPS;
        long startTime;
        long sleepTime;
        while (running){
            startTime = System.currentTimeMillis();
            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()){
                    view.ondraw(c);
                }
            }
            finally {
                if(c != null)
                    view.getHolder().unlockCanvasAndPost(c);
            }
            sleepTime = tickPS - (System.currentTimeMillis() - startTime);
            try {
                if(sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
