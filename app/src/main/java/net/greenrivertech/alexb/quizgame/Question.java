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
    //the question text (question statement)
    private String question;

    //what is the correct answer to this question?
    private boolean correctAnswer;

    //has this question been answered yet?
    private boolean isAnswered;

    //if it was answered, was it answered with true, or false?
    private boolean answer;

    /**
     * Constructor that builds a new Question with the given question string and answer.
     *
     * @param question The question itself, as a string.
     * @param correctAnswer The correct answer to this question.
     */
    public Question(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.isAnswered = false;
        this.answer = false;
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

    /**
     * Returns true if this question has been answered, false otherwise.
     *
     * @return True if this question has been answered, false otherwise.
     */
    public boolean isAnswered() {
        return isAnswered;
    }

    /**
     * Answers this question with the given answer. If it is the correct answer, return true.
     * Otherwise, return false.
     *
     * @param answer The answer to this question; correct if it matches the correctAnswer.
     *
     * @return True if the answer matches the correctAnswer, false otherwise.
     */
    public boolean answerQuestion(boolean answer) {
        this.answer = answer;
        isAnswered = true;
        return getIsCorrectlyAnswered();
    }

    /**
     * Returns the answer that was given to this question. Throws an IllegalStateException if
     * called before answering this question.
     *
     * @return The answer that was given to this question.
     *
     * @throws IllegalStateException If called before answering this question.
     */
    public boolean getAnswer() {
        if (!isAnswered) {
            throw new IllegalStateException("Cannot get answer from unanswered question!");
        }

        return answer;
    }

    /**
     * Returns true if the answer given to this question matches the correctAnswer.
     *
     * @return True if the answer given to this question matches the correctAnswer.
     */
    public boolean getIsCorrectlyAnswered() {
        return answer == correctAnswer;
    }

    /**
     * Returns a copy of this question.
     *
     * @return A copy of this question.
     */
    public Question copyQuestion() {
        return new Question(question, correctAnswer);
    }
}
