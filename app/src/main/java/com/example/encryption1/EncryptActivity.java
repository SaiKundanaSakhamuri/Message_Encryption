package com.example.encryption1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class EncryptActivity extends AppCompatActivity {

    Button nav_new_message;
    Spinner selectionSpinner;
    EditText plainmessageET;
    EditText keyET;
    EditText encryptmessageET;
    Button copy;
    Button encrypt;
    String[] algorithms = {"Advanced Encryption Standard","Triple Data Encryption Standard","Caesar Cipher"};

    FirebaseAuth auth3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        this.setTitle("Encryption");

        auth3 = FirebaseAuth.getInstance();

        nav_new_message = findViewById(R.id.enewmsgBTN);
        selectionSpinner = findViewById(R.id.eselectionSPINNER);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EncryptActivity.this, android.R.layout.simple_spinner_item, algorithms);
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

        nav_new_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent  (EncryptActivity.this,SendActivity.class);
                startActivity(intent);

            }
        });
    }
    public void encrypt(View view) throws Exception {
        plainmessageET = findViewById(R.id.emessageET);
        keyET = findViewById(R.id.ekeyET);
        encryptmessageET = findViewById(R.id.encryptedmessageET);

        String message = String.valueOf(plainmessageET.getText());
        String key = String.valueOf(keyET.getText());
        String algorithm = selectionSpinner.getSelectedItem().toString();

        if (plainmessageET.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a message to Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        if (keyET.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a Key for Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }


        switch (algorithm) {
            case "Advanced Encryption Standard":
                AES aes = new AES();
                String enc = aes.AESencrypt(key.getBytes("UTF-16LE"), message.getBytes("UTF-16LE"));
                encryptmessageET.setText(enc);
                break;
            case "Triple Data Encryption Standard":
                DES des = new DES();
                String encData = des.encrypt(key.getBytes("UTF-16LE"), message.getBytes("UTF-16LE"));
                encryptmessageET.setText(encData);
                break;
            case "Caesar Cipher":
                if (key.isEmpty()) {
                    Toast.makeText(view.getContext(), "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (key.length() > 26) {
                    Toast.makeText(view.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                Caesarcipher c = new Caesarcipher();
                encryptmessageET.setText(c.caesarcipherEnc(message, Integer.parseInt(key)));
                break;
        }
    }


    public void copyToClipboard(View view) {
        encryptmessageET = findViewById(R.id.encryptedmessageET);

        String copyText = String.valueOf(encryptmessageET.getText());
        if (encryptmessageET.length() == 0) {
            Toast.makeText(view.getContext(), "There is no message to copy", Toast.LENGTH_SHORT).show();
            return;
        }

        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                    view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(copyText);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData
                    .newPlainText("Your message :", copyText);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(view.getContext(),
                "Your message has be copied", Toast.LENGTH_SHORT).show();
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
            auth3.signOut();
            startActivity(new Intent(EncryptActivity.this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}