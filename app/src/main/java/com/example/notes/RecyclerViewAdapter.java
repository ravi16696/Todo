package com.example.notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Controller.NotesList;
import com.example.notes.Util.Logging;

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView content;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.notes_header);
            content = itemView.findViewById(R.id.notes_content);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    position = getAdapterPosition();
                    showDialog(v, position);
                    Logging.debug("ravi", "long clicked");
                    return false;
                }
            });

        }

        public void showDialog(View view, final int position) {
            AlertDialog alertDialog = new AlertDialog.Builder(view.getContext())
                    .setMessage("Do you want to delete")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            NotesList.deleteNotes(position);
                        }
                    })
                    .setNegativeButton("cancel", null)
                    .show();

        }

        @Override
        public void onClick(View v) {
            position = getAdapterPosition();
            Intent intent = new Intent(v.getContext(), AddNotes.class);
            intent.putExtra("title" , title.getText().toString());
            intent.putExtra("content", content.getText().toString());
            intent.putExtra("position", position);
            v.getContext().startActivity(intent);
            Logging.debug(TAG, "ITEM CLICKED");
        }

    }

}
