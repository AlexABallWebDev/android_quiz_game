package net.greenrivertech.alexb.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ScoreSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the user's score from the intent
        Intent intent = getIntent();
        int score = intent.getIntExtra(QuestionsActivity.MY_SCORE, 0);

        //get the scoreSummary TextView
        TextView scoreSummary = (TextView) findViewById(R.id.scoreSummary);

        //display the user's score
        if (scoreSummary != null) {
            scoreSummary.setText("Your score: " + score);
        }
    }

}
