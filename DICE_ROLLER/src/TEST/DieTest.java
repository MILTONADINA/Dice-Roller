package TEST;

import PD.DiceBag;
import PD.Die;
import java.util.Map;

public class DieTest {

    public static void main(String[] args) {
        testFrequencyTracking();
        System.out.println("All tests passed!");
    }

    // Test the frequency tracking method in DiceBag
    private static void testFrequencyTracking() {
        DiceBag diceBag = new DiceBag(3, 6); // 3 dice, 6 faces
        Map<Integer, Integer> frequencyMap = diceBag.rollAndTrackFrequency(1000); // Roll 1000 times

        int totalRolls = 0;
        for (int frequency : frequencyMap.values()) {
            totalRolls += frequency;
        }

        assert totalRolls == 1000 : "Total rolls should equal the number of roll iterations (1000).";
        for (int face = 1; face <= 6; face++) {
            assert frequencyMap.get(face) != null : "All faces from 1 to 6 should have a frequency.";
            assert frequencyMap.get(face) > 0 : "Each face should have been rolled at least once.";
        }

        System.out.println("testFrequencyTracking passed.");
    }
}
