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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    EditText email_edittxt;
    EditText password_edittxt;
    Button signup_btn;
    private FirebaseAuth mAuth;  // firebase authentication instance
    public static final String TAG_SIGNIN = "Firebase Sign In";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();  // get an instance of firebaseauth

        email_edittxt = findViewById(R.id.email);
        password_edittxt = findViewById(R.id.password);
        signup_btn = findViewById(R.id.btn_signup);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_edittxt.getText().toString();
                String password = password_edittxt.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(email_edittxt.getText().toString(), password_edittxt.getText().toString());
                }
            }
        });
    }

    private void createAccount(String email, String password) {
//        if (!validateForm())
//        {
//            return;
//        }
//        if (email.equals("") || password.equals("")){
//            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // create new user with authentication instance's createUserWithEmailAndPassword method
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG_SIGNIN, "createUserWithEmail:success");
                            Toast.makeText(SignupActivity.this, "Authentication Success.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG_SIGNIN, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            //                            updateUI(null);
                        }
                    }
                });
    }
}