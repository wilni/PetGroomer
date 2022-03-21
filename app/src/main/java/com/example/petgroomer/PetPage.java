package com.example.petgroomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PetPage extends AppCompatActivity {

    // declare db
    SQLiteDatabase db;

    // declare created helper class for entity
    PetDBHelper petDBHelper;

    TextView nameTxt, breedTxt, weightTxt, instructionTxt;
    Button deleteBtn;



    private static final String NAME_TAG = "name";
    private static final String BREED_TAG = "breed";
    private static final String WEIGHT_TAG = "weight";
    private static final String INSTRUC_TAG = "instructions";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_page);
        petDBHelper = new PetDBHelper(getApplicationContext());
        db = petDBHelper.getWritableDatabase();

        nameTxt = (TextView) findViewById(R.id.nameShowTxt);
        breedTxt = (TextView) findViewById(R.id.breedShowTxt);
        weightTxt = (TextView) findViewById(R.id.weightShowText);
        instructionTxt = (TextView) findViewById(R.id.instructionShowTxt);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        setPetInfo();

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("CHECKINGCLICK", "WAS CLICKED");
                petDBHelper.deletePet(getIntent().getStringExtra(NAME_TAG));
                Toast.makeText(PetPage.this, "Deleted this pet", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PetPage.this, ShowPage.class);
                startActivity(i);
            }
        });
    }
    public void setPetInfo(){
        Intent petInfo = getIntent();
        nameTxt.setText(petInfo.getStringExtra(NAME_TAG));
        breedTxt.setText(petInfo.getStringExtra(BREED_TAG));
        weightTxt.setText(String.valueOf(petInfo.getDoubleExtra(WEIGHT_TAG, 0)));
        instructionTxt.setText(petInfo.getStringExtra(INSTRUC_TAG));

    }

}