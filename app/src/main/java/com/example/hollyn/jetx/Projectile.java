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

    public Projectile(List<Projectile> temps, GameView gameView, float x, float y, Bitmap bitmap, GameSound sound){
        this.gameView = gameView;
        bmp = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.x = (int) x + Avion.getWidth()/2 - width/2;
        this.y = (int) y;
        ySpeed = 100;
        this.temps = temps;
        sound.playSound("Projectile");
    }

    public void update() {
        if((y <= Rock1.getY() + Rock1.getHeight() && y >= Rock1.getY() && x >= Rock1.getX() - width / 2
                && x <= Rock1.getX() + Rock1.getWidth()) || (Math.abs(Rock1.getY() - y) <= Math.abs(Rock1.getYSpeed() + ySpeed) &&
                x >= Rock1.getX() - width / 2 && x <= Rock1.getX() + Rock1.getWidth())){
            temps.remove(this);
            gameView.boom(1);
        }else if((y <= Rock2.getY() + Rock2.getHeight() && y >= Rock2.getY() && x >= Rock2.getX() - width / 2
                && x <= Rock2.getX() + Rock2.getWidth()) || (Math.abs(Rock2.getY() - y) <= Math.abs(Rock2.getYSpeed() + ySpeed) &&
                x >= Rock2.getX() - width / 2 && x <= Rock2.getX() + Rock2.getWidth())){
            Rock2.isHit();
            temps.remove(this);
            gameView.boom(2);
        }else if((y <= Rock3.getY() + Rock3.getHeight() && y >= Rock3.getY() && x >= Rock3.getX() - width / 2
                && x <= Rock3.getX() + Rock3.getWidth()) || (Math.abs(Rock3.getY() - y) <= Math.abs(Rock3.getYSpeed() + ySpeed) &&
                x >= Rock3.getX() - width / 2 && x <= Rock3.getX() + Rock3.getWidth())) {
            Rock3.isHit();
            temps.remove(this);
            gameView.boom(3);
        }else if((y <= Rock4.getY() + Rock4.getHeight() && y >= Rock4.getY() && x >= Rock4.getX() - width / 2
                && x <= Rock4.getX() + Rock4.getWidth()) || (Math.abs(Rock4.getY() - y) <= Math.abs(Rock4.getYSpeed() + ySpeed) &&
                x >= Rock4.getX() - width / 2 && x <= Rock4.getX() + Rock4.getWidth())){
            Rock4.isHit();
            temps.remove(this);
            gameView.boom(4);
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
