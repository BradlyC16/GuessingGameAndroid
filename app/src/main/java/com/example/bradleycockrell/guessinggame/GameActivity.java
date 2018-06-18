package com.example.bradleycockrell.guessinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Button guessButton;
    private TextView clue;
    private EditText guess;
    private int generatedNumber;
    private int numberOfGuesses = 0;
    private final int MAX_GUESS_COUNT = 4;
    public static final String winningNumber = "WINING_NUMBER";

//TODO Fix restarting game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        guessButton = findViewById(R.id.submit_guessbutton);
        clue = findViewById(R.id.clue_textview);
        guess = findViewById(R.id.guess_edittext);


//        Toast.makeText(this, Integer.toString(generatedNumber), Toast.LENGTH_LONG).show();

        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        generatedNumber = (int) Math.ceil(Math.random() * 100);

        numberOfGuesses = 0;
        clue.setVisibility(View.VISIBLE);
        guess.setText("");
    }

    private void setListener() {
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateGuess();
            }
        });

    }

    //    Check to make sure the user had put in a valid number
    private void validateGuess() {

        try {
            int userGuess = Integer.parseInt(guess.getText().toString());
            if (userGuess > 100 || userGuess <= 0) {
                clue.setText("Enter a number between 1 and 100");
                clue.setVisibility(View.VISIBLE);
                guess.setText("");
            } else {
                checkGuess(userGuess);
            }
        } catch (NumberFormatException nfe) {
            clue.setText("Enter a number");
            clue.setVisibility(View.VISIBLE);

        }
    }

    @SuppressLint("StringFormatInvalid")
    private void checkGuess(int userGuess) {

        if (userGuess == generatedNumber) {
            Intent winner = new Intent(this, MainActivity.class);
            startActivity(winner);


        } else if (numberOfGuesses == MAX_GUESS_COUNT) {
            Intent loser = new Intent(this, ResultsActivity.class);
            loser.putExtra(winningNumber, generatedNumber);
            startActivity(loser);


        } else if (userGuess < generatedNumber) {
            clue.setText(R.string.higher);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            Toast.makeText(this, getString(R.string.chances_left, (5 - numberOfGuesses)), Toast.LENGTH_SHORT).show();
        } else if (userGuess > generatedNumber) {
            clue.setText(R.string.lower);
            clue.setVisibility(View.VISIBLE);
            guess.setText("");
            numberOfGuesses++;
            Toast.makeText(this, getString(R.string.chances_left, (5 - numberOfGuesses)), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
        public void onBackPressed () {
//        Removed super.onBackPressed(); to make sure if the back button is pressed nothing will happen.
        }
    }
