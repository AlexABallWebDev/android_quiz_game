package net.greenrivertech.alexb.quizgame;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

/**
 * This class represents a quiz game in which a user has a score. They increase their score
 * by correctly answering true/false questions.
 *
 * @author Alex Ball
 */
public class QuizModel {
    //the user's score; the number of correctly answered questions for this game.
    private int score;

    //the number for the question that is currently on-screen
    private int currentQuestionNum;

    //map representing the list of questions used for this game. keys represent the question
    //numbers, values represent whether they were correctly answered or not.
    private int[] questions;

    //the question number that the user is on (used for ordering the questions; user may be
    //on 1st question (0), or 3rd question (2),etc.)
    private int gameQuestionNum;

    private int numQuestionsAnswered;

    /**
     * The number of questions in this quiz
     */
    public static final int NUM_QUESTIONS = 3;

    /**
     * The full list of questions that the quiz game might ask the user.
     * A small number of questions are selected from this list for each game.
     */
    public static SparseArray<String> fullQuestionList;

    /**
     * The full list of answers that holds the correct answers for each question.
     */
    public static SparseBooleanArray answerList;

    static {
        fullQuestionList = new SparseArray<>();
        fullQuestionList.put(1, "Question 1: answer is true");
        fullQuestionList.put(2, "Question 2: answer is false");
        fullQuestionList.put(3, "Question 3: answer is true");

        answerList = new SparseBooleanArray();
        answerList.put(1, true);
        answerList.put(2, false);
        answerList.put(3, true);
    }

    /**
     * Constructor that creates a new QuizModel with score 0 and gameQuestionNum 0 (first question).
     */
    public QuizModel() {
        score = 0;
        gameQuestionNum = 0;
        numQuestionsAnswered = 0;

        //fill the map with questions.
        questions = new int[NUM_QUESTIONS];
        for(int i = 0; i < NUM_QUESTIONS; i++) {
            questions[i] = i + 1;
        }

        //set currentQuestion to the first question.
        updateCurrentQuestion();
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
     * Returns the current question.
     *
     * @return The current question
     */
    public String getCurrentQuestion() {
        return fullQuestionList.get(currentQuestionNum);
    }

    /**
     * Takes the user's answer and compares it to the correct answer. If they match, then
     * the user's score increments and this method returns true. Otherwise, this method
     * returns false. Either way, move on to next question and increment numQuestionsAnswered.
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
        if (answer == answerList.get(currentQuestionNum)) {
            result = true;
            score++;
        }

        //the user answered a question; increment numQuestionsAnswered.
        numQuestionsAnswered++;

        //advance to the next question.
        nextQuestion();

        //return result (true if user was correct, false otherwise).
        return result;
    }

    /**
     * Advance to the next question without answering the current one.
     * Returns true if successful, false otherwise.
     *
     * @return True if successful, false otherwise.
     */
    public boolean nextQuestion() {
        //if this is the last question, do not advance, and return false.
        if (gameQuestionNum >= NUM_QUESTIONS - 1) {
            return false;
        }

        //if this is not the last question, advance to the next question and return true.
        gameQuestionNum++;
        updateCurrentQuestion();
        return true;
    }

    /**
     * Go back to the previous question without answering the current one.
     * Returns true if successful, false otherwise.
     *
     * @return True if successful, false otherwise.
     */
    public boolean previousQuestion() {
        //if this is the first question, do not go back, and return false.
        if (gameQuestionNum <= 0) {
            return false;
        }

        //if this is not the first question, go back to the previous question and return true.
        gameQuestionNum--;
        updateCurrentQuestion();
        return true;
    }

    /**
     * Updates the currentQuestion based on this game's questions list and
     * gameQuestionNum (which question the user is currently on).
     */
    public void updateCurrentQuestion() {
        currentQuestionNum = questions[gameQuestionNum];
    }

    /**
     * Returns true if the game is over. The game is considered over when the user has
     * answered NUM_QUESTIONS questions (whether they are correct or not).
     *
     * @return True if the user has answered enough questions, false otherwise.
     */
    public boolean isGameOver() {
        return numQuestionsAnswered >= NUM_QUESTIONS;
    }
}
