package com.issergeev.themes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;

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

    public void close() {
        dbhelper.close();
    }

    //Add Admin
    public void addAdmin() {
        String[] password = Encryption.Encrypt("P@ssw0rd");

        ContentValues values = new ContentValues();

        values.put(DB.getLOGIN(), "admin");
        values.put(DB.getPASSWORD(), "d9b3e3519a9ee9b1e791f296de7ab1425909b0f7c364b2f3bf7908550fc65c58ec5d68413d31f04d58075eddfb449c152aa59246ca087b79acb0bb669f137aef");
        values.put(DB.getSALT(), "zLMH1qIphh");
        values.put(DB.getUserName(), "Иван");
        values.put(DB.getUserLastname(), "Сергеев");
        values.put(DB.getUserAccess(), 2);
        database.insert(DB.getUsersTableName(), null, values);

        Log.d("2", "inserted");
    }
}