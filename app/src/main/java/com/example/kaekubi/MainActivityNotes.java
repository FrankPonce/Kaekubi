 package com.example.kaekubi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kaekubi.adapters.NotesListAdapter;
import com.example.kaekubi.database.RoomDB;
import com.example.kaekubi.models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

 public class MainActivityNotes extends AppCompatActivity {
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notes);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);

        database = RoomDB.getInstance(this);
        notes = database.mainDAO().getAll();
        
        updateRecycler(notes);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNotes.this, NotesTakerActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == 101) {
             if (resultCode == Activity.RESULT_OK) {
                 Notes new_notes = (Notes) data.getSerializableExtra("note");
                 database.mainDAO().insert(new_notes);
                 notes.clear();
                 notes.addAll(database.mainDAO().getAll());
                 notesListAdapter.notifyDataSetChanged();
             }
         }
     }

     private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivityNotes.this, notes, notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
     }

     private final NotesClickListener notesClickListener = new NotesClickListener() {
         @Override
         public void onClick(Notes notes) {

         }

         @Override
         public void onLongClick(Notes notes, CardView cardview) {

         }
     };
 }