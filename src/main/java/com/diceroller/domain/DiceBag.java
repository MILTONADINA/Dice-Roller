package com.diceroller.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiceBag {
    private final List<Die> dice;

    public DiceBag(int numDice, int faces) {
        if (numDice < 1 || faces < 1) {
            throw new IllegalArgumentException("Number of dice and faces must be greater than 0.");
        }
        this.dice = new ArrayList<>(numDice);
        for (int i = 0; i < numDice; i++) {
            dice.add(new Die(faces));
        }
    }

    public List<Integer> rollAllDice() {
        List<Integer> results = new ArrayList<>(dice.size());
        for (Die die : dice) {
            results.add(die.roll());
        }
        return results;
    }

    public int getTotal() {
        int total = 0;
        for (Die die : dice) {
            total += die.getValue();
        }
        return total;
    }

    public Map<Integer, Integer> rollAndTrackFrequency(int numRolls) {
        Map<Integer, Integer> faceFrequency = new LinkedHashMap<>();
        int faces = getFaces();
        for (int i = 1; i <= faces; i++) {
            faceFrequency.put(i, 0);
        }

        // Fix: Roll ALL dice `numRolls` times to track total distribution accurately
        for (int i = 0; i < numRolls; i++) {
            for (Die die : dice) {
                int result = die.roll();
                faceFrequency.put(result, faceFrequency.get(result) + 1);
            }
        }
        return faceFrequency;
    }

    public int getNumberOfDice() {
        return dice.size();
    }

    public int getFaces() {
        if (dice.isEmpty()) return 0;
        return dice.get(0).getFaces();
    }
}
