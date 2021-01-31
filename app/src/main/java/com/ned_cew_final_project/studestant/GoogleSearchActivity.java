package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GoogleSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_search);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  // Disable darkmode
    }

    public void btnclick(View view) {

        EditText et_google = findViewById(R.id.editText_google);
        String google_search_str = et_google.getText().toString().trim();

        if (google_search_str.isEmpty()) {
            Toast.makeText(this,"You did not write",Toast.LENGTH_SHORT).show();

        }
        else {

            String url = "https://www.google.com/search?q=" + google_search_str; //making url which is going to be redirected to browser


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}