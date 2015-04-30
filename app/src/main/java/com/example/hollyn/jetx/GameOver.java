package com.example.hollyn.jetx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hollyn on 3/11/15.
 */
public class GameOver extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        TextView score = (TextView)findViewById(R.id.score);
        TextView coin = (TextView)findViewById(R.id.viewcoingained);
        TextView distance = (TextView)findViewById(R.id.viewdistancereached);
        TextView rockdestroyed = (TextView)findViewById(R.id.viewrochedestroyed);

        score.setText(getIntent().getExtras().getString("score"));
        coin.setText(getIntent().getExtras().getString("coin"));
        distance.setText(getIntent().getExtras().getString("distance"));
        rockdestroyed.setText(getIntent().getExtras().getString("rockdestroy"));

        Button button =(Button)findViewById(R.id.retry);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMainActivity();
            }
        });
    }

    public void returnToMainActivity(){
        super.onStop();
        startActivity(new Intent(GameOver.this, Game.class));
        finish();
    }

    /*@Override
    protected void onStop(){
        super.onStop();
        startActivity(new Intent(GameOver.this, Menu.class));
        finish();
    }*/
}
