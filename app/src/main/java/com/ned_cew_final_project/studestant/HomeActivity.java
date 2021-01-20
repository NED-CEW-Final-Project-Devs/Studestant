package com.ned_cew_final_project.studestant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    Button btn_todo_list;
    Button btn_wikipedia_search;
    Button btn_formulas_cheatsheet;
    Button btn_pomodoro_timer;
//    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_todo_list = findViewById(R.id.btn_todo_list);
        btn_wikipedia_search = findViewById(R.id.btn_wikipedia_search);
        btn_formulas_cheatsheet = findViewById(R.id.btn_formulas_cheatsheet);
        btn_pomodoro_timer = findViewById(R.id.btn_pomodoro_timer);
//        btn_logout = findViewById(R.id.btn_logout_home);

        btn_todo_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TodoListActivity.class);
                startActivity(intent);
            }
        });

        btn_wikipedia_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WikipediaSearchActivity.class);
                startActivity(intent);
            }
        });

        btn_formulas_cheatsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormulasCheatsheetActivity.class);
                startActivity(intent);
            }
        });

        btn_pomodoro_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PomodoroTimerActivity.class);
                startActivity(intent);
            }
        });

//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logOut();
//            }
//        });
    }

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        checkAuthState();
//    }
////
////    @Override
////    protected void onResume() {
////        super.onResume();
////        checkAuthState();
////    }
//
//    private void checkAuthState() {
//        if (FirebaseAuth.getInstance().getCurrentUser() == null)
//        {
//            Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
//            logOut();
//        }
//    }

//    private void logOut() {
//       AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
//           @Override
//           public void onComplete(@NonNull Task<Void> task) {
//               if (task.isSuccessful())
//               {
//                   Toast.makeText(HomeActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
//               }
//           }
//       });
//    }
}