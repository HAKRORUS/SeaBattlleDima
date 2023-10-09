package com.example.seabattlledima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextEnemy;
    EditText editTextSize;
    EditText editTextShips;
    TextView textViewEror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEnemy = findViewById(R.id.editTextNumberEnemy);
        editTextSize = findViewById(R.id.editTextNumberSize);
        editTextShips = findViewById(R.id.editTextNumberShips);
        textViewEror = findViewById(R.id.textViewEror);
    }

    public void check(View view) {
        boolean isCorect= true;
        Intent intent = new Intent(MainActivity.this, SizeActivity.class);
        if (Integer.parseInt(String.valueOf(editTextEnemy.getText())) <= 10 && Integer.parseInt(String.valueOf(editTextEnemy.getText())) !=0) {
            intent.putExtra("Enemy",Integer.parseInt(String.valueOf(editTextEnemy.getText())));
            isCorect= true;
        } else {
            isCorect = false;
        }
        if (Integer.parseInt(String.valueOf(editTextSize.getText())) >= 10 && Integer.parseInt(String.valueOf(editTextSize.getText())) <= 28) {
            intent.putExtra("Size",Integer.parseInt(String.valueOf(editTextSize.getText())));
            isCorect= true;
        } else {
            isCorect = false;
        }
        if (Integer.parseInt(String.valueOf(editTextShips.getText())) == 1 || Integer.parseInt(String.valueOf(editTextShips.getText())) ==0) {
            intent.putExtra("Ships",Integer.parseInt(String.valueOf(editTextShips.getText())));
            isCorect= true;
        } else {
            isCorect = false;
        }
        if (isCorect) {
        startActivity(intent);
        } else {
            textViewEror.setText("Что-то не так");
        }
    }
}