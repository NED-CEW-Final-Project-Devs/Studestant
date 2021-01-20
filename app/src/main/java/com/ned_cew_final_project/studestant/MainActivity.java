package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_SIGNIN = "Firebase Sign In";
    public static final int RC_SIGN_IN = 5450;
    private FirebaseAuth mAuth;  // firebase authentication instance
    private FirebaseAuth.AuthStateListener mAuthListener;  // authentication listener

    Button login_btn;
    Button logout_btn;
    TextView user_info_txtview;

    // authentication providers
    List<AuthUI.IdpConfig> auth_providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();  // get an instance of firebaseauth
        login_btn = findViewById(R.id.btn_login);
        logout_btn = findViewById(R.id.btn_logout);
        user_info_txtview = findViewById(R.id.user_info);

        //        if (AuthUI.canHandleIntent(getIntent())) {
//            if (getIntent().getExtras() == null) {
//                return;
//            }
//            String link = getIntent().getExtras().getString(ExtraConstants.EMAIL_LINK_SIGN_IN);
//            if (link != null) {
//                startActivityForResult(
//                        AuthUI.getInstance()
//                                .createSignInIntentBuilder()
//                                .setEmailLink(link)
//                                .setAvailableProviders((auth_providers)) // possible error
//                                //.setLogo(R.drawable.scholar_hat)
//                                .build(),
//                        RC_SIGN_IN);
//            }
//        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        FirebaseUser current_user = mAuth.getCurrentUser();
        if (current_user == null)
        {
            logout_btn.setVisibility(View.GONE);
            login_btn.setVisibility(View.VISIBLE);
            user_info_txtview.setText("");
        }
        else
        {
            String user_name = current_user.getDisplayName();
            String user_email = current_user.getEmail();
            logout_btn.setVisibility(View.VISIBLE);
            login_btn.setVisibility(View.GONE);
            user_info_txtview.setText(String.format("Logged in as:\nName: %s\nEmail: %s", user_name, user_email));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK)
            {
                // Successfully signed in
                Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
                //                String user_name = current_user.getDisplayName();
//                String user_email = current_user.getEmail();
//                user_info_txtview.setText(String.format("Name: %s\nEmail: %s", user_name, user_email));
            }
            else
            {
                Toast.makeText(this, "Login FAIL", Toast.LENGTH_LONG).show();
                // Toast.makeText(this, response.getError().getErrorCode(), Toast.LENGTH_SHORT).show();
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
            updateUI();

        }
    }



    public void logIn(View view) {

        // settings for enabling email lin sign in
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setAndroidPackageName("com.ned_cew_final_project.studestant", /* installIfNotAvailable= */ true,
                        /* minimumVersion= */ null)
                .setHandleCodeInApp(true) // This must be set to true
                .setUrl("https://google.com") // This URL needs to be whitelisted
                .build();
        // 5. If you want to catch the link in a specific activity, please follow the steps outlined here. Otherwise, the link will redirect to your launcher activity.

        auth_providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().enableEmailLinkSignIn().setActionCodeSettings(actionCodeSettings).build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(auth_providers) // possible error
                        .setLogo(R.drawable.scholar_hat)
                        .build(),
                RC_SIGN_IN);

        updateUI();
    }

    public void logOut(View view) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG_SIGNIN, "logout:success");
                    Toast.makeText(MainActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG_SIGNIN, "logout:fail");
                    Toast.makeText(MainActivity.this, "Failed to log Out", Toast.LENGTH_SHORT).show();
                }
                updateUI();
            }
        });
//        AuthUI.getInstance().delete(this).addOnCompleteListener(new OnCompleteListener<Void>() {
        //            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful())
//                {
//                    Log.d(TAG_SIGNIN, "deleteOut:success");
//                    Toast.makeText(MainActivity.this, "Deleted Out Successfully", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Log.d(TAG_SIGNIN, "deleteOut:fail");
//                    Toast.makeText(MainActivity.this, "Failed to Delete Out", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });  // error
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser current_user = mAuth.getCurrentUser();
//        if (current_user == null)
//        {
//            // go to sign in activty
//            Intent intent = new Intent(this, SigninActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        else
//        {
//            // go to home activity
////            Intent intent = new Intent(this, HomeActivity.class);
////            startActivity(intent);
////            finish();
//            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show();
//            String user_name = current_user.getDisplayName();
//            String user_email = current_user.getEmail();
//
//            user_info_txtview.setText(String.format("Name: %s\nEmail: %s", user_name, user_email));
//
//
//        }
//    }

//    private void logOut() {
//        mAuth.signOut();
//        Intent intent = new Intent(this, SigninActivity.class);
//        startActivity(intent);
//        finish();
//    }


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
}