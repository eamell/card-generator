package dev.eamell.terms.cardgenerator.exception;

public class CardGenerationFailureException extends RuntimeException {
    public CardGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
