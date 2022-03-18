package com.example.petgroomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowPage extends AppCompatActivity {
    //Recycler components for pet info
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager recyclerManager;

    // declare db
    SQLiteDatabase db;

    // declare created helper class for entity
    PetDBHelper petDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_page);
        //DB helper with app context
        petDBHelper = new PetDBHelper(getApplicationContext());
        // get db and set recycler;
        loadData();
        Log.v("READDATA", "we loaded the data");
    }


    public void loadData() {
        SQLiteDatabase db = petDBHelper.getWritableDatabase();
        // array with column names for search query
        String[] projection = {
                PetHelper.PetEntry._ID,
                PetHelper.PetEntry.COLUMN_NAME_NAME,
                PetHelper.PetEntry.COLUMN_NAME_BREED,
                PetHelper.PetEntry.COLUMN_NAME_WEIGHT,
                PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS
        };

        String sortOrder = PetHelper.PetEntry.COLUMN_NAME_NAME + " ASC";

        // cursor to access Entity's table
        Cursor cursor = db.query(
                PetHelper.PetEntry.TABLE_NAME,
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        // parallel List to hold each pet's info
        List<String> petNames = new ArrayList<>();
        List<String> petBreeds = new ArrayList<>();
        List<Double> petWeights = new ArrayList<>();
        List<String> petInstructions = new ArrayList<>();



        //navigate rows with cursor
        while(cursor.moveToNext()){
            // create local variables to add to parallel list for each row returned
            String petName = cursor.getString(cursor.getColumnIndexOrThrow(PetHelper.PetEntry.COLUMN_NAME_NAME));
            String petBreed = cursor.getString(cursor.getColumnIndexOrThrow(PetHelper.PetEntry.COLUMN_NAME_BREED));
            double petWeight = cursor.getDouble(cursor.getColumnIndexOrThrow(PetHelper.PetEntry.COLUMN_NAME_WEIGHT));
            String spec_instructions = cursor.getString(cursor.getColumnIndexOrThrow(PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS));
            petNames.add(petName);
            petBreeds.add(petBreed);
            petWeights.add(petWeight);
            petInstructions.add(spec_instructions);
        }

        //initialize recycler
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        //instantiate layout manager and set manager on recyclerView
        recyclerManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerManager);
        //create and set adapter
        adapter = new RecyclerAdapter(this, petNames, petBreeds, petWeights, petInstructions);
        recyclerView.setAdapter(adapter);

    }
}