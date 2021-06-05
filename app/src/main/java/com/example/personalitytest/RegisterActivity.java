package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText email,password,fullname;
    Button btnregister;
    TextView tvlogin,tvback;
    private FirebaseAuth fAuth;
    public static String e,p;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.fullname);
        email= findViewById(R.id.em);
        password = findViewById(R.id.pass);
        btnregister =findViewById(R.id.btnregister);
        tvlogin= findViewById(R.id.tvlogin);
        tvback= findViewById(R.id.tvback);

        fAuth = FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = fullname.getText().toString().trim();
                 e = email.getText().toString().trim();
                 p = password.getText().toString().trim();
                if (TextUtils.isEmpty(e)) {
                    email.setError("email is required!");
                    return;
                }

                if (TextUtils.isEmpty(p)) {
                    password.setError("password is required!");
                    return;
                }

                if (p.length() < 6) {
                    password.setError("Password too short, enter minimum 6 characters!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User is created successfuly :D", Toast.LENGTH_SHORT).show();
                            fAuth.signOut();
                            Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.putExtra("e",e);
                            intent.putExtra("p",p);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "ERROR !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }
    public void gotologin(View view){
        Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}