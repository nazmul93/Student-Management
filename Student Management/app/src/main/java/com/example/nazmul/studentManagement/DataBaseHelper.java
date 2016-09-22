package com.example.nazmul.studentManagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "list_view_registration";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_REGISTRATION = "registration";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_FATHERS_NAME = "fathersName";
    public static final String COL_MOTHERS_NAME = "mothersName";
    public static final String COL_ADDRESS = "address";
    public static final String COL_CONTACT = "contactNo";
    public static final String COL_SCHOOLS_NAME = "schoolsName";
    public static final String COL_CLASS_NAME = "class";
    public static final String COL_DOB = "dateOfBirth";
    public static final String COL_GENDER = "gender";

    public static final String TABLE_USER = "user_info";
    public static final String COL_USER_ID = "id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_USER_PASSWORD = "password";
    public static final String COL_USER_PHONENO = "phone_number";
    public static final String COL_USER_EMAIL_NO = "email_number";

    static final String CREATE_TABLE_REGISTRATION = " CREATE TABLE " + TABLE_REGISTRATION +
            "( " + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT, "
            + COL_FATHERS_NAME + " TEXT, " + COL_MOTHERS_NAME + " TEXT, "
            + COL_ADDRESS + " TEXT, " + COL_CONTACT + " TEXT, "
            + COL_SCHOOLS_NAME + " TEXT, " + COL_CLASS_NAME + " TEXT, "
            + COL_DOB + " TEXT, " + COL_GENDER + " TEXT )";

    private static final String CREATE_USER_TABLE = " CREATE TABLE " + TABLE_USER +
            "( " + COL_USER_ID + " INTEGER PRIMARY KEY," + COL_USER_NAME + " TEXT, "
            + COL_USER_PASSWORD + " TEXT, " + COL_USER_PHONENO + " TEXT, " + COL_USER_EMAIL_NO + " TEXT )";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_REGISTRATION);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
