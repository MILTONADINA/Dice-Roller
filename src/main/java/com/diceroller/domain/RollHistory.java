package com.diceroller.domain;

import java.time.LocalDateTime;

public class RollHistory {
    private final int id;
    private final LocalDateTime timestamp;
    private final int numDice;
    private final int faces;
    private final int totalResult;
    private final String breakdown;

    public RollHistory(int id, LocalDateTime timestamp, int numDice, int faces, int totalResult, String breakdown) {
        this.id = id;
        this.timestamp = timestamp;
        this.numDice = numDice;
        this.faces = faces;
        this.totalResult = totalResult;
        this.breakdown = breakdown;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getNumDice() {
        return numDice;
    }

    public int getFaces() {
        return faces;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public String getBreakdown() {
        return breakdown;
    }
}
