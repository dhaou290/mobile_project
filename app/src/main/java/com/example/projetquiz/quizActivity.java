package com.example.projetquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class quizActivity extends AppCompatActivity {
    private TextView option1;
    private TextView option2;
    private TextView option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the category information from the intent
        String categoryId = getIntent().getStringExtra("categoryId");
        String categoryName = getIntent().getStringExtra("categoryName");

        // Initialize references to your TextView options
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        // Set onClickListener for option1
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for option1 click
                handleOptionClick(option1);
            }
        });

        // Set onClickListener for option2
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for option2 click
                handleOptionClick(option2);
            }
        });

        // Set onClickListener for option3
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic for option3 click
                handleOptionClick(option3);
            }
        });
    }

    // Method to handle option click
    private void handleOptionClick(TextView clickedOption) {
        // Disable click events on all options
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);

        // Implement your logic here based on the clicked option
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (clickedOption == option3) {
            builder.setMessage("Vrai");
            clickedOption.setBackgroundResource(R.drawable.right_option); // Change background of clicked option to green
        } else {
            builder.setMessage("Faux");
            clickedOption.setBackgroundResource(R.drawable.wrong_option); // Change background of clicked option to red
        }
        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Close the dialog after a short delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss(); // Close the dialog
                // Reset the backgrounds of all options
                option1.setBackgroundResource(R.drawable.unselected_option);
                option2.setBackgroundResource(R.drawable.unselected_option);
                option3.setBackgroundResource(R.drawable.unselected_option);
                // Enable click events on all options
                option1.setClickable(true);
                option2.setClickable(true);
                option3.setClickable(true);
            }
        }, 1500); // 1500 milliseconds (1.5 seconds) delay
    }






    // Méthode appelée lors du clic sur le bouton "suivant"
    public void onNextButtonClick(View view) {
        // Intent pour ouvrir quizActivity2
        Intent intent = new Intent(this, quizActivity2.class);
        startActivity(intent);
    }
}