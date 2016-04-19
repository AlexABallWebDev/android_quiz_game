package net.greenrivertech.alexb.quizgame;

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

    /**
     * The number of questions in this quiz
     */
    public static final int NUM_QUESTIONS = 3;

    /**
     * Constructor that creates a new QuizModel with score 0 and currentQuestionNum 1.
     */
    public QuizModel() {
        score = 0;
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

        //increase currentQuestionNum either way.
        currentQuestionNum++;

        //return result (true if user was correct, false otherwise).
        return result;
    }

    public boolean isGameOver() {
        return currentQuestionNum > NUM_QUESTIONS;
    }
}
