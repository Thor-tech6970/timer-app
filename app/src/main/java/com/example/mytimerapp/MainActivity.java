package com.example.mytimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;

    TextView textView;

    MediaPlayer mediaPlayer;

    Button startButton;

    boolean countdownIsActive = false;

    CountDownTimer countDownTimer;

    public  void start(View view){

        if(countdownIsActive == true){

            mediaPlayer.pause();

            seekBar.setEnabled(true);

            seekBar.setProgress(30);

            startButton.setText("START");

            textView.setText("00:30");

            countDownTimer.cancel();

            countdownIsActive = false;


        }

        else{

            seekBar.setEnabled(false);

            startButton.setText("STOP !");

            countdownIsActive= true;

          countDownTimer = new CountDownTimer(seekBar.getProgress()*1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                //Log.i("Info",+millisUntilFinished/1000 +"seconds left.");

                seekBar.setProgress((int)millisUntilFinished/1000);



            }

            @Override
            public void onFinish() {

                //Log.i("Info2", "Finished!");

                mediaPlayer.start();





            }
        }.start();

    }}


    public void updateTime(int progress){

        int minutes = progress/60;

        int seconds = progress - (minutes*60);

        textView.setText(+minutes + ":" +seconds) ;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.himym);

        startButton = (Button) findViewById(R.id.startButton);

        textView = (TextView) findViewById(R.id.textView);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setMax(900);

        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });








    }
}
