package com.ned_cew_final_project.studestant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TodoEditorActivity extends AppCompatActivity {
    FirebaseUser current_user;
    DocumentReference todo_item_reference;

    EditText et_todo_editor_title;
    EditText et_todo_editor_details;

    String doc_id;
    String todo_title;
    String todo_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_editor);
        setTitle("Edit");

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        if (current_user == null)  // if not logged in
        {
            finish();
        }

        Intent intent = getIntent();

        // validation
        if ( ! (intent.hasExtra("todo_document_id") &&
                intent.hasExtra("todo_title") && intent.hasExtra("todo_details")))
        {
            Toast.makeText(this, "Error, couldn't find the todo item.", Toast.LENGTH_SHORT).show();
            finish();
        }

        doc_id = intent.getStringExtra("todo_document_id");
        todo_title = intent.getStringExtra("todo_title");
        todo_details = intent.getStringExtra("todo_details");

        todo_item_reference =
                FirebaseFirestore.getInstance().  // get an instance of firestore database
                        collection("users").  // go to the users collection
                        document(current_user.getUid()).  // go inside the document corresponding to current user
                        collection("todoItems").  // go to the todoItems collection inside that user's to-dolist collection
                        document(doc_id);  // go to the specific to-do item document

        et_todo_editor_title = findViewById(R.id.edittxt_todo_editor_title);
        et_todo_editor_details = findViewById(R.id.edittxt_todo_editor_details);

        et_todo_editor_title.setText(todo_title);
        et_todo_editor_details.setText(todo_details);

        findViewById(R.id.btn_todo_editor_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_todo_editor_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        findViewById(R.id.btn_todo_editor_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TodoEditorActivity.this, "Long press to delete.\n" +
                        "WARNING: THIS ACTION CAN NOT BE UNDONE.", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_todo_editor_delete).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                todo_item_reference.delete();
                finish();
                Toast.makeText(TodoEditorActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
        });
    }


    private void saveChanges() {
        if (current_user == null)  // if no user logged in
        {
            finish();  // end the activity
        }


        String updated_todo_title = et_todo_editor_title.getText().toString().trim();
        if (updated_todo_title.equals(""))
        {
            Toast.makeText(this, "Can't save a todo item without a title.", Toast.LENGTH_SHORT).show();
            return;
        }

        String updated_todo_details = et_todo_editor_details.getText().toString().trim();

        // add the new to-do item to cloud firestore (which will also automatically update the recyclerview)
        todo_item_reference.update("title", updated_todo_title, "details", updated_todo_details);

        Toast.makeText(this, "Successfully saved changes", Toast.LENGTH_SHORT).show();
        finish();
    }


}