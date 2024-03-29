package com.example.cleancloud;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        button = (Button)findViewById(R.id.btn_login);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == button){
                    LoginUser();
                }
            }
        });
    }
    public void LoginUser(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser = mAuth.getCurrentUser();
                            finish();
                            startActivity(new Intent(getApplicationContext(),
                                    ProfileActivity.class));
                        }else {
                            Toast.makeText(LoginFragment.this, "couldn't login",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
