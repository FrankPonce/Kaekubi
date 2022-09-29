package com.example.kaekubi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notesButton = findViewById(R.id.notes_button);
        Button pdfButton = findViewById(R.id.pdf_button);
        Button flashcardsButton = findViewById(R.id.flashcards_button);

        notesButton.setOnClickListener(this);
        pdfButton.setOnClickListener(this);
        flashcardsButton.setOnClickListener(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.notes_button:
                MainActivity.this.startActivity(new Intent(this, MainActivityNotes.class));
                break;
            case R.id.pdf_button:
                //uh
                break;
            case R.id.flashcards_button:
                //uh
                break;
        }
    }
}