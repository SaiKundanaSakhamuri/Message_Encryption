package com.example.encryption1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button nav_register,login;
    EditText username, password;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        nav_register = findViewById(R.id.createBTN);
        login = findViewById(R.id.loginBTN);
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);

        auth = FirebaseAuth.getInstance();

        nav_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent (LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String userpassword = password.getText().toString();
                if(!email.equals("") && !password.equals("")){
                    signin(email,userpassword);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please enter the Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signin(String email, String password){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent (LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Login is Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}