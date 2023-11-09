package com.dinsaren.roompersistenceandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dinsaren.roompersistenceandroid.dao.NoteDAO;
import com.dinsaren.roompersistenceandroid.models.Note;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();

    private static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , NoteDatabase.class, "NoteDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
