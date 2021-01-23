package com.ned_cew_final_project.studestant;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;

public class TodoAdapter extends FirestoreRecyclerAdapter<TodoItem, TodoAdapter.TodoHolder> {
    private OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TodoAdapter(@NonNull FirestoreRecyclerOptions<TodoItem> options) {
        super(options);
    }

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull TodoHolder holder, int position, @NonNull TodoItem model) {
        holder.txtview_title.setText(model.getTitle());
        holder.txtview_details.setText(model.getDetails());
        holder.checkBox_isDone.setChecked(model.getIsDone());
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolist_item, parent, false);
        return new TodoHolder(v, mListener);
    }

    class TodoHolder extends RecyclerView.ViewHolder
    {
        TextView txtview_title;
        TextView txtview_details;
        CheckBox checkBox_isDone;

        public TodoHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtview_title = itemView.findViewById(R.id.todo_title);
            txtview_details = itemView.findViewById(R.id.todo_details);
            checkBox_isDone = itemView.findViewById(R.id.todo_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    DocumentReference todo_document_reference = getSnapshots().getSnapshot(position).getReference();
                    Intent todo_editor_intent = new Intent(itemView.getContext(), TodoEditorActivity.class);
                    todo_editor_intent.putExtra("todo_document_id", todo_document_reference.getId());
                    todo_editor_intent.putExtra("todo_title", txtview_title.getText().toString());
                    todo_editor_intent.putExtra("todo_details", txtview_details.getText().toString());
                    itemView.getContext().startActivity(todo_editor_intent);
                }
            });

            checkBox_isDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();  // get position clicked in recycler view

                    if (position != RecyclerView.NO_POSITION)  // if checkbox on a valid item pressed
                    {
                        // update bool value in firestore
                        getSnapshots().getSnapshot(position).getReference().update("isDone", checkBox_isDone.isChecked());
                    }

                }
            });
        }
    }
}
