package com.example.movieapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText email;
    EditText password;
    FirebaseAuth auth;
    private static  final String EMAIL="email";
    private static final String PASSWORD="password";
    private static final String Mypref="mypref";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.eamilAddress);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        sharedPreferences=getSharedPreferences(Mypref,MODE_PRIVATE);
        TextView forget=findViewById(R.id.forgetpassword);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,ForgetPassword.class));
            }
        });

        Intent intent=getIntent(); // get value from setting framgmet when user logout i delete data from shardePreferences;

        if(intent!=null)
        {
            if(intent.getIntExtra("Logout",-1)!=-1)
            {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(EMAIL,null);
                editor.putString(PASSWORD,null);
                editor.apply();
            }
        }


    }

    public void Login(View view){

        if(email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
            email.requestFocus();
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Enter Another Email");
            email.requestFocus();
            return;
        }
        else if(password.getText().toString().isEmpty())
        {
            password.setError("Enter Password");
            password.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString(EMAIL,email.getText().toString());
                            editor.putString(PASSWORD,password.getText().toString());
                            editor.apply();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            password.setError("Wrong Enter");
                            password.requestFocus();
                        }
                    }
                });




    }

    public void createAcount(View view)
    {
        startActivity(new Intent(getApplicationContext(), CreateAccount.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }
}