package com.example.hollyn.jetx;

/**
 * Created by hollyn on 4/1/15.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Shop extends FragmentActivity {

    ViewPager viewPager;
    ImageButton done;
    public static boolean PAUSE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        viewPager = (ViewPager)findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));
        done = (ImageButton)findViewById(R.id.done);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       TextView diamand =(TextView)findViewById(R.id.diamand);
        TextView coins =(TextView)findViewById(R.id.coins);

        diamand.setText(" "+load("diamand"));
        coins.setText(" "+load("coin"));
    }

    public int load(String data) {
        SharedPreferences sharedPreferences = getSharedPreferences("mdata", Context.MODE_PRIVATE);
        int loaded = sharedPreferences.getInt(data, 0);
        return loaded;
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new PlaneFuschsia();
                    break;

                case 1:
                    fragment = new PlaneLemon();
                    break;

                case 2:
                    fragment = new PlaneNavy();
                    break;

                case 3:
                    fragment = new PlaneTeal();
                    break;

                case 4:
                    fragment = new PlaneRed();
                    break;

                case 5:
                    fragment = new PlaneGreen();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        PAUSE = true;
    }

    @Override
    public void onResume(){
        super.onPause();
        ContentMenu.play(true);
    }

}
