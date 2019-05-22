package com.example.myapplication3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton[] list;
    TextView text;
    Button del;
    ImageButton play;
    MediaPlayer mp;
    String tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        del = findViewById(R.id.del);
        del.setOnClickListener(this);
        play = findViewById(R.id.play);
        play.setOnClickListener(this);
        mp  = MediaPlayer.create(this, R.raw.test);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.release();
            }
        });
        list =  new ImageButton[]{
                findViewById(R.id.b1),
                findViewById(R.id.b2),
                findViewById(R.id.b3),
                findViewById(R.id.b4),
                findViewById(R.id.b5),
                findViewById(R.id.b6),
                findViewById(R.id.b7),
                findViewById(R.id.b8),
                findViewById(R.id.b9),
                findViewById(R.id.b10),
                findViewById(R.id.b11),
                findViewById(R.id.b12),
                findViewById(R.id.b13),
                findViewById(R.id.b14),
                findViewById(R.id.b15),
                findViewById(R.id.b16),
                findViewById(R.id.b17),
                findViewById(R.id.b18),
                findViewById(R.id.b19),
                findViewById(R.id.b20)
        };

        for (int i=0; i < 20;i++){
            list[i].setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b1:
                tmp = text.getText().toString();
                text.setText(tmp+"Я хочу ");
                break;
            case R.id.b2:
                tmp = text.getText().toString();
                text.setText(tmp+"Я не хочу ");
                break;
            case R.id.b3:
                tmp = text.getText().toString();
                text.setText(tmp+"Мне жарко ");
                break;
            case R.id.b4:
                tmp = text.getText().toString();
                text.setText(tmp+"Мне холодно ");
                break;
            case R.id.b5:
                tmp = text.getText().toString();
                text.setText(tmp+"Да ");
                break;
            case R.id.b6:
                tmp = text.getText().toString();
                text.setText(tmp+"Нет ");
                break;
            case R.id.b7:
                tmp = text.getText().toString();
                text.setText(tmp+"Мне нравиться ");
                break;
            case R.id.b8:
                tmp = text.getText().toString();
                text.setText(tmp+"Мне не нравиться ");
                break;
            case R.id.b9:
                tmp = text.getText().toString();
                text.setText(tmp+"воду ");
                break;
            case R.id.b10:
                tmp = text.getText().toString();
                text.setText(tmp+"молоко ");
                break;
            case R.id.b11:
                tmp = text.getText().toString();
                text.setText(tmp+"чай ");
                break;
            case R.id.b12:
                tmp = text.getText().toString();
                text.setText(tmp+"сок ");
                break;
            case R.id.b13:
                tmp = text.getText().toString();
                text.setText(tmp+"яблоко ");
                break;
            case R.id.b14:
                tmp = text.getText().toString();
                text.setText(tmp+"арбуз ");
                break;
            case R.id.b15:
                tmp = text.getText().toString();
                text.setText(tmp+"клубника ");
                break;
            case R.id.b16:
                tmp = text.getText().toString();
                text.setText(tmp+"морковь ");
                break;
            case R.id.b17:
                tmp = text.getText().toString();
                text.setText(tmp+"курицу ");
                break;
            case R.id.b18:
                tmp = text.getText().toString();
                text.setText(tmp+"рыбу ");
                break;
            case R.id.b19:
                tmp = text.getText().toString();
                text.setText(tmp+"суп ");
                break;
            case R.id.b20:
                tmp = text.getText().toString();
                text.setText(tmp+"хлеб ");
                break;
            case R.id.del:
                text.setText("");
                break;
            case R.id.play:
                mp.start();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), RightLeftActivity.class);
        startActivity(intent);
        finish();
    }
}
