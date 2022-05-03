package com.example.fyp_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fyp_app.ui.home.HomeFragment;

public class turn_on_notifi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_on_notifi);

        Button login_button = (Button) findViewById(R.id.btn_login);

        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(turn_on_notifi.this, HomeFragment.class);
                startActivity(intent);
            }
        });

    }
}