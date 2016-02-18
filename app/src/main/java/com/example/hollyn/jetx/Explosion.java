package com.example.hollyn.jetx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by hollyn on 3/10/15.
 */
public class Explosion {

    private static final int BMP_WIDTH = 40;
    private int x;
    private int y;
    private GameView gameView;
    private Bitmap bmp;
    private int width;
    private int height;
    private int currentFrame;
    private int life = 39;
    private List<Explosion> temps;
    private GameSound sound;

    public Explosion(List<Explosion> temps, GameView gameView, int x, int y, Bitmap bitmap, GameSound sound){
        this.gameView = gameView;
        bmp = bitmap;
        width = bitmap.getWidth() / BMP_WIDTH;
        height = bitmap.getHeight();
        this.x = x;
        this.y = y;
        this.temps = temps;
        sound.playSound("RocheExplosion");
        //sound.playFx("RocheExplosion", 15, 15);
    }

    public void update(){
        currentFrame = ++currentFrame % BMP_WIDTH;
        if(--life < 1)
            temps.remove(this);
    }

    public void onDraw(Canvas canvas){
        update();
        int srcX = currentFrame * width;
        int srcY = 1;
        Rect s = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect d = new Rect(x, y, x+width, y+height);
        canvas.drawBitmap(bmp, s, d, null);
    }
}
