package com.diceroller.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DiceBagTest {

    @Test
    void testDiceBagInitialization() {
        DiceBag diceBag = new DiceBag(5, 6);
        assertEquals(5, diceBag.getNumberOfDice());
        assertEquals(6, diceBag.getFaces());
    }

    @Test
    void testRollAllDice() {
        DiceBag diceBag = new DiceBag(3, 6);
        List<Integer> results = diceBag.rollAllDice();
        assertEquals(3, results.size());
        for (int result : results) {
            assertTrue(result >= 1 && result <= 6);
        }
    }

    @Test
    void testGetTotal() {
        DiceBag diceBag = new DiceBag(3, 6);
        diceBag.rollAllDice();
        int total = diceBag.getTotal();
        assertTrue(total >= 3 && total <= 18);
    }

    @Test
    void testRollAndTrackFrequency() {
        DiceBag diceBag = new DiceBag(2, 6); // 2 dice, 6 faces
        // Roll 100 times. Means 2 dice * 100 = 200 total rolls evaluated
        Map<Integer, Integer> frequencyMap = diceBag.rollAndTrackFrequency(100);

        int totalFrequency = frequencyMap.values().stream().mapToInt(Integer::intValue).sum();
        assertEquals(200, totalFrequency, "Total rolls across all dice should be 200");
    }

    @Test
    void testInvalidDiceBag() {
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> new DiceBag(0, 6));
        assertEquals("Number of dice and faces must be greater than 0.", e1.getMessage());

        Exception e2 = assertThrows(IllegalArgumentException.class, () -> new DiceBag(3, 0));
        assertEquals("Number of dice and faces must be greater than 0.", e2.getMessage());
    }
}
