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
        int numQuestionsAnswered = intent.getIntExtra(QuestionsActivity.NUM_QUESTIONS_ANSWERED, 0);

        //get the scoreView TextView
        TextView scoreView = (TextView) findViewById(R.id.scoreView);

        //get the numQuestionsView TextView
        TextView numQuestionsView = (TextView) findViewById(R.id.numQuestionsView);

        //format score and numQuestions
        String scoreText = String.format("%s %d",
                getResources().getText(R.string.yourScore), score);
        String numQuestionsText = String.format("%s %d",
                getResources().getText(R.string.numQuestionsAnswered), numQuestionsAnswered);

        //display the user's score
        if (scoreView != null) {
            scoreView.setText(scoreText);
        }

        //display the number of questions the user answered
        if (numQuestionsView != null) {
            numQuestionsView.setText(numQuestionsText);
        }
    }

}
