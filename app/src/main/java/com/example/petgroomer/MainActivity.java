package com.example.petgroomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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
    EditText dogWeight, dogBreed, dogName, special_instructions;
    TextView dogInfo;
    Button searchBtn;


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

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Log.d("READDATA", "We clicked the btn");
            Intent showPage = new Intent(MainActivity.this, ShowPage.class);
            startActivity(showPage);
            }
        });
    }


    public void insertData(View view) {
        ContentValues values = new ContentValues();
        values.put(PetHelper.PetEntry.COLUMN_NAME_NAME, dogName.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_BREED, dogBreed.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_WEIGHT, dogWeight.getText().toString());
        values.put(PetHelper.PetEntry.COLUMN_NAME_SPEC_INSTRUCTIONS, special_instructions.getText().toString());
        Log.d("LOGTEST", "We made it through instructions ");
        long newRowID = db.insert(PetHelper.PetEntry.TABLE_NAME, null, values);
        Log.d("LOGTEST", "We inserted: " + newRowID);
        String dogInfoTxt = "Name: " + dogName.getText().toString() +
                "\nBreed: " +dogBreed.getText().toString() + "\nWeight: " +
                dogWeight.getText().toString() + "\n instructions: \n" + special_instructions.getText().toString();

        dogName.setText("");
        dogBreed.setText("");
        dogWeight.setText("");
        special_instructions.setText("");



        dogInfo.setText(dogInfoTxt);
    }
}