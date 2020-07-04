package com.rishistech.onlinecart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rishistech.onlinecart.HomePage;
import com.rishistech.onlinecart.R;

public class LoginRegisterActivity extends AppCompatActivity {
    //initialization
    Button tpLogin_btn;
    TextView tpRegister_btn;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent i = new Intent(LoginRegisterActivity.this, HomePage.class);
            startActivity(i);
            finish();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        //binding
        tpLogin_btn = findViewById(R.id.tpLogin_btn);
        tpRegister_btn = findViewById(R.id.tpRegister_btn);
        firebaseAuth = FirebaseAuth.getInstance();


        tpLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginRegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        tpRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginRegisterActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
