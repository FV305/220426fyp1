package com.example.fyp_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class php_signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText editTextUsername, editTextemail, editTextPassword;
    Button btn_signup;
    Spinner editTextWeakness;
    ImageButton btn_male,btn_female;
    boolean isPressed = true;
    String gender_selected = "Male";
    TextView btn_gotoLogin;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_acc);

        editTextWeakness = findViewById(R.id.body_part_spinner);
        editTextWeakness.setOnItemSelectedListener(this);
        editTextUsername = (EditText)findViewById(R.id.create_name);
        editTextemail = (EditText)findViewById(R.id.create_email);
        editTextPassword = (EditText)findViewById(R.id.create_password);
        btn_male = findViewById(R.id.btn_male);
        btn_female = findViewById(R.id.btn_female);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_gotoLogin = (TextView)findViewById(R.id.btn_gotoLogin);

        btn_gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), php_login.class);
                startActivity(intent);
                finish();
            }
        });

        String[] bodyPart = getResources().getStringArray(R.array.body_part);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, bodyPart);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextWeakness.setAdapter(adapter);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email, password, weakness;
                String gender;
                username = String.valueOf(editTextUsername.getText());
                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextPassword.getText());
                weakness = editTextWeakness.getSelectedItem().toString();
                gender = gender_selected;

                if (!username.equals("") && !email.equals("") && !password.equals("") && !weakness.equals("") && !gender.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "username";
                            field[1] = "email";
                            field[2] = "password";
                            field[3] = "gender";
                            field[4] = "weakness";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = username;
                            data[1] = email;
                            data[2] = password;
                            data[3] = gender;
                            data[4] = weakness;
                            PutData putData = new PutData("http://192.168.64.2/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals( "Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), php_login.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields requried", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.body_part_spinner) {
            String valueFromSpinner = parent.getItemAtPosition(position).toString();
            //get spinner value
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setGenderButton(View view) {
        if (view.equals(btn_male)){
            btn_male.setBackgroundResource(R.drawable.btn_circle_selected);
            btn_female.setBackgroundResource(R.drawable.btn_circle);
            gender_selected = "Male";

        }else{
            btn_female.setBackgroundResource(R.drawable.btn_circle_selected);
            btn_male.setBackgroundResource(R.drawable.btn_circle);
            gender_selected = "Female";
        }
    }

}
