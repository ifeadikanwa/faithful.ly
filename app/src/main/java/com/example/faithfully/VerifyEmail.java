package com.example.faithfully;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyEmail extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    Button verify_button;
    TextView different_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        verify_button = findViewById(R.id.email_verify_button);
        different_email = findViewById(R.id.different_email);

        //on button click we want to check if the user has verified their email.
        verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.isEmailVerified()){
                    //TODO: if the email is verified we want to move to the ChooseReligion activity
                    startActivity(new Intent(VerifyEmail.this, ChooseReligion.class));
                }
                else{
                    //we want to notify the user that their email isnt verified yet
                    Toast.makeText(VerifyEmail.this, "Email has not been verified", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //on textview click we want to sign user out and go back to sign in activity
        different_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(VerifyEmail.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(VerifyEmail.this, SignInActivity.class));
                            }
                        });
            }
        });

    }
}