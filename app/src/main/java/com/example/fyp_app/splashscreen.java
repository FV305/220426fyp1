package com.example.fyp_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class splashscreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Button login_button = (Button) findViewById(R.id.btn_login);
        TextView splash_createAcc = (TextView) findViewById(R.id.splash_createAcc);
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(splashscreen.this, php_login.class);
                startActivity(intent);
            }
        });
        splash_createAcc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                Intent i = new Intent(getApplicationContext(), php_signup.class);
                startActivity(i);
                return false;
            }
        });

    }
}
