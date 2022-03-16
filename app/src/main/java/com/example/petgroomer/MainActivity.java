package com.example.petgroomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // declare db
    SQLiteDatabase db;
    // declare created helper class for entity
    PetDBHelper petDBHelper;
    //declare UI components
    EditText dogWeight, dogBreed, dogName, special_instructions, searchPets;
    TextView dogInfo;
    Button searchBtn, submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize UI components
        petDBHelper = new PetDBHelper(getApplicationContext());
        db = petDBHelper.getWritableDatabase();
        dogName = (EditText) findViewById(R.id.nameTxt);
        dogBreed = (EditText) findViewById(R.id.breedTxt);
        dogWeight = (EditText) findViewById(R.id.weightTxt);
        special_instructions = (EditText) findViewById(R.id.specInstructionsTxt);
        dogInfo = (TextView) findViewById(R.id.resultsTV);
        searchPets = (EditText) findViewById(R.id.searchPets);

    }


    public void insertData(View view) {
        ContentValues values = new ContentValues();
        values.put(PetHelper.PetEntry.COLUMN_NAME_NAME, dogName.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_BREED, dogBreed.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_WEIGHT, dogWeight.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS, special_instructions.getText().toString());
        Log.d("LOGTEST", "We made it through instructions ");
        long newRowID = db.insert(PetHelper.PetEntry.TABLE_NAME, null, values);
        Log.d("LOGTEST", "We inserted ");

        dogName.setText("");
        dogBreed.setText("");
        dogWeight.setText("");
        special_instructions.setText("");

    }

    public void readData(View view) {
        SQLiteDatabase db = petDBHelper.getReadableDatabase();

        // array with column names for search query
        String[] projection = {
                PetHelper.PetEntry._ID,
                PetHelper.PetEntry.COLUMN_NAME_NAME,
                PetHelper.PetEntry.COLUMN_NAME_BREED,
                PetHelper.PetEntry.COLUMN_NAME_WEIGHT,
                PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS
        };

        //Query strings to find LIKE name entry
        String selection = PetHelper.PetEntry.COLUMN_NAME_NAME + " LIKE ?";

        String[] selectionArgs = {searchPets.getText().toString() + "%"};

        String sortOrder = PetHelper.PetEntry.COLUMN_NAME_NAME + " ASC";

        // cursor to access Entity's table
        Cursor cursor = db.query(
                PetHelper.PetEntry.TABLE_NAME,
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
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

        //create string to display results
        String petInfo = "";
        int i = 0;
        for(; i < petNames.size(); i++){
            petInfo += "Doggo: "+ petNames.get(i)
            + " Breed: " + petBreeds.get(i) +
            " Weight: " + petWeights.get(i) +
            " instructions: " + petInstructions.get(i);
            petInfo += "\n";
        }
        dogInfo.setText(petInfo);
    }
}