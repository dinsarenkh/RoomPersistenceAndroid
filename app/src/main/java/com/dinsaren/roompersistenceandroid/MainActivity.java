package com.dinsaren.roompersistenceandroid;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dinsaren.adapter.NoteAdapter;
import com.dinsaren.app.BaseActivity;
import com.dinsaren.roompersistenceandroid.dao.NoteDAO;
import com.dinsaren.roompersistenceandroid.database.NoteDatabase;
import com.dinsaren.roompersistenceandroid.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity {
    private NoteDAO noteDAO;
    private RecyclerView recyclerViewNote;
    private FloatingActionButton btnAdd;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteDAO = NoteDatabase.getInstance(this).noteDAO();
        initView();
        getAllData();
    }

    private void initView() {
        recyclerViewNote = findViewById(R.id.recyclerViewNote);
        btnAdd = findViewById(R.id.btnAddNote);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                startActivityForResult(intent, 1000);
            }
        });
    }

    private void getAllData() {
        if (noteDAO.getAll().isEmpty()) {
            noteDAO.insert(new Note(1, "My Note", "I love bbu"));
        }

        noteAdapter = new NoteAdapter(this, noteDAO.getAll(), new NoteAdapter.OnClickListener() {
            @Override
            public void onClickItem(View view, Object object) {
                Note note = (Note) object;
                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                intent.putExtra("ID", note.getId());
                intent.putExtra("TITLE", note.getTitle());
                intent.putExtra("DESCRIPTION", note.getDescription());
                startActivityForResult(intent, 1000);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerViewNote.setLayoutManager(gridLayoutManager);
        recyclerViewNote.setAdapter(noteAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            getAllData();
        }
    }
}