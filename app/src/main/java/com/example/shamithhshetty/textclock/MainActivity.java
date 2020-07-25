package com.example.shamithhshetty.textclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.textclock2.TextClock;

public class MainActivity extends AppCompatActivity {

    TextClock textClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textClock=findViewById(R.id.txt);
        //textClock.startClock();
    }
    public void clickStart(View view){
        //textClock.setSeconds(200);
        textClock.startClock();
    }
    public void clickReset(View view){
        textClock.resetClock();
    }
    public void clickPause(View view){
        textClock.pauseClock();
    }
    public void clickResume(View view){
        textClock.resumeClock();
    }

}
