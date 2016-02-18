package com.example.hollyn.jetx;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by hollyn on 3/10/15.
 */
public class Avion {

    private static int x;
    private static int y;
    private static GameView view;
    private static float avionHeight;
    private static float avionWidth;
    Bitmap bmp;

    public Avion(GameView gameView, Bitmap bitmap){
        view = gameView;
        bmp = bitmap;
        avionHeight = bitmap.getHeight();
        avionWidth = bitmap.getWidth();
        x = (int) (gameView.getWidth()/2 - avionWidth/2);
        y = (int) (gameView.getHeight() - avionHeight);
    }

    public void update(){

    }

    public static int getWidth(){
        return (int) avionWidth;
    }

    public static int getHeight() {
        return (int) avionHeight;
    }

    public void draw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }

    public static void setX(int x){
        if(x < view.getWidth() - avionWidth && x > 0)
            Avion.x = x;
    }

    public static void setY(int y){
        if(y < view.getHeight() - avionHeight && y > 0)
            Avion.y = y;
    }

    public static void ExplodeAll(){
        for (int i=1; i<=4; i++) {
            view.boom(i);
        }
    }
    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }
}
