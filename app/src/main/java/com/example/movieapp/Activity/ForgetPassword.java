package com.example.movieapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.internal.$Gson$Preconditions;

public class ForgetPassword extends AppCompatActivity {
    Button reset;
    EditText email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        reset=findViewById(R.id.resetPassword);
        email=findViewById(R.id.texteamilAddress);
        auth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword();
            }
        });

    }
    public void ResetPassword()
    {
        if(email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            email.setError("Enter Anthor Email");
            email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgetPassword.this, "Check Your Email", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(ForgetPassword.this, "Try Again Some Thing happer", Toast.LENGTH_LONG).show();
                        }

                    }
                });



    }
}