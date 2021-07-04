package com.example.movieapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.R;
import com.example.movieapp.Data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {
    EditText name,email,password,phone;
    Button create;
    FirebaseAuth aut;

    private static final String Mypref="mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        aut=FirebaseAuth.getInstance();

        create=findViewById(R.id.buttoncreate);
        name=findViewById(R.id.editTextname);
        email=findViewById(R.id.editnewemail);
        password=findViewById(R.id.editnewpassword);
        phone=findViewById(R.id.phone);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAccount();
            }
        });


    }
    public void newAccount()
    {
        if(name.getText().toString().isEmpty())
        {
            name.setError("Enter name");
            name.requestFocus();
            return;
        }
        else if(phone.getText().toString().isEmpty())
        {
            phone.setError("Enter phone number");
            phone.requestFocus();
            return;
        }
        else if(!Patterns.PHONE.matcher(phone.getText().toString()).matches())
        {
            phone.setError("Enter anthor number");
            phone.requestFocus();
            return;
        }
        else if(email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {
            email.setError("Enter Another Email");
            email.requestFocus();
            return;
        }
        else if(password.getText().toString().isEmpty())
        {
            password.setError("Enter password");
            password.requestFocus();
        }
        else if(password.getText().toString().length()<6)
        {
            password.setError("Enter password more the 6 character");
            password.requestFocus();
            return;
        }
        aut.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            User user=new User(name.getText().toString().trim(),phone.getText().toString(),email.getText().toString(),password.getText().toString());
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(aut.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(CreateAccount.this, "Create Account Is done", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(CreateAccount.this,MainActivity.class));
                                    }
                                    else{

                                    }
                                }
                            });

                        }
                    }
                });


    }


}