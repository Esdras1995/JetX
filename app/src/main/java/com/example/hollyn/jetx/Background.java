package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by root on 3/24/15.
 */
public class Background extends View {
    private static Bitmap back;
    private static int posx = -30;
    private int posy = 0;
    private int width;
    private int height;
    private int init, compte = 0;
    private boolean shake = false;
    private Rect rect;
    private int speed = 20;
    private static boolean isRunning = true;

    public Background(Context context){
        super(context);

        back = BitmapFactory.decodeResource(getResources(), R.drawable.backtest);
        rect = new Rect();
        int newHeight = getScreenDimensions(context).y;
        int newWidth = getScreenDimensions(context).x;
        back = getResizedBitmap(back, newHeight+30, newWidth+30);
        init = -back.getHeight();
    }

    public void ondraw(Canvas canvas){
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        if(isRunning)
        {
            vol();

            rect.set(0, 0, canvas.getWidth(), canvas.getHeight());
            canvas.drawRect(rect, paint);
            canvas.drawBitmap(back, posx, init, null);
            canvas.drawBitmap(back, posx, posy, null);
            invalidate();
        }
    }

    public void vol(){

        if(posy < height)
            posy +=speed;

        if(init < height)
            init += speed;

        if(posy >= height)
            posy = init - back.getHeight();

        if(init >= height)
            init = posy - back.getHeight();

        if(shake && compte < 10){

            if(compte%2 == 0)
                posx = 10;

            else
                posx = -10;

            compte++;
        }else{
            compte = 0;
            shake = false;
        }
    }

    public void shakeBack(boolean bool){

        shake = bool;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth){

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;


    }

    public static Point getScreenDimensions(Context context) {

        int width = context.getResources().getDisplayMetrics().widthPixels;

        int height = context.getResources().getDisplayMetrics().heightPixels;

        return new Point(width, height);
    }
}