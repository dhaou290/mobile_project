package com.example.projetquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class quizResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
    }


    public void onNextButtonClick(View view) {
        // Intent pour ouvrir quizActivity2
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}