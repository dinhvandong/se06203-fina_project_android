package com.btec.fpt.appdemo_week7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnClick = (Button) findViewById(R.id.btn_click);

        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

// Find UI elements and customize
        ImageView toastIcon = layout.findViewById(R.id.toast_icon);
        TextView toastText = layout.findViewById(R.id.toast_text);

        //toastIcon.setImageResource(R.drawable.ic_success); // Set icon
        toastText.setText("This is a custom Toast!"); // Set message

// Create and show the Toast
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

     final Dialog dialog = new AlertDialog.Builder(MainActivity.this)
             .setTitle("Confirmation")
             .setMessage("Are you sure you want to proceed?")
             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     Toast.makeText(MainActivity.this, "Confirmed!", Toast.LENGTH_SHORT).show();
                 }
             })
             .setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             }).create();

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Click me", Toast.LENGTH_LONG).show();
              // toast.show();
              //  dialog.show();

                showCustomDialog();


            }
        });
    }



    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);

        // Initialize UI elements
        TextView title = dialog.findViewById(R.id.dialog_title);
        EditText message = dialog.findViewById(R.id.dialog_input);
        Button closeButton = dialog.findViewById(R.id.dialog_ok_button);

        // Set data dynamically if needed
        title.setText("Hello!");
        message.setText("This is a custom dialog example.");

        // Handle button click
        closeButton.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }
}