package com.diceroller.domain;

import java.util.Random;

public class Die {
    private final int faces;
    private int value;
    private final Random random;

    public Die(int faces) {
        if (faces < 1) {
            throw new IllegalArgumentException("Number of faces must be greater than 0.");
        }
        this.faces = faces;
        this.random = new Random();
        this.value = roll();
    }

    public int roll() {
        this.value = random.nextInt(faces) + 1;
        return value;
    }

    public int getValue() {
        return value;
    }

    public int getFaces() {
        return faces;
    }
}
