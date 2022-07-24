package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler mainHandler=new Handler();
    int count=0;
    boolean running=false;

    TextView counterValue;
    Button startBtn,stopBtn;
    void startThread()
    {
        NewThread nObj=new NewThread();
        nObj.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterValue=(TextView) findViewById(R.id.counterval);
        startBtn=(Button) findViewById(R.id.start);
        stopBtn=(Button) findViewById(R.id.stop);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=0;
                running=true;
                startThread();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running=false;
            }
        });

    }

     class NewThread extends Thread{
        @Override
         public void run() {
            while(running)
            {
                count++;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counterValue.setText(String.valueOf(count));
                    }
                });
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
