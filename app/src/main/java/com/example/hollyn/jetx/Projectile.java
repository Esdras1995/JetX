package com.example.hollyn.jetx;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;
import java.util.Random;

/**
 * Created by hollyn on 3/12/15.
 */
public class Projectile {

    private int x = 0;
    private int y = 0;
    private GameView gameView;
    private Bitmap bmp;
    private int width;
    private int height;
    private int ySpeed;
    private List<Projectile> temps;

    public Projectile(List<Projectile> temps, GameView gameView, float x, float y, Bitmap bitmap){
        this.gameView = gameView;
        bmp = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.x = (int) x + Avion.getWidth()/2 - width/2;
        this.y = (int) y;
        ySpeed = 100;
        this.temps = temps;
    }

    public void update() {
        if((y <= Roche.getY() + Roche.getHeight() && y >= Roche.getY() && x >= Roche.getX() - width / 2
                && x <= Roche.getX() + Roche.getWidth()) || (Math.abs(Roche.getY() - y) <= Math.abs(Roche.getYSpeed() + ySpeed) &&
                x >= Roche.getX() - width / 2
                && x <= Roche.getX() + Roche.getWidth())) {
            temps.remove(this);
            gameView.boom();
        }else if(y > 0) {
            y -= ySpeed;
        }else if(y <= 0){
            temps.remove(this);
        }
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }


/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
* /////////////////////////////////////////////                  X, Y, HEIGHT, WIDTH                 //////////////////////////////////////////////
* ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

   /* public static void setY(int y){
        Projectile.y = y;
    }

    public static void setX(int x){
        Projectile.x = x;
    }*/

    public int getySpeed(){
        return ySpeed;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}
