package com.btec.fpt.appdemo_week7;

import static org.junit.Assert.*;

import com.btec.fpt.appdemo_week7.database.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;

public class DatabaseHelperTest {

    private DatabaseHelper dbHelper;

    @Before
    public void setUp() {
        dbHelper = new DatabaseHelper(null); // Context is not needed for logic tests
    }

    @Test
    public void testHashPassword() {
        String password = "123456";
        String hashedPassword1 = dbHelper.hashPassword(password);
        String hashedPassword2 = dbHelper.hashPassword(password);

        // Ensure the same password always generates the same hash
        assertEquals(hashedPassword1, hashedPassword2);

        // Ensure different passwords produce different hashes
        assertNotEquals(hashedPassword1, dbHelper.hashPassword("654321"));
    }
}
