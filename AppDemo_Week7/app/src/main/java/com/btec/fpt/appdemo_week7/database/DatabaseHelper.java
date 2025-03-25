package com.btec.fpt.appdemo_week7.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.btec.fpt.appdemo_week7.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_SEX = "sex";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EMAIL + " TEXT UNIQUE, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_FULLNAME + " TEXT, " +
            COLUMN_BIRTHDAY + " TEXT, " +
            COLUMN_SEX + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public boolean checkUser(String email, String enteredPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE email=?", new String[]{email});

        if (cursor.moveToFirst()) {
            String storedHash = cursor.getString(0);
            cursor.close();
            // Compare entered password (hashed) with stored hash
            return storedHash.equals(hashPassword(enteredPassword));
        }

        cursor.close();
        return false;
    }


    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Insert user with hashed password
    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Hash the password before storing it
        String hashedPassword = hashPassword(user.getPassword());

        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, hashedPassword); // Store hashed password
        values.put(COLUMN_FULLNAME, user.getFullName());
        values.put(COLUMN_BIRTHDAY, user.getBirthday());
        values.put(COLUMN_SEX, user.getSex());

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1; // Returns true if insertion is successful
    }


    // Check if email exists
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=?", new String[]{email});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
