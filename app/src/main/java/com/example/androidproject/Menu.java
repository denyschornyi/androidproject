package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    Button btnLogout, Camera , Grafika ;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Grafika = findViewById(R.id.Grafika);
        Grafika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grafika = new Intent(Menu.this, Slider
                        .class);
                startActivity(grafika);
            }
        });

        Camera = findViewById(R. id.Camera);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



        btnLogout = findViewById(R. id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Menu.this, MainActivity.class);
                startActivity(intToMain);
            }
        });
    }

    public void open (View view){
        Intent intentCam = new Intent();
        intentCam.setAction(Intent.ACTION_VIEW);
        intentCam.setData(Uri.parse("geo:51.232557, 22.557461"));
        startActivity(intentCam);
    }
}
