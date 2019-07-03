package com.tournity.View;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.tournity.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         usernameEditText = findViewById(R.id.username);
         passwordEditText = findViewById(R.id.password);
         loginButton = findViewById(R.id.loginButton);
         registerButton = findViewById(R.id.registerButton);
         registerButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onRegisterClick();
             }
         });

    }

    private void onRegisterClick(){
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }


}
