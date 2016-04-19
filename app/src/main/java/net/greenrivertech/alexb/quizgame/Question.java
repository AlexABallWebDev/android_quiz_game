/*
 * Author: Alex Ball
 * Date: 04/19/2016
 * Filename: Question.java
 *
 * This class represents a true/false question for the Quiz Game. Question objects
 * contain a question string (the actual question) and the correct answer for that question.
 *
 */
package net.greenrivertech.alexb.quizgame;

/**
 * This class represents a true/false question for the Quiz Game. Question objects
 * contain a question string (the actual question) and the correct answer for that question.
 *
 * @author Alex Ball
 */
public class Question {
    private String question;
    private boolean correctAnswer;

    /**
     * Constructor that builds a new Question with the given question string and answer.
     *
     * @param question The question itself, as a string.
     * @param correctAnswer The correct answer to this question.
     */
    public Question(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the question string.
     *
     * @return The question string.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the correct answer for this question.
     *
     * @return The correct answer for this question.
     */
    public boolean getCorrectAnswer() {
        return correctAnswer;
    }
}
