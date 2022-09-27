package com.example.kaekubi;

import androidx.cardview.widget.CardView;

import com.example.kaekubi.models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardview);

}
