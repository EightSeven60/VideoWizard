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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.buttonLoginL);
        EditText tEditEmail = findViewById(R.id.tEditEmailL);
        EditText tEditPassword = findViewById(R.id.tEditPasswordL);
        Button registerButton = findViewById(R.id.buttonRegisterL);

        CurrentUser.reset();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tEditEmail.getText().toString();
                String password = tEditPassword.getText().toString();
                Toast.makeText(LoginActivity.this, "Contacting server", Toast.LENGTH_LONG).show();

                SendToServerAsyncTask sendToServerAsyncTask = new SendToServerAsyncTask();
                sendToServerAsyncTask.execute("Login" + CurrentUser.separator + email + CurrentUser.separator + password,
                        "192.168.100.3",
                        "8000");

                while (CurrentUser.Email == null ||
                        CurrentUser.Email.equals("")) {
                    Thread.yield();
                }

                startActivity(new Intent(LoginActivity.this, VideoFeedActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}