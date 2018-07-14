package com.example.hamza.flythedrogo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.TextView;
import android.content.Intent;
import android.app.AlertDialog;

public class gameStart extends AppCompatActivity{

    private GameView gameView;
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30;

        //TextView namedb = (TextView) findViewById(R.id.Name_DB);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
       //String value1 = super.getIntent().getExtras().getString("<Name_db>");
       // namedb.setText(value1);
        gameView = (GameView) findViewById(R.id.signature_canvas);


        //gameView = new GameView(this);

        //setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, TIMER_INTERVAL);

    }



}
