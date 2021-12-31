package com.example.finalproject22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_Activity extends AppCompatActivity {
    EditText etRegLogin;
    EditText etRegPassword;
    EditText etName;
    FirebaseAuth mAuth;
    Button regbtn1;
    Button logbtn1;
    FirebaseDatabase db;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etRegLogin = findViewById(R.id.EntertheLogin1);
        etRegPassword = findViewById(R.id.EnterThePassword1);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        userRef = db.getReference();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        regbtn1 = findViewById(R.id.regbtn1);
        etName = findViewById(R.id.editTextTextPersonName1);
        logbtn1 = findViewById(R.id.logbtn1);
        regbtn1.setOnClickListener(view -> {
            createUser();
        });
        logbtn1.setOnClickListener(view -> {
            startActivity(new Intent(Registration_Activity.this, Login_Activity.class));
        });
    }

    private void createUser() {
        String email = etRegLogin.getText().toString();
        String password = etRegPassword.getText().toString();
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etRegLogin.setError("Email cannot be empty");
            etRegLogin.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Registration_Activity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registration_Activity.this, Login_Activity.class));

                    } else {
                        Toast.makeText(Registration_Activity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            });
        }
    }
}


                //  User user=new User();
//                        user.setEmail(etRegLogin.getText().toString());
//                        user.setName(etRegPassword.getText().toString());
//                        user.setPassword(etName.getText().toString());
//                        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).
//                                addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        Toast.makeText(Registration_Activity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Registration_Activity.this,"Something gone wrong try later",Toast.LENGTH_SHORT).show();
//                            }
//                        });


