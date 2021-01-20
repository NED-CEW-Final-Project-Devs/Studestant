package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_SIGNIN = "Firebase Sign In";
    private FirebaseAuth mAuth;  // firebase authentication instance

    Button logout_btn;
    TextView user_info_txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();  // get an instance of firebaseauth
        logout_btn = findViewById(R.id.btn_logout);
        user_info_txtview = findViewById(R.id.user_info);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser current_user = mAuth.getCurrentUser();
        if (current_user == null)
        {
            // go to sign in activty
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            // go to home activity
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//            finish();
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show();
            String user_name = current_user.getDisplayName();
            String user_email = current_user.getEmail();

            user_info_txtview.setText(String.format("Name: %s\nEmail: %s", user_name, user_email));


        }
    }

    private void logOut() {
        mAuth.signOut();
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
        finish();
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

//    private void createAccount(String email, String password)
//    {
//        if (!validateForm())
//        {
//            return;
//        }
//
//
//        // create new user with authentication instance's createUserWithEmailAndPassword method
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG_SIGNIN, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        }
//                        else
//                        {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG_SIGNIN, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//                    }
//                });
//    }
//
//    private void signIn(String email, String password) {
//        Log.d(TAG_SIGNIN, "signIn:" + email);
//        if (!validateForm()) {
//            return;
//        }
//
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in User's Information
//                            Log.d(TAG_SIGNIN, "signnWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        }
//                        else
//                        {
//                            // IF sign-in fails, display message to user
//                            Log.w(TAG_SIGNIN, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//                    }
//
//                });
//    }
//
//    private void signOut() {
//        mAuth.signOut();
//        updateUI(null);
//    }
//
//    private void updateUI(FirebaseUser user)
//    {
//        // Check if user not null, then set text views with all details
//        // like name, token, id, etc
//        // if null then call sign up/ sign in screen?
//    }

}