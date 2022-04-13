package com.example.vproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vproject.R;
import com.example.vproject.networking.SendToServerAsyncTask;
import com.example.vproject.user.CurrentUser;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonLogin = findViewById(R.id.buttonLoginR);
        Button buttonRegister = findViewById(R.id.buttonRegisterR);
        EditText emailEditText = findViewById(R.id.tEditEmailR);
        EditText nameEditText = findViewById(R.id.tEditNameR);
        EditText passwordEditText1 = findViewById(R.id.tEditPasswordR1);
        EditText passwordEditText2 = findViewById(R.id.tEditPasswordR2);

        CurrentUser.reset();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String password1 = passwordEditText1.getText().toString();
                String password2 = passwordEditText2.getText().toString();
                if (email.contains("@") &&
                        email.contains(".") &&
                        name.length() > 0 &&
                        password1.length() > 5 &&
                        password1.equals(password2)) {
                    Toast.makeText(RegisterActivity.this, "Contacting server", Toast.LENGTH_LONG).show();

                    SendToServerAsyncTask sendToServerAsyncTask = new SendToServerAsyncTask();
                    sendToServerAsyncTask.execute("Register" + CurrentUser.separator + email + CurrentUser.separator + name +
                                    CurrentUser.separator + password1 + CurrentUser.separator + "0",
                            "192.168.100.3",
                            "8000");

                    /*
                    RegisterReceiveAsyncTask registerReceiveAsyncTask = new RegisterReceiveAsyncTask();
                    registerReceiveAsyncTask.execute("8000");
                    */

                    while (CurrentUser.Email == null ||
                            CurrentUser.Email.equals("")) {
                        Thread.yield();
                    }

                    startActivity(new Intent(RegisterActivity.this, VideoFeedActivity.class));

                } else {
                    Toast.makeText(RegisterActivity.this, "Invalid user details", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}