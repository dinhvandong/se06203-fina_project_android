package com.btec.fpt.appdemo_week7;


import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.btec.fpt.appdemo_week7.database.DatabaseHelper;
import com.btec.fpt.appdemo_week7.models.User;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHelperInstrumentedTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dbHelper = new DatabaseHelper(context);
    }

    @Test
    public void testInsertUser() {
        User user = new User("test@example.com", "123456", "John Doe", "01-01-1990", "Male");
        boolean result = dbHelper.insertUser(user);
        assertTrue(result); // Ensure user is inserted
    }

    @Test
    public void testCheckUser() {
        dbHelper.insertUser(new User("test@example.com", "123456", "John Doe", "01-01-1990", "Male"));
        assertFalse(dbHelper.checkUser("test@example.com", "123456"));
        assertFalse(dbHelper.checkUser("wrong@example.com", "password"));
    }
}

