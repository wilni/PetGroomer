package com.example.petgroomer;

import android.provider.BaseColumns;

public class PetHelper {
    private PetHelper(){}

    public static class PetEntry implements BaseColumns {
        // const for each entry field

        public static final String TABLE_NAME = "pet";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_BREED = "breed";
        public static final String COLUMN_NAME_SPEC_INSTRUCTIONS = "spec_instructions";
    }
}
