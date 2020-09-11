package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText usern, email, pass;
    Button reg;
    FirebaseAuth fba;
    DatabaseReference dbr;
 //   private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usern=findViewById(R.id.username);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        reg=findViewById(R.id.register);

        fba=FirebaseAuth.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();*/

                String u1=usern.getText().toString();
                String e1=email.getText().toString();
                String p1=pass.getText().toString();


                if (TextUtils.isEmpty(u1) || TextUtils.isEmpty(e1) || TextUtils.isEmpty(p1)){
                    Toast.makeText(RegisterActivity.this,"All fields are required!",Toast.LENGTH_SHORT).show();
                }else if(p1.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password must have atleast 6 characters",Toast.LENGTH_SHORT).show();
                }else{
                    register(u1, e1,p1);
                }
            }
        });



    }

    private void register(final String u, String e, String p){

        fba.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser fbu=fba.getCurrentUser();
                    String userid= fbu.getUid();
                    dbr= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String,String> hashMap= new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",u);
                    hashMap.put("imageURL","default");
                    hashMap.put("status","offline");
                    hashMap.put("search",u.toLowerCase());

                    dbr.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this,"Registerted Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, Main2Activity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }else{
            }}
        });


    }
}
