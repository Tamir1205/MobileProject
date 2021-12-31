package com.example.finalproject22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
EditText etLogLogin;
EditText etLogPassword;
Button logbtn2;
Button regbtn2;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogLogin=findViewById(R.id.EntertheLogin2);
        etLogPassword=findViewById(R.id.EnterThePassword2);
        logbtn2=findViewById(R.id.loginbtn2);
        regbtn2=findViewById(R.id.regbtn2);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        logbtn2.setOnClickListener(view -> {
            loginUser();
        });
        regbtn2.setOnClickListener(view ->{
            startActivity(new Intent(Login_Activity.this, Registration_Activity.class));
        });
    }

    private void loginUser() {
        String email = etLogLogin.getText().toString();
        String password = etLogPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLogLogin.setError("Email cannot be empty");
            etLogLogin.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLogPassword.setError("Password cannot be empty");
            etLogPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login_Activity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    }else{
                        Toast.makeText(Login_Activity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}