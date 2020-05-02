package com.example.cosmeticsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        final EditText username=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText nickname=(EditText)findViewById(R.id.nickname);
        Button buttonReg=(Button) findViewById(R.id.signup);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser=username.getText().toString();
                String newPassword=password.getText().toString();
                String newNickname=nickname.getText().toString();
                if(username.getText().length()<=0){
                    Toast.makeText(SignupActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(password.getText().length()<=0){
                    Toast.makeText(SignupActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(nickname.getText().length()<=0){
                    Toast.makeText(SignupActivity.this, "Enter nickname", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.putExtra("emailKey", newUser);
//                intent.putExtra("passKey", newPassword);

                Toast.makeText(SignupActivity.this, "User is signed up", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

}
