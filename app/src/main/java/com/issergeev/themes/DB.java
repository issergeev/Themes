package com.issergeev.themes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {


    public static String getUsersTableName() {
        return USERS_TABLE_NAME;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getSALT() {
        return SALT;
    }

    public static String getThemesTableName() {
        return THEMES_TABLE_NAME;
    }

    public static String getThemeName() {
        return THEME_NAME;
    }

    public static String getOCCUPIED() {
        return OCCUPIED;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static String getUserLastname() {
        return USER_LASTNAME;
    }

    public static String getUserAccess() {
        return USER_ACCESS;
    }

    public static String getID() {
        return ID;
    }

    //Tables column names
    private static final String USERS_TABLE_NAME = "users";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SALT = "salt";
    private static final String USER_NAME = "user_name";
    private static final String USER_LASTNAME = "user_lastname";
    private static final String USER_ACCESS = "user_access";

    private static final String THEMES_TABLE_NAME = "themes";
    private static final String ID = "number";
    private static final String THEME_NAME = "theme_name";
    private static final String OCCUPIED = "student_login";

    //Caption and Database version
    private static final String DB_NAME = "Themes.db";
    private static final int DB_VERSION = 8;

    //Constructor
    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Creating Tables
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE_NAME
            + "(" + LOGIN + " TEXT PRIMARY KEY, "
            + SALT + " TEXT NOT NULL, "
            + PASSWORD + " TEXT NOT NULL, "
            + USER_NAME + " TEXT NOT NULL,"
            + USER_LASTNAME + " TEXT NOT NULL,"
            + USER_ACCESS + " INTEGER NOT NULL);";
    private static final String CREATE_THEMES_TABLE = "CREATE TABLE IF NOT EXISTS " + THEMES_TABLE_NAME
            + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + THEME_NAME + " TEXT NOT NULL,"
            + OCCUPIED + " TEXT);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_THEMES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    //Updating Tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + THEMES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);

        onCreate(db);
    }
}