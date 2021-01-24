package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TodoAdderActivity extends AppCompatActivity {
    EditText et_todo_adder_title;
    EditText et_todo_adder_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_adder);
        setTitle("Add new todo item");
        et_todo_adder_title = findViewById(R.id.edittxt_todo_adder_title);
        et_todo_adder_details = findViewById(R.id.edittxt_todo_adder_details);


        findViewById(R.id.btn_todo_adder_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_todo_adder_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTodoItem();
            }
        });
    }


    private void saveTodoItem () {
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();

        if (current_user == null)  // if no user logged in
        {
            finish();  // end the activity
        }

        CollectionReference todo_collection_reference =
                FirebaseFirestore.getInstance().  // get an instance of firestore database
                collection("users").  // go to the users collection
                document(current_user.getUid()).  // go inside the document corresponding to current user
                collection("todoItems");  // go to the todoItems collection inside that user's to-dolist collection


        String new_todo_title = et_todo_adder_title.getText().toString().trim();
        if (new_todo_title.equals(""))
        {
            Toast.makeText(this, "Can't save a todo item without a title.", Toast.LENGTH_SHORT).show();
            return;
        }

        String new_todo_details = et_todo_adder_details.getText().toString().trim();


        // add the new to-do item to cloud firestore (which will also automatically update the recyclerview)
        todo_collection_reference.add(new TodoItem(new_todo_title, new_todo_details));
        finish();
    }
}