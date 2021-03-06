package com.example.admin.corneronahorizontalbar;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    private Chronometer mChronometer;
    private int sound;
    private SoundPool soundPool;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createSoundPoolWithBuilder();
        } else {
            createSoundPoolWithConstructor();
        }

        soundPool.setOnLoadCompleteListener(this);
        sound = soundPool.load(this, R.raw.click, 1);

        /*Date date = new Date();
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(date.toString());*/



        mChronometer = (Chronometer) findViewById(R.id.chronometer);

/*        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - mChronometer.getBase();

                if (elapsedMillis > 5000) {
                    String strElapsedMillis = "Прошло больше 5 секунд";
                    Toast.makeText(getApplicationContext(),
                            strElapsedMillis, Toast.LENGTH_SHORT)
                            .show();

            }
        });*/
    }

   public void onStartClick(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    public void onStopClick(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        mChronometer.stop();
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        int month = time.month + 1;
        String sTime = time.monthDay + "." + month + "." + time.year;
        /*textView = (TextView) findViewById(R.id.textView);
        textView.setText(sTime);*/

        String sChronometer = mChronometer.getText().toString();
        Contact contact = new Contact(sTime, sChronometer);
        contact.save();
    }


    public void onBaseClick(View view) {
        soundPool.play(sound, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, ListWorkout.class);
        startActivity(intent);
    }


    // sound
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createSoundPoolWithBuilder() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(1).build();
    }

    @SuppressWarnings("deprecation")
    protected void createSoundPoolWithConstructor() {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    }


    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

    }
}