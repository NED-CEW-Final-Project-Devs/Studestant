package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    Button btn_todo_list;
    Button btn_wikipedia_search;
    Button btn_formulas_cheatsheet;
    Button btn_pomodoro_timer;
    Button btn_useful_links;
    TextView txtview_userinfo;
    Button btn_signout;

    FirebaseAuth mAuth;  // firebase authentication instance
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_todo_list = findViewById(R.id.btn_todo_list);
        btn_wikipedia_search = findViewById(R.id.btn_wikipedia_search);
        btn_formulas_cheatsheet = findViewById(R.id.btn_formulas_cheatsheet);
        btn_pomodoro_timer = findViewById(R.id.btn_pomodoro_timer);
        btn_useful_links = findViewById(R.id.btn_useful_links);
        txtview_userinfo = findViewById(R.id.txtview_userinfo);
        btn_signout = findViewById(R.id.btn_signout);

        mAuth = FirebaseAuth.getInstance();

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

        btn_useful_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsefulLinks.class);
                startActivity(intent);
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
//                Auth.GoogleSignInApi.signOut(apiClient);  // check this for google signout problems
            }
        });

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser current_user = mAuth.getCurrentUser();
                if (current_user == null)  // if user isn't logged in
                {
                    // Create and launch sign-in activity intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setTheme(R.style.Theme_Studestant)
                                    .setIsSmartLockEnabled(false)
                                    .build(),
                            RC_SIGN_IN);
                    //.setLogo(R.drawable.studestant_logo_0)
                }
                updateUI();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // adding authStateListener
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // removing authstateListener
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN)  // if result is from firebase signin activity
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Successfully signed in", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (response == null)  // user pressed back on login screen
                {
                    finish(); // close the app
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void updateUI() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)  // if not logged in
        {
            txtview_userinfo.setText("User info:\nNot signed in");
            btn_signout.setVisibility(View.GONE);
        }
        else  // if logged in
        {
            btn_signout.setVisibility(View.VISIBLE);
            String name = currentUser.getDisplayName();
            if (TextUtils.isEmpty(name)){
                name = "Not available";
            }
            String email = currentUser.getEmail();
            if (TextUtils.isEmpty(email)){
                email = "Not available";
            }
            // set user name and email in text view
            txtview_userinfo.setText(String.format("User info:\nName: %s\nEmail: %s", name, email));
        }
    }
}
