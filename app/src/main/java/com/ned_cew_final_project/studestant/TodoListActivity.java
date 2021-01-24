package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TodoListActivity extends AppCompatActivity {
    private CollectionReference todo_collection_reference;

    private TodoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        findViewById(R.id.btn_add_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TodoListActivity.this, TodoAdderActivity.class));
            }
        });

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        if (current_user == null)  // if no user logged in
        {
            finish();  // end the activity
        }

        todo_collection_reference = FirebaseFirestore.getInstance().  // get an instance of firestore database
                collection("users").  // go to the users collection
                document(current_user.getUid()).  // go inside the document corresponding to current user
                collection("todoItems");  // go to the todoItems collection inside that user's document

        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() == null)  // if user not logged in
        {
            finish();  // end activity
        }
        adapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.stopListening();
    }


    private void setUpRecyclerView()
    {
        Query query = todo_collection_reference.orderBy("isDone", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<TodoItem> options = new FirestoreRecyclerOptions.Builder<TodoItem>()
            .setQuery(query, TodoItem.class)
            .build();

        adapter = new TodoAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_todo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}