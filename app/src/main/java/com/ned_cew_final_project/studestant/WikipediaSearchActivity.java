package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WikipediaSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia_search);
    }


    public void btnclick(View view) {

        EditText et_wiki= (EditText)findViewById(R.id.editText_wiki);
        String et1=et_wiki.getText().toString();

        String url= "https://en.wikipedia.org/wiki/" + et1;


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}