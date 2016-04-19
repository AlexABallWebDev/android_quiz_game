package net.greenrivertech.alexb.quizgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {

    //the view that displays the question text
    private TextView questionText;

    //the model that represents the game (the game logic)
    private QuizModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get views for displaying question text, false button, and true button.
        questionText = (TextView) findViewById(R.id.questionText);

        Button falseAnswer = (Button) findViewById(R.id.falseAnswer);
        Button trueAnswer = (Button) findViewById(R.id.trueAnswer);

        //create model
        model = new QuizModel();

        //display first question
        if (questionText != null) {
            questionText.setText(model.getCurrentQuestion());
        }

        //listener for the false button
        if (falseAnswer != null) {
            falseAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateGameDisplay(false);
                }
            });
        }

        //listener for the true button
        if (trueAnswer != null) {
            trueAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateGameDisplay(true);
                }
            });
        }
    }

    public void updateGameDisplay(boolean answer) {
        //answer question and get whether the user was correct or not.
        boolean result = model.answerQuestion(answer);

        //if result is true, user was correct, otherwise they were wrong.
        if (result) {
            popToast("Correct!");
        } else {
            popToast("Wrong!");
        }

        //check if game is over. If it is, start the score summary activity.
        //otherwise, update questionText with the next question.
        if (model.isGameOver()) {
            //start score summary activity
            popToast("Game over!");
        } else {
            //get new question and update the questionText.
            if (questionText != null) {
                questionText.setText(model.getCurrentQuestion());
            }
        }
    }

    public void popToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
