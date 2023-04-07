package com.android.jetnotes.data

import com.android.jetnotes.model.Note

class NoteDataSource(){
    fun loadNotes() : List<Note>{
        return listOf(
            Note(title = "First Note", description = "First Description"),
            Note(title = "Second Note", description = "Second Description"),
            Note(title = "Third Note", description = "Third Description"),
            Note(title = "Fourth Note", description = "Fourth Description"),
            Note(title = "Fifth Note", description = "Fifth Description"),
            Note(title = "Sixth Note", description = "Sixth Description"),
            Note(title = "Seventh Note", description = "Seventh Description"),
            Note(title = "Eighth Note", description = "Eighth Description"),
            Note(title = "Ninth Note", description = "Ninth Description"),
            Note(title = "Tenth Note", description = "Tenth Description")
        )
    }
}