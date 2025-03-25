package com.btec.fpt.appdemo_week7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.btec.fpt.appdemo_week7.database.DatabaseHelper;
import com.btec.fpt.appdemo_week7.models.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etFullName, etBirthday;
    private RadioGroup rgSex;
    private Button btnRegister;
    private TextView registerText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        // Initialize Views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);
        etBirthday = findViewById(R.id.etBirthday);
        rgSex = findViewById(R.id.rgSex);
        btnRegister = findViewById(R.id.btnRegister);
        registerText = findViewById(R.id.registerText);

        // Register Button Click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        // Navigate to Login Activity
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String birthday = etBirthday.getText().toString().trim();
        int selectedId = rgSex.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String sex = (selectedRadioButton != null) ? selectedRadioButton.getText().toString() : "";

        // Validation
        if (email.isEmpty() || password.isEmpty() || fullName.isEmpty() || birthday.isEmpty() || sex.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email already exists
        if (dbHelper.isEmailExists(email)) {
            Toast.makeText(this, "Email already registered!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save to Database
        User user = new User(email, password, fullName, birthday, sex);
        boolean isInserted = dbHelper.insertUser(user);

        if (isInserted) {
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration Failed. Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
}
