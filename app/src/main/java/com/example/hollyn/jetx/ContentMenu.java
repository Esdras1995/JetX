package com.example.hollyn.jetx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by hollyn on 5/6/15.
 */
public class ContentMenu extends Fragment {

    ImageButton btnPlay;
    ImageButton btnOption;
    ImageButton btnShop;
    Button btnHelp;
    private static GameSound sound;
    private static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_menu, container, false);


        btnPlay = (ImageButton)view.findViewById(R.id.play);
        btnOption = (ImageButton)view.findViewById(R.id.option);
        btnShop = (ImageButton)view.findViewById(R.id.shop);
        //btnHelp = (Button)view.findViewById(R.id.help);

        context = getActivity();
        sound = new GameSound(getActivity());
        sound.init(R.raw.menu);
        play(load("music"));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.playSound("ButtonSound");
                startActivity(new Intent(getActivity(), Game.class));

            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.playSound("ButtonSound");
                startActivity(new Intent(getActivity(), Option.class));
                Option.PAUSE = false;
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound.playSound("ButtonSound");
                startActivity(new Intent(getActivity(), Shop.class));
                Shop.PAUSE = false;
            }
        });
    }

    public static Context getMenuContext(){
        return context;
    }

    @Override
    public void onPause(){
        super.onPause();
        if(Option.PAUSE == true && Shop.PAUSE == true && Credits.PAUSE == true)
            sound.pauseMusic();
    }

    @Override
    public void onResume(){
        super.onResume();
        //sound = new GameSound(getActivity(), R.raw.menu);
       // sound.init(R.raw.menu);
        GameView.pause();
        if(!sound.isPlayingMusic())
            sound.playMusic();

    }

    public static boolean load(String data){

        SharedPreferences sharedPreferences = context.getSharedPreferences("mdata", Context.MODE_PRIVATE);
        boolean loaded = sharedPreferences.getBoolean(data, false);

        return loaded;
    }

    public static void play(boolean b){
            if(b){
                sound.playMusic();
            }

    }

    public static void pause(){
       // if(sound.isPlayingMusic())
            sound.pauseMusic();
    }
}
