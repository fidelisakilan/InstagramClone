package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave,getall,nexAct;
    private EditText editName,editPunch,editKick;
    private TextView txtdata;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.button);
        btnSave.setOnClickListener(SignUp.this);
        editName=findViewById(R.id.name);
        editKick=findViewById(R.id.kickid);
        editPunch=findViewById(R.id.punchid);
        txtdata=findViewById(R.id.txtgetdata);
        nexAct=findViewById(R.id.nextact);
        txtdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery= ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("wtsvqUD3MV", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object !=null && e==null){
                            txtdata.setText(object.get("name")+"-"+" Punch power"+object.get("punchSpeed"));
                        }
                    }
                });


            }
        });
        nexAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUp.this,SignUplogin_activity.class);
                startActivity(intent);
            }
        });
        getall=findViewById(R.id.getall);
        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers="";
                ParseQuery<ParseObject> queryall= ParseQuery.getQuery("KickBoxer");
                queryall.whereGreaterThan("punchSpeed",100);
                queryall.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size()>0){
                                for(ParseObject kickboxer :objects){
                                    allKickBoxers=allKickBoxers+kickboxer.get("name")+"\n";
                                }

                                FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                            }else{
                                FancyToast.makeText(SignUp.this, "Failed >.<", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            }
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        try {

            kickBoxer.put("name", editName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(editPunch.getText().toString()));
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

