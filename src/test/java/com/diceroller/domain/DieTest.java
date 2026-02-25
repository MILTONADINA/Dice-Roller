package com.diceroller.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    @Test
    void testValidInitialization() {
        Die die = new Die(6);
        assertEquals(6, die.getFaces());
        assertTrue(die.getValue() >= 1 && die.getValue() <= 6);
    }

    @Test
    void testInvalidInitialization() {
        assertThrows(IllegalArgumentException.class, () -> new Die(0));
        assertThrows(IllegalArgumentException.class, () -> new Die(-5));
    }

    @Test
    void testRollRange() {
        Die die = new Die(20);
        for (int i = 0; i < 1000; i++) {
            int roll = die.roll();
            assertTrue(roll >= 1 && roll <= 20, "Roll should be between 1 and 20");
        }
    }
}
