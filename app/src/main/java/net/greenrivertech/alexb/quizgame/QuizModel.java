package net.greenrivertech.alexb.quizgame;

import android.util.SparseBooleanArray;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a quiz game in which a user has a score. They increase their score
 * by correctly answering true/false questions.
 *
 * @author Alex Ball
 */
public class QuizModel {
    //the user's score; the number of correctly answered questions for this game.
    private int score;

    //total number of questions
    private int totalNumQuestions;

    //the number for the question that is currently on-screen
    private int currentQuestionNum;

    //map representing the list of questions used for this game. keys represent the question
    //numbers, values represent whether they were correctly answered or not.
    private SparseBooleanArray questions;

    //the question number that the user is on (used for ordering the questions; user may be
    //on 1st question (0), or 3rd question (2),etc.)
    private int gameQuestionNum;

    /**
     * The number of questions in this quiz
     */
    public static final int NUM_QUESTIONS = 3;

    /**
     * Constructor that creates a new QuizModel with score 0 and gameQuestionNum 1.
     */
    public QuizModel() {
        score = 0;
        gameQuestionNum = 1;

        //retrieve the total number of questions
        totalNumQuestions = 3;

        //fill the map with questions.
        questions = new SparseBooleanArray(NUM_QUESTIONS);
        for(int i = 0; i < NUM_QUESTIONS; i++) {
            questions.put(i + 1, false);
        }

        //randomly pick a first question.
        currentQuestionNum = 1;
    }

    /**
     * Returns the current score (number of correctly answered questions).
     *
     * @return Current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the number representing the current question.
     *
     * @return The number representing the current question.
     */
    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    /**
     * Takes the user's answer and compares it to the correct answer. If they match, then
     * the user's score increments and this method returns true. Otherwise, this method
     * returns false. The currentQuestionNum increments either way (move on to next question).
     *
     * @param answer The user's answer.
     *
     * @throws IllegalStateException Throws IllegalStateException if called when
     * the game is already over.
     *
     * @return True if the user was correct, false otherwise.
     */
    public boolean answerQuestion(boolean answer) {

        //if the game is over, then a question cannot be answered.
        if (isGameOver()) {
            throw new IllegalStateException("Cannot answer questions when game is over.");
        }

        //assume user was incorrect.
        boolean result = false;

        //if the answer to the question represented by currentQuestionNum matches
        //the user's answer, then increase score and set result to true (user was correct).
        if (answer) {
            result = true;
            score++;
        }

        //increase gameQuestionNum either way.
        gameQuestionNum++;

        //return result (true if user was correct, false otherwise).
        return result;
    }

    public boolean isGameOver() {
        return gameQuestionNum > NUM_QUESTIONS;
    }
}
