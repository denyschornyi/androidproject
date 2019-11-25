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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText etUsername , etPassword;
    TextView tvZarejestruj;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etUsername = findViewById(R. id.etUsername);
        etPassword = findViewById(R. id.etPassword);
        tvZarejestruj = findViewById(R. id.tvZarejestruj);
        bLogin = findViewById(R. id.bLogin);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(Login.this, "Jestes zalogowany", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Menu.class);
                    startActivity(i) ;
                }
                else {
                    Toast.makeText(Login.this, "Prosze zalogowac sie", Toast.LENGTH_SHORT).show();
                }
            }
        };

        bLogin.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(Login.this, "Pola sa puste!" , Toast.LENGTH_SHORT).show();
                } else if(!(email.isEmpty()  && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd) .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Login.this, "Blad Logowania, Prosze zalogowac jeszcze raz" , Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intToHome = new Intent(Login.this, Menu.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this, "Blad systemowy" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvZarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(Login.this, Register.class);
                startActivity(intSignUp);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
