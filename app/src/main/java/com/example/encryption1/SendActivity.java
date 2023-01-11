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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SendActivity extends AppCompatActivity {

    EditText mailidET;
    EditText sendmessageTV;
    Button sendBTN;
    FirebaseAuth auth1;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase database;
    String userName;
    String otherUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        this.setTitle("Send message");

        mailidET = findViewById(R.id.mailidET);
        sendmessageTV = findViewById(R.id.sendmessageTV);
        sendBTN = findViewById(R.id.sendBTN);

        auth1 = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        user = auth1.getCurrentUser();

        reference.child("Users").child(user.getUid()).child("userName").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otherUser = mailidET.getText().toString();

                String message = sendmessageTV.getText().toString();
                if (!message.equals("")) {
                    sendMessage(message);
                    sendmessageTV.setText("");
                } else
                    Toast.makeText(SendActivity.this, "Message not sent successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendMessage(String message) {
        String key = reference.child("Inbox").child(userName).push().getKey();
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("from", userName);
        messageMap.put("message", message);
        reference.child("Inbox").child(otherUser).child(key).setValue(messageMap);
        Toast.makeText(SendActivity.this, "Message sent successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu, menu);
        menu.findItem(R.id.username).setTitle(userName);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            auth1.signOut();
            startActivity(new Intent(SendActivity.this, LoginActivity.class));
        }
        if (item.getItemId() == R.id.username) {
        }
        return super.onOptionsItemSelected(item);
    }
}