package com.example.encryption1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import Algorithms.AES;
import Algorithms.Caesarcipher;
import Algorithms.DES;

public class DecryptActivity extends AppCompatActivity {
    Button nav_dnew_message;
    Button nav_send_message;
    Spinner selectionSpinner;
    EditText codedmessageET;
    EditText decryptionkeyET;
    Button decryptBTN;
    EditText decrpytmessageET;
    String[] algorithms = {"Advanced Encryption Standard","Triple Data Encryption Standard","Caesar Cipher"};

    FirebaseAuth auth4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
        this.setTitle("Decryption");

        auth4 = FirebaseAuth.getInstance();

        nav_dnew_message = findViewById(R.id.dnewmsgBTN);
        nav_send_message = findViewById(R.id.dsendBTN);
        selectionSpinner = findViewById(R.id.dselectionSPINNER);
        decryptBTN = findViewById(R.id.decrpytionBTN);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DecryptActivity.this, android.R.layout.simple_spinner_item, algorithms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectionSpinner.setAdapter(adapter);

        selectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String algorithm = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        nav_dnew_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent01 = new Intent(DecryptActivity.this, EncryptActivity.class);
                startActivity(intent01);
            }

        });
        nav_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent02 = new Intent(DecryptActivity.this, SendActivity.class);
                startActivity(intent02);
            }
        });
    }

    public void decrypt(View view) throws Exception {
        codedmessageET = findViewById(R.id.dmessageET);
        decryptionkeyET = findViewById(R.id.dkeyET);
        decrpytmessageET = findViewById(R.id.decryptedmessageET);

        if (codedmessageET.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a message to Decrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        if (decryptionkeyET.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a Key for Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = codedmessageET.getText().toString();
        String key = decryptionkeyET.getText().toString();
        String algorithm = selectionSpinner.getSelectedItem().toString();

        switch (algorithm) {
            case "Advanced Encryption Standard":
                AES aes = new AES();
                try {
                    String decData = aes.AESdecrypt(key, Base64.decode(message.getBytes("UTF-16LE"), Base64.DEFAULT));
                    decrpytmessageET.setText(decData);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            case "Triple Data Encryption Standard":
                DES des = new DES();
                try {
                    String decData = des.decrypt(key, Base64.decode(message.getBytes("UTF-16LE"), Base64.DEFAULT));
                    decrpytmessageET.setText(decData);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            case "Caesar Cipher":
                if (key.length() == 0) {
                    Toast.makeText(view.getContext(), "Enter a key", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(key) >= 26) {
                    Toast.makeText(view.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                Caesarcipher c = new Caesarcipher();
                decrpytmessageET.setText(c.caesarcipherDec(message, Integer.parseInt(key)));
                break;
        }

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
            auth4.signOut();
            startActivity(new Intent(DecryptActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}