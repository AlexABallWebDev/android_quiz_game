/*
 * Author: Alex Ball
 * Date: 04/19/2016
 * Filename: QuestionsActivity.java
 *
 * Main activity that displays questions and true/false buttons.
 */
package net.greenrivertech.alexb.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity that displays questions and true/false buttons.
 *
 * @author Alex Ball
 */
public class QuestionsActivity extends AppCompatActivity {

    //the view that displays the question text
    private TextView questionText;

    //the view that displays whether the current question was answered already or not
    private TextView answeredText;

    //the model that represents the game (the game logic)
    private QuizModel model;

    /**
     * Name for the user's score, which is passed to ScoreSummaryActivity to be displayed.
     */
    public static final String MY_SCORE = "net.greenrivertech.alexb.quizgame.MY_SCORE";

    /**
     * Name for the number of questions the user answered for this game.
     */
    public static final String NUM_QUESTIONS_ANSWERED =
            "net.greenrivertech.alexb.quizgame.NUM_QUESTIONS_ANSWERED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get views for displaying question text, if the question was answered or not,
        //false button, true button, and previous/next question buttons.
        questionText = (TextView) findViewById(R.id.questionText);
        answeredText = (TextView) findViewById(R.id.answeredText);

        Button falseAnswer = (Button) findViewById(R.id.falseAnswer);
        Button trueAnswer = (Button) findViewById(R.id.trueAnswer);

        ImageButton previousQuestion = (ImageButton) findViewById(R.id.previousQuestion);
        ImageButton nextQuestion = (ImageButton) findViewById(R.id.nextQuestion);

        //create model
        model = new QuizModel();

        //display first question
        if (questionText != null) {
            questionText.setText(model.getCurrentQuestionText());
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

        //listener for the previous button
        if (previousQuestion != null) {
            previousQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.previousQuestion();
                    updateGameDisplay();
                }
            });
        }

        //listener for the next button
        if (nextQuestion != null) {
            nextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.nextQuestion();
                    updateGameDisplay();
                }
            });
        }

        //listener for the question text display (acts as a next button)
        if (questionText != null) {
            questionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.nextQuestion();
                    updateGameDisplay();
                }
            });
        }
    }

    /**
     * Answers the current question with the user's answer, displays a toast that tells the user
     * if they were correct, then either displays the next question, or starts the score summary
     * activity.
     *
     * @param answer The user's answer.
     */
    public void updateGameDisplay(boolean answer) {
        //answer question and get whether the user was correct or not.
        boolean result = model.answerQuestion(answer);

        //if result is true, user was correct, otherwise they were wrong.
        if (result) {
            popToast(getResources().getString(R.string.correct));
        } else {
            popToast(getResources().getString(R.string.wrong));
        }

        updateGameDisplay();
    }

    /**
     * Update the game display by displaying the current question. If the game is over,
     * start the ScoreSummaryActivity instead.
     */
    public void updateGameDisplay() {
        //check if game is over. If it is, start the score summary activity.
        //otherwise, update questionText with the next question.
        if (model.isGameOver()) {
            //start score summary activity
            startScoreSummary();
        } else {
            //get new question and update the questionText.
            if (questionText != null) {
                questionText.setText(model.getCurrentQuestionText());
            }

            //check if this question was answered and update the answeredText.
            if (answeredText != null) {
                if (model.isCurrentQuestionAnswered()) {
                    if (model.isCurrentQuestionAnsweredCorrectly()) {
                        String wasAnsweredCorrect = getResources()
                                .getString(R.string.wasAnsweredCorrect);
                        answeredText.setText(wasAnsweredCorrect);
                    } else {
                        String wasAnsweredWrong = getResources()
                                .getString(R.string.wasAnsweredWrong);
                        answeredText.setText(wasAnsweredWrong);
                    }
                } else {
                    answeredText.setText(null);
                }
            }
        }
    }

    /**
     * Starts the ScoreSummaryActivity, displaying the user's score.
     */
    public void startScoreSummary() {
        //display game over text
        popToast(getResources().getString(R.string.gameOver));

        //Create intent, put quiz answer information in it, and start the activity.
        Intent intent = new Intent(this, ScoreSummaryActivity.class);
        intent.putExtra(MY_SCORE, model.getScore());
        intent.putExtra(NUM_QUESTIONS_ANSWERED, model.getNumQuestionsAnswered());
        startActivity(intent);
    }

    /**
     * pop a toast message.
     *
     * @param text The text to be displayed.
     */
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
