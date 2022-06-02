package com.example.stopwach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    //Number of seconds displayed on the stopwatch
    private int seconds = 0;//Segundos
    //Is the stopwatch running?
    private  boolean running;
    private  boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    @Override
    protected void onPause(){//Pausado el tiempo
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onResume(){//Continuar
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view){running = true;}//Inician, para y reinician el tiempo
    //Stop the stopwatch running when the stop button is clicked.
    public void onClickStop(View view){running = false;}
    //Reset the stopwatch when the stop button is clicked.
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }
    //Sets the number of seconds on the timer.
    private void runTimer(){///Se encarga de poner numeros en la pantalla
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
                    public void run(){
                int hours = seconds/3600;//Convertir segundos a horas
                int ninutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, ninutes, secs);
                timeView.setText(time);//String ya fomrateado
                if(running){
                    seconds++;//Aumenta los segundos
                }
                handler.postDelayed(this,100);//Se le agrega unos milisegundos.
            }
        });
    }
}