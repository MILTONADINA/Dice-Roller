// Package declaration, specifying that this class belongs to the 'PD' package
package PD;

import java.util.Random;  // Import the Random class to generate random numbers

public class Die {
    private int faces;  // Variable to store the number of faces on the die
    private int value;  // Variable to store the current value of the die after rolling
    private Random random;  // Random object to generate random numbers

    // Constructor to initialize the die with a specific number of faces
    public Die(int faces) {
        // Check if the number of faces is less than 1, which is invalid
        if (faces < 1) {
            // Throw an exception if the number of faces is invalid
            throw new IllegalArgumentException("Number of faces must be greater than 0.");
        }
        // Set the number of faces on the die
        this.faces = faces;
        // Initialize the Random object to be used for generating random numbers
        this.random = new Random();
        // Roll the die once when it is created to set an initial value
        this.value = roll(); 
    }

    // Method to roll the die and return the new value
    public int roll() {
        // Generate a random number between 1 and the number of faces on the die
        this.value = random.nextInt(faces) + 1;
        // Return the new value of the die after rolling
        return value;
    }

    // Getter method to retrieve the current value of the die
    public int getValue() {
        return value;
    }

    // Getter method to retrieve the number of faces on the die
    public int getFaces() {
        return faces;
    }
}
