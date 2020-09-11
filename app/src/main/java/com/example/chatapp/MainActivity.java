package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button l, r;
    FirebaseUser fbu;

    @Override
    protected void onStart() {
        super.onStart();

        fbu= FirebaseAuth.getInstance().getCurrentUser();
        if (fbu!=null){
            Toast.makeText(MainActivity.this, "You're logged in",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();

        }
        else{
            Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbu= FirebaseAuth.getInstance().getCurrentUser();




        l = findViewById(R.id.login);
        r=findViewById(R.id.register);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }
}
