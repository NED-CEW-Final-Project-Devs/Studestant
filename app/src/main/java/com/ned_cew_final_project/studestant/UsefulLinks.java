package com.ned_cew_final_project.studestant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UsefulLinks extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_links);

        TextView tv_moshLink = findViewById(R.id.tv_moshLink);
        tv_moshLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv_w3Link = findViewById(R.id.tv_w3Link);
        tv_w3Link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv_stackLink = findViewById(R.id.tv_stackLink);
        tv_stackLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv_grammarlyLink = findViewById(R.id.tv_grammarlyLink);
        tv_grammarlyLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv_kAcadLink = findViewById(R.id.tv_kAcadLink);
        tv_kAcadLink.setMovementMethod(LinkMovementMethod.getInstance());

    }
}




















