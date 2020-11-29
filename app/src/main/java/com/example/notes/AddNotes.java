package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.notes.Controller.NotesList;
import com.example.notes.Util.Logging;
import com.example.notes.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddNotes extends AppCompatActivity {

    EditText inputText, DescText;
    ListView listView;
    String intentTitle, intentContent;
    int position;
    boolean isUpdate = false;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        fab = findViewById(R.id.fabtick);
        inputText = findViewById(R.id.inputText);
        DescText = findViewById(R.id.DescText);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            intentTitle = bundle.getString("title");
            intentContent = bundle.getString("content");
            position = bundle.getInt("position");
            isUpdate = true;
        }

        inputText.setText(intentTitle);
        DescText.setText(intentContent);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputText.getText().toString();
                String content = DescText.getText().toString();
                NotesList notesList = new NotesList(title, content);
                if(isUpdate) {
                    NotesList.deleteNotes(position);
                }
                NotesList.addNotes(notesList);
                finish();
            }
        });
    }

}


