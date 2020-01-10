package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView txtwelcome=findViewById(R.id.txtWelcome);
        txtwelcome.setText("Welcome "+ ParseUser.getCurrentUser().get("username"));
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logOut();
                finish();
                FancyToast.makeText(WelcomeActivity.this,"Logged out successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

            }
        });
    }
}
