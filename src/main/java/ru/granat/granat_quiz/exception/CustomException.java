package ru.granat.granat_quiz.exception;

public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
