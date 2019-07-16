package com.example.andelaandroidchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button alcButton, myProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Opening the ALC About Page

        alcButton = findViewById(R.id.about_alc_button);
        alcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutALC();
            }
        });

        // Opening My Profile Page

        myProfileButton = findViewById(R.id.my_profile_button);
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyProfile();
            }
        });

    }

    private void openMyProfile() {
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }


    private void openAboutALC() {
        Intent intent = new Intent(this, AboutALCActivity.class);
        startActivity(intent);
    }


}
