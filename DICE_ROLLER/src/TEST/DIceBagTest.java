package TEST;  // Package declaration, specifying that this class belongs to the 'TEST' package

// Import the DiceBag class from the PD package, as we will test its functionality
import PD.DiceBag;
import java.util.List;  // Import the List class to handle collections of roll results

public class DIceBagTest {

   

    // Test to verify that the DiceBag object is initialized correctly
    public static void testDiceBagInitialization() {
        // Create a DiceBag with 5 dice, each with 6 faces
        DiceBag diceBag = new DiceBag(5, 6);
        // Assert that the DiceBag contains 5 dice
        assert diceBag.getNumberOfDice() == 5 : "The bag should have 5 dice.";
        // Assert that each die has 6 faces
        assert diceBag.getFaces() == 6 : "Each die should have 6 faces.";
        // Print message indicating that this test passed
        System.out.println("testDiceBagInitialization passed.");
    }

    // Test to verify that all dice in the bag are rolled and the results are within the expected range
    public static void testRollAllDice() {
        // Create a DiceBag with 3 dice, each with 6 faces
        DiceBag diceBag = new DiceBag(3, 6);
        // Roll all dice and get the results as a list
        List<Integer> results = diceBag.rollAllDice();
        // Assert that there are 3 results, as we rolled 3 dice
        assert results.size() == 3 : "There should be 3 roll results.";
        // Assert that each result is between 1 and 6, which are valid face values for each die
        for (int result : results) {
            assert result >= 1 && result <= 6 : "Each roll value should be between 1 and 6.";
        }
        // Print message indicating that this test passed
        System.out.println("testRollAllDice passed.");
    }

    // Test to verify that the total value of all rolled dice is within the expected range
    public static void testGetTotal() {
        // Create a DiceBag with 3 dice, each with 6 faces
        DiceBag diceBag = new DiceBag(3, 6);
        diceBag.rollAllDice();  // Roll the dice in the bag
        int total = diceBag.getTotal();  // Get the total value of the dice rolls
        // Assert that the total value is between 3 and 18 (minimum and maximum possible values for 3 dice with 6 faces)
        assert total >= 3 && total <= 18 : "The total value should be between 3 and 18 for 3 dice with 6 faces.";
        // Print message indicating that this test passed
        System.out.println("testGetTotal passed.");
    }

    // Test to verify that creating a DiceBag with an invalid number of dice throws an exception
    public static void testInvalidDiceBag() {
        try {
            // Try to create a DiceBag with 0 dice, which is invalid
            new DiceBag(0, 6);
            // If no exception is thrown, the assertion will fail (as it should throw an exception)
            assert false : "Expected an IllegalArgumentException for invalid number of dice.";
        } catch (IllegalArgumentException e) {
            // Check that the exception message matches what we expect
            assert e.getMessage().equals("Number of dice and faces must be greater than 0.") : "Expected exception message not received.";
        }
        // Print message indicating that this test passed
        System.out.println("testInvalidDiceBag passed.");
    }
}
