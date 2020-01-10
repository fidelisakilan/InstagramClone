package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUplogin_activity extends AppCompatActivity {
   private EditText edtUserNameSignup,edtUserNameLogin,edtPasswordSignup,edtPasswordLogin;
   private Button btnSignUp,btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtUserNameSignup=findViewById(R.id.signupname);
        edtUserNameLogin=findViewById(R.id.signinname);
        edtPasswordLogin=findViewById(R.id.signinpass);
        edtPasswordSignup=findViewById(R.id.signuppass);
        btnSignUp=findViewById(R.id.signupbut);
        btnLogin=findViewById(R.id.signinbut);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser=new ParseUser();
                appUser.setUsername(edtUserNameSignup.getText().toString());
                appUser.setPassword(edtPasswordSignup.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(SignUplogin_activity.this, appUser.get("username")+" is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            Intent intent=new Intent(SignUplogin_activity.this,WelcomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            FancyToast.makeText(SignUplogin_activity.this, appUser.get("username")+" is not signed up", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, false).show();
                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null && user!=null){
                        Intent intent=new Intent(SignUplogin_activity.this,WelcomeActivity.class);
                        startActivity(intent);
                        FancyToast.makeText(SignUplogin_activity.this,user.get("username")+" is signed in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                    }else{
                        FancyToast.makeText(SignUplogin_activity.this, user.get("username")+" is not signed in );", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, false).show();
                    }
                }
            });
            }
        });


    }
}
