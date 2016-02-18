package com.example.hollyn.jetx;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Hangar extends FragmentActivity {

    ViewPager viewPager;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        done = (Button) findViewById(R.id.done);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView bestScore =(TextView)findViewById(R.id.bestScore);
        TextView coins =(TextView)findViewById(R.id.coins);

        bestScore.setText(" : "+load("bestScore"));
        coins.setText(" : "+load("coin"));
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
                    fragment = new PlaneA();
                    break;

                case 1:
                    fragment = new PlaneB();
                    break;

                case 2:
                    fragment = new PlaneC();
                    break;

                case 3:
                    fragment = new PlaneD();
                    break;

                case 4:
                    fragment = new PlaneE();
                    break;

                case 5:
                    fragment = new PlaneF();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

}

