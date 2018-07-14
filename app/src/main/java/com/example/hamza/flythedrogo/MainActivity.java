package com.example.hamza.flythedrogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
     public EditText Name;
     public TextView GN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(this);

       // Button hsdbButton = (Button) findViewById(R.id.hsdb);
       // hsdbButton.setOnClickListener(this);

    }

     public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                Name = (EditText) findViewById(R.id.Name_Ans);
                String Name_db = Name.getText().toString();
             //GN = (TextView) findViewById(R.id.Name_DB);
             //GN.setText("Name:" + Name_db);
            Intent newgame = new Intent(this,gameStart.class);
           // newgame.putExtra("Key", Name_db);
            startActivity(newgame);

                break;
            //case R.id.hsdb:
            //Intent hsdb = new Intent(this,highScoreDB.class);
            //startActivity(hsdb);
                //break;
        }
    }


}
