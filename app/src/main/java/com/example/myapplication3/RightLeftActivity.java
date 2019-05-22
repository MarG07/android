package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RightLeftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_left);
    }

    public void ChatStart(View view) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(intent);
        finish();
    }

    public void MainStart(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
