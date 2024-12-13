package PD;

import java.util.ArrayList;  // Import the ArrayList class for storing dice
import java.util.HashMap;    // Import the HashMap class for tracking face frequencies
import java.util.List;      // Import the List interface for storing roll results
import java.util.Map;       // Import the Map interface for mapping face values to their frequencies

public class DiceBag {
    private List<Die> dice;  // List to store the dice in the bag

    // Constructor to initialize the dice bag with a specified number of dice and faces
    public DiceBag(int numDice, int faces) {
        // Check if the number of dice or faces is less than 1, which is invalid
        if (numDice < 1 || faces < 1) {
            // Throw an exception if the number of dice or faces is invalid
            throw new IllegalArgumentException("Number of dice and faces must be greater than 0.");
        }
        // Initialize the list of dice
        dice = new ArrayList<>();
        // Create the specified number of dice and add them to the list
        for (int i = 0; i < numDice; i++) {
            dice.add(new Die(faces));
        }
    }

    // Method to roll all dice and return their results
    public List<Integer> rollAllDice() {
        // Create a list to store the results of each die roll
        List<Integer> results = new ArrayList<>();
        // Roll each die and add the result to the list
        for (Die die : dice) {
            results.add(die.roll());
        }
        // Return the list of roll results
        return results;
    }

    // Method to get the total value of all dice rolls
    public int getTotal() {
        // Initialize the total value to 0
        int total = 0;
        // Add the value of each die to the total
        for (Die die : dice) {
            total += die.getValue();
        }
        // Return the total value
        return total;
    }

    // Method to track the frequency of each face after multiple rolls of a single die
    public Map<Integer, Integer> rollAndTrackFrequency(int numRolls) {
        // Create a map to track the frequency of each face value
        Map<Integer, Integer> faceFrequency = new HashMap<>();

        // Initialize the frequency map with each face value set to 0
        for (int i = 1; i <= getFaces(); i++) {
            faceFrequency.put(i, 0);
        }
        // Roll a single die multiple times and track face frequencies
        for (int i = 0; i < numRolls; i++) {
            // Roll the first die in the bag and get the result
            int result = dice.get(0).roll();
            // Update the frequency of the rolled face value in the map
            faceFrequency.put(result, faceFrequency.get(result) + 1);
        }
        // Return the map of face frequencies
        return faceFrequency;
    }
    

    // Method to get the number of dice in the bag
    public int getNumberOfDice() {
        return dice.size();  // Return the size of the list, which is the number of dice
    }

    // Method to get the number of faces on the dice
    public int getFaces() {
        // Check if the list of dice is empty
        if (dice.isEmpty()) {
            // Return 0 if there are no dice in the bag
            return 0;
        }
        // Return the number of faces on the first die (assuming all dice have the same number of faces)
        return dice.get(0).getFaces();
    }
}
