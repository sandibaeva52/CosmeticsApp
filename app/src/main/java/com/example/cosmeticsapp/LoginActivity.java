package com.example.cosmeticsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private String emailKey;
    private String passKey;
    private static  final String PREFER_NAME="pref";
    private static  final String KEY_EMAIL="email_key";
    private static  final String KEY_PASSWORD="pass_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final EditText username=(EditText) findViewById(R.id.email);
        final EditText password=(EditText) findViewById(R.id.password);

        SharedPreferences preferences=getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
        emailKey=preferences.getString(KEY_EMAIL,"");
        passKey=preferences.getString(KEY_PASSWORD,"");
        if(emailKey.length()>0 && passKey.length()>0){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        Button btnReg=(Button)findViewById(R.id.signup);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, SignupActivity.class)   ;
                startActivity(intent);
            }
        });
        Button btnLogin=(Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =username.getText().toString();
                String pass =password.getText().toString();
                if(user.length()<=0&&pass.length()<=0){
                    Toast.makeText(LoginActivity.this, "Enter email and password, please ", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences preferences=getSharedPreferences(PREFER_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(KEY_EMAIL, username.getText().toString());
                editor.putString(KEY_PASSWORD, password.getText().toString());
                editor.commit();
//                editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("emailKey", user);
                intent.putExtra("passKey", pass);
                Toast.makeText(LoginActivity.this, "User is logged in", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            }
        });




    }
}
