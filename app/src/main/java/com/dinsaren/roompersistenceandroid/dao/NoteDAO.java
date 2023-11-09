package com.dinsaren.roompersistenceandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dinsaren.roompersistenceandroid.models.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note productCart);

    @Query("SELECT * FROM note_table")
    List<Note> getAll();

    @Delete
    void delete(Note productCart);

    @Update
    void update(Note productCart);

}
