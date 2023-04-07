package com.android.jetnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.jetnotes.model.Note
import com.android.jetnotes.util.DateConvertor
import com.android.jetnotes.util.UUIDConvertor

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConvertor::class,UUIDConvertor::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao
}