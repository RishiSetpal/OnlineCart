package com.rishistech.onlinecart;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class helpActivity extends AppCompatActivity {
    ImageButton backRegister_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        backRegister_btn2 = findViewById(R.id.backRegister_btn2);
        backRegister_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(helpActivity.this,LoginRegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
