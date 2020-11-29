package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Controller.NotesList;
import com.example.notes.Util.Logging;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NotesList> notesLists;
    private static String TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter(Context context, ArrayList<NotesList> notesLists) {
        this.context = context;
        this.notesLists = notesLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesList notes = notesLists.get(position);
        holder.title.setText(notes.getNotesTitle());
        holder.content.setText(notes.getNotesContent());
    }

    @Override
    public int getItemCount() {
        return notesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.notes_header);
            content = itemView.findViewById(R.id.notes_content);
        }

        @Override
        public void onClick(View v) {

            Logging.debug(TAG, "ITEM CLICKED");

        }
    }

}
