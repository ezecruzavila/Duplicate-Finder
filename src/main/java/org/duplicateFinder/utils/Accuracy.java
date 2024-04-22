package org.duplicateFinder.utils;

public enum Accuracy {
    EXACT("Exact"),
    VERY_HIGH("Very High"),
    HIGH("High"),
    LOW("Low"),
    ZERO("Zero");

    public final String value;

    Accuracy(String value) {
        this.value = value;
    }

}
