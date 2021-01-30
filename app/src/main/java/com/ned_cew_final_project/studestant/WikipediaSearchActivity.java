package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WikipediaSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia_search);
    }


    public void btnclick(View view) {

        EditText et_wiki= (EditText)findViewById(R.id.editText_wiki);
        String et_w=et_wiki.getText().toString();

        if (et_w.isEmpty()) {
            Toast.makeText(this,"You did not write",Toast.LENGTH_SHORT).show();

        }
        else {

            String url = "https://en.wikipedia.org/wiki/" + et_w; //making url which is going to be redirected to browser


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}