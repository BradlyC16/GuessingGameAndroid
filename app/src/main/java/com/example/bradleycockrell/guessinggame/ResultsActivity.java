package com.example.bradleycockrell.guessinggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.bradleycockrell.guessinggame.GameActivity.winningNumber;

public class ResultsActivity extends AppCompatActivity {

    private Button playAgainButton;
    private TextView correctNumberTextview;
    private TextView resultsTextview;
    private ImageView resultImageview;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        playAgainButton = findViewById(R.id.restart_button);
        correctNumberTextview = findViewById(R.id.number_textview);
        resultsTextview = findViewById(R.id.result_textview);
        resultImageview = findViewById(R.id.winning_imageView);

        setListener();

//Get winning number from intent IF user has lost
        intent = getIntent();
        if (intent.hasExtra(winningNumber)) {
            setLosingData();
        }
    }

    private void setLosingData() {
        int winningNumber = intent.getIntExtra(GameActivity.winningNumber,0);
        resultsTextview.setText(R.string.You_lose);
        correctNumberTextview.setText(getString(R.string.the_correct_number_was_1_d, winningNumber));
        correctNumberTextview.setVisibility(View.VISIBLE);

        resultImageview.setImageResource(R.drawable.losingsadface);
    }

    //    Method to handle setting the listener for the playAgainButton
    private void setListener() {

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
