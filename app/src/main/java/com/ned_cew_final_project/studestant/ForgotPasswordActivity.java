package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    public static final String TAG_SIGNIN = "Firebase Sign In";

    EditText email_edittxt;
    Button get_password_link_btn;
    Button back_btn;

    private FirebaseAuth mAuth;  // firebase authentication instance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        email_edittxt = findViewById(R.id.email);
        get_password_link_btn = findViewById(R.id.btn_get_password_link);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        get_password_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edittxt.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter email.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PasswordResetEmail(email);
                }
            }
        });

    }

    private void PasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG_SIGNIN, "Email sent.");
                    Toast.makeText(ForgotPasswordActivity.this, "Email sent!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.d(TAG_SIGNIN, "Email couldn't be sent.");
                    Toast.makeText(ForgotPasswordActivity.this, "Unable to send reset mail", Toast.LENGTH_LONG).show();
                }
            }
        })
    }
}