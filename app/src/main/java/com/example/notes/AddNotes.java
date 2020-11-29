package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.notes.Controller.NotesList;
import com.example.notes.Util.Logging;
import com.example.notes.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class AddNotes extends AppCompatActivity {

    EditText inputText, DescText;
    ListView listView;
    String intentTitle, intentContent;
    int position;
    boolean isUpdate = false;
    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    public static final int PICK_IMAGE = 1;


    FloatingActionButton fab;
    View sketch,todo,mike,picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        fab = findViewById(R.id.fabtick);
        inputText = findViewById(R.id.inputText);
        DescText = findViewById(R.id.DescText);
        todo = findViewById(R.id.navIcon_1);
        sketch = findViewById(R.id.navIcon_2);
        mike = findViewById(R.id.navIcon_3);
        picture = findViewById(R.id.navIcon_5);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            intentTitle = bundle.getString("title");
            intentContent = bundle.getString("content");
            position = bundle.getInt("position");
            isUpdate = true;
        }

        inputText.setText(intentTitle);
        DescText.setText(intentContent);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                DescText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });



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

        sketch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSketch(v);
            }
        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachImage(v);

            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodo(v);
            }
        });

        mike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoice(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            Logging.debug("Ravi", "picking from gallery");
            //TODO: action
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    public void startSketch(View view) {
        Logging.debug("ravi", "startSketch activity");
        Intent intent = new Intent(view.getContext(), sketchActivity.class);
        startActivity(intent);
    }

    public void addTodo(View view) {

    }

    public void startVoice(View view) {
        showVoiceDialog(view);
        speechRecognizer.startListening(speechRecognizerIntent);
    }

    private void showVoiceDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext())
                .setMessage("Listening...")
                .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        speechRecognizer.destroy();
                    }
                })
                .setIcon(R.drawable.ic_baseline_keyboard_voice)
                .show();
    }

    public void attachImage(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

}


