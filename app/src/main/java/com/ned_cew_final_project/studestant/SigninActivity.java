package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    EditText email_edittxt;
    EditText password_edittxt;
    Button signup_btn;
    Button signin_btn;
    private FirebaseAuth mAuth;  // firebase authentication instance
    public static final String TAG_SIGNIN = "Firebase Sign In";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();  // get an instance of firebaseauth

        email_edittxt = findViewById(R.id.email);
        password_edittxt = findViewById(R.id.password);
        signin_btn = findViewById(R.id.btn_signin);
        signup_btn = findViewById(R.id.btn_signup);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edittxt.getText().toString();
                String password = password_edittxt.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SigninActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SigninActivity.this, email + password, Toast.LENGTH_SHORT).show();
                    signIn(email_edittxt.getText().toString(), password_edittxt.getText().toString());
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void signIn(String email, String password) {
        Toast.makeText(this, "HEREEE", Toast.LENGTH_LONG).show();
        Log.d(TAG_SIGNIN, "signIn:" + email);
//        if (!validateForm()) {
//            return;
//        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in User's Information
                            Log.d(TAG_SIGNIN, "signinWithEmail:success");
                            Toast.makeText(SigninActivity.this, "signed in!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            // IF sign-in fails, display message to user
                            Log.w(TAG_SIGNIN, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }

                });
    }
}