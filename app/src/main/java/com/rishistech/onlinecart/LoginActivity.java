package com.rishistech.onlinecart;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    ImageButton back_btn;
    EditText emailLogin_txt,passwordLogin_txt;
    Button login_btn;
    TextView tpRegister_btn;
    TextView needHelps_btn;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent i = new Intent(LoginActivity.this, HomePage.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        back_btn = findViewById(R.id.back_btn);
        emailLogin_txt =  findViewById(R.id.emailLogin_txt);
        passwordLogin_txt = findViewById(R.id.passwordLogin_txt);
        login_btn = findViewById(R.id.login_btn);
        tpRegister_btn = findViewById(R.id.tpRegister2_btn);
        needHelps_btn = findViewById(R.id.needHelps_btn);
        firebaseAuth = FirebaseAuth.getInstance();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, LoginRegisterActivity.class);
                startActivity(i);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin_txt.getText().toString();
                String password = passwordLogin_txt.getText().toString();
                if (email.length()==0){
                    emailLogin_txt.requestFocus();
                    emailLogin_txt.setError("Field can not be empty");
                }else if (password.length()==0){
                    passwordLogin_txt.requestFocus();
                    passwordLogin_txt.setError("Password can not be empty");
                }else{
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, HomePage.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(LoginActivity.this,
                                        "Invalid "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        tpRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        needHelps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,helpActivity.class);
                startActivity(i);
            }
        });
    }
}
