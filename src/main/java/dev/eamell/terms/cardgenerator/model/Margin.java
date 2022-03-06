package dev.eamell.terms.cardgenerator.model;

public record Margin(float top, float bottom, float left, float right) {
    public static Margin equalMargin(float size) {
        return new Margin(size, size, size, size);
    }
}
