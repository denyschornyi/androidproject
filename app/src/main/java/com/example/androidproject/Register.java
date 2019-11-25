package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    Button bRegister;
    EditText etUsername , etPassword ;
    TextView tvToLogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etUsername = findViewById(R. id.etUsername);
        etPassword = findViewById(R. id.etPassword);
        tvToLogin = findViewById(R. id.tvToLogin);
        bRegister = findViewById(R. id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email  = etUsername.getText().toString();
                String pwd  = etPassword.getText().toString();
                if(email.isEmpty()){
                    etUsername.setError("Prosze wpisac swoj email");
                    etUsername.requestFocus();
                }else if(pwd.isEmpty()){
                    etPassword.setError("Prosze wpisac haslo");
                    etPassword.requestFocus();
                }else if(email.isEmpty()  && pwd.isEmpty()){
                    Toast.makeText(Register.this, "Pola sa puste!" , Toast.LENGTH_SHORT).show();
                } else if(!(email.isEmpty()  && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Register.this, "Logowanie nie udalo sie, Prosze sprobowac ponownie " , Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Register.this, Menu.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "Blad systemowy" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });
    }


}
