package com.issergeev.themes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlDataWorker {

    //Classes to work with Database
    private DB dbhelper;
    private SQLiteDatabase database;

    private Context ourcontext;

    //Constructor
    public SqlDataWorker(Context c) {
        ourcontext = c;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public SqlDataWorker open() throws SQLException {
        dbhelper = new DB(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void insertUser(String login, String password, String name, String lastname, String user_access) {
        ContentValues values = new ContentValues();

        values.put(DB.getLOGIN(), login);
        values.put(DB.getPASSWORD(), password);
        values.put(DB.getUserName(), name);
        values.put(DB.getUserLastname(), lastname);
        values.put(DB.getUserAccess(), user_access);
        database.insert(DB.getUsersTableName(), null, values);
    }

    public void updateUser(String login, String password, String name, String lastname) {
        ContentValues values = new ContentValues();

        values.put(DB.getPASSWORD(), password);
        values.put(DB.getUserName(), name);
        values.put(DB.getUserLastname(), lastname);
        database.update(DB.getUsersTableName(), values, DB.getLOGIN() + " = ?", new String[]{login});
    }

    public void deleteUser(String login) {
        database.delete(DB.getUsersTableName(), DB.getLOGIN() + " = ?",
                new String[]{login});
    }

    public int deleteTheme(long id) {
        return database.delete(DB.getThemesTableName(), DB.getID() + " = ?",
                new String[]{String.valueOf(id)});
    }

    public long insertTheme(String themeName) {
        ContentValues values = new ContentValues();

        values.put(DB.getThemeName(), themeName);
        return database.insert(DB.getThemesTableName(), null, values);
    }

    //Reading rows in a Table in Database
//    public String[] readEntry() {
//        String[] allColumns = new String[] { DB.getUserLogin(), DB.getUserName(),
//                DB.getUserLastname() };
//        String[] users = null;
//
//        Cursor c = database.query(DB.getUsersTableName(), allColumns, null, null, null,
//                null, null);
//
//        if (c != null && c.moveToFirst()) {
//            users = new String[] { c.getString(c.getColumnIndex(DB.getUserLogin())),
//                    c.getString(c.getColumnIndex(DB.getUserName())),
//                    c.getString(c.getColumnIndex(DB.getUserLastname())),
//                    c.getString(c.getColumnIndex(DB.getUserAccess()))};
//        } else {
//            Log.d("", "null"); }
//
//        return users;
//    }

    public String[] getUser(String login) {
        String[] columns = new String[] { DB.getLOGIN(), DB.getPASSWORD(), DB.getSALT(), DB.getUserName(), DB.getUserLastname(),
        DB.getUserAccess() };
        String s[] = null;

        Cursor c = database.query(DB.getUsersTableName(), columns, DB.getLOGIN() + " = ?", new String[] {login}, null,
                null, null);

        if (c != null && c.moveToFirst()) {
            s = new String[] { c.getString(c.getColumnIndex(DB.getLOGIN())), c.getString(c.getColumnIndex(DB.getPASSWORD())),
                    c.getString(c.getColumnIndex(DB.getSALT())), c.getString(c.getColumnIndex(DB.getUserName())),
                    c.getString(c.getColumnIndex(DB.getUserLastname())), c.getString(c.getColumnIndex(DB.getUserAccess()))};
        }

        return s;
    }

    public List<Student> getStudentList() {
        String[] columns = new String[] { DB.getUserName(), DB.getUserLastname(), DB.getThemeId() };
        List<Student> students = new ArrayList<Student>();

        Cursor c = database.query(DB.getUsersTableName(), columns, null, null, null,
                null, null);

        if (c != null && c.moveToFirst()) {
            do {
                students.add(new Student(c.getString(c.getColumnIndex(DB.getUserName())),
                        c.getString(c.getColumnIndex(DB.getUserLastname())),
                        c.getString(c.getColumnIndex(DB.getThemeId()))));
            } while (c.moveToNext());
        }

        return students;
    }

//    public ArrayList<String> getThemesCount() {
//        ArrayList<String> numbers = new ArrayList<>();
//
//        Cursor c = database.query(DB.getThemesTableName(), new String[] {DB.getID()}, null, null, null,
//                null, null);
//
//        if (c != null && c.moveToFirst()) {
//            do {
//                numbers.add(c.getString(c.getColumnIndex(DB.getOCCUPIED())));
//            } while (c.moveToNext());
//        }
//
//        return numbers;
//    }

    public List<Theme> getThemesList() {
        String[] columns = new String[] { DB.getID(), DB.getThemeName() };
        List<Theme> themes = new ArrayList<Theme>();

        Cursor c = database.query(DB.getThemesTableName(), columns, null, null, null,
                null, null);

        if (c != null && c.moveToFirst()) {
            do {
                themes.add(new Theme(Long.valueOf(c.getString(c.getColumnIndex(DB.getID()))),
                        c.getString(c.getColumnIndex(DB.getThemeName()))));
            } while (c.moveToNext());
        }

        return themes;
    }

    public void close() {
        dbhelper.close();
    }

    //Add Admin
    public long addAdmin() {
        ContentValues values = new ContentValues();

        values.put(DB.getLOGIN(), "admin");
        values.put(DB.getPASSWORD(), "d9b3e3519a9ee9b1e791f296de7ab1425909b0f7c364b2f3bf7908550fc65c58ec5d68413d31f04d58075eddfb449c152aa59246ca087b79acb0bb669f137aef");
        values.put(DB.getSALT(), "zLMH1qIphh");
        values.put(DB.getUserName(), "Иван");
        values.put(DB.getUserLastname(), "Сергеев");
        values.put(DB.getUserAccess(), 2);

        try {
            return database.insert(DB.getUsersTableName(), null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public int deleteAdmin(String login) {
        return database.delete(DB.getUsersTableName(), DB.getLOGIN() + " = ?",
                new String[]{login});
    }
}