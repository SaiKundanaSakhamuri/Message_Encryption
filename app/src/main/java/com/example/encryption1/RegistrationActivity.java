package com.example.encryption1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegistrationActivity extends AppCompatActivity {
    Button register;
    EditText regusername, email, regpassword, confirmpswd;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.setTitle("User Registration");

        register = findViewById(R.id.registerBTN);
        regusername = findViewById(R.id.regusernameTV);
        email = findViewById(R.id.emailTV);
        regpassword = findViewById(R.id.regpasswordTV);
        confirmpswd = findViewById(R.id.confirmpswdTV);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = regusername.getText().toString();
                String emailForReg = email.getText().toString();
                String password = regpassword.getText().toString();
                String confirmpasword = confirmpswd.getText().toString();

                if (!username.equals("") && !emailForReg.equals("") && !password.equals("") && !confirmpasword.equals("")) {
                    if (!password.equals(confirmpasword)) {
                        Toast.makeText(RegistrationActivity.this, "Password and Confirm Password are not same", Toast.LENGTH_SHORT).show();
                    } else
                        signup(emailForReg, password, username);
                }
            }
        });
    }

    public void signup(String email, String pswd, String username) {
        auth.createUserWithEmailAndPassword(email, pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    reference.child("Users").child(auth.getUid()).child("userName").setValue(username);

                    Intent int_nav = new Intent(RegistrationActivity.this, LoginActivity.class);
                    int_nav.putExtra("userName", username);
                    startActivity(int_nav);
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "There is a problem.Check the fields again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout){
            auth.signOut();
            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}