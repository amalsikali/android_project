package com.example.personalitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button btnlog;
    TextInputEditText email,password;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email =findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnlog =findViewById(R.id.btnlog);

        String emailregiter =getIntent().getStringExtra("e");
        String passregister =getIntent().getStringExtra("p");
        email.setText(emailregiter);
        password.setText(passregister);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, TestActivity.class));
            finish();
        }

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String ee = email.getText().toString().trim();
              String pp = password.getText().toString().trim();
                if (TextUtils.isEmpty(ee)){
                   email.setError("email is required !");
                }
                if (TextUtils.isEmpty(pp)) {
                    password.setError("password is required!");
                    return;
                }

                fAuth.signInWithEmailAndPassword(ee,pp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent =new Intent(LoginActivity.this,TestActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error!" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    public void gotoregister(View view) {
        Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}