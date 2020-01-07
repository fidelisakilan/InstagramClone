package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText editName,editPunch,editKick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(SignUp.this);
        editName=findViewById(R.id.name);
        editKick=findViewById(R.id.kickid);
        editPunch=findViewById(R.id.punchid);
    }


    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        try {

            kickBoxer.put("name", editName.getText().toString());
            kickBoxer.put("punchSpeed", editPunch.getText().toString());
            kickBoxer.put("kickSpeed", editKick.getText().toString());
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " saved successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                    } else {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is not saved >.<", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                    }
                }
            });
        }catch (Exception e){
            FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is not saved >.<", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

        }
    }
}

