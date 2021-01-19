package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_SIGNIN = "Firebase Sign In";
    Button btn_todo_list;
    Button btn_wikipedia_search;
    Button btn_formulas_cheatsheet;
    Button btn_pomodoro_timer;

    private FirebaseAuth mAuth;  // firebase authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();  // get an instance of firebaseauth


        btn_todo_list = findViewById(R.id.btn_todo_list);
        btn_wikipedia_search = findViewById(R.id.btn_wikipedia_search);
        btn_formulas_cheatsheet = findViewById(R.id.btn_formulas_cheatsheet);
        btn_pomodoro_timer = findViewById(R.id.btn_pomodoro_timer);

        btn_todo_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TodoListActivity.class);
                startActivity(intent);
            }
        });

        btn_wikipedia_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WikipediaSearchActivity.class);
                startActivity(intent);
            }
        });

        btn_formulas_cheatsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormulasCheatsheetActivity.class);
                startActivity(intent);
            }
        });

        btn_pomodoro_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PomodoroTimerActivity.class);
                startActivity(intent);
            }
        });
    }
}


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void createAccount(String email, String password)
    {
        if (!validateForm())
        {
            return;
        }


        // create new user with authentication instance's createUserWithEmailAndPassword method
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG_SIGNIN, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG_SIGNIN, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in User's Information
                            Log.d(TAG_SIGNIN, "signnWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else
                        {
                            // IF sign-in fails, display message to user
                            Log.w(TAG_SIGNIN, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }

                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void updateUI(FirebaseUser user)
    {
        // Check if user not null, then set text views with all details
        // like name, token, id, etc
        // if null then call sign up/ sign in screen?
    }

}