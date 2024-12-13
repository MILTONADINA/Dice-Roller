package UI;

import PD.DiceBag;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class MAIN extends JFrame {

    // Fields for user input and output (text fields and display areas)
    private JTextField numberOfDiceField;
    private JTextField numberOfFacesField;
    private JTextField rollTimesField;
    private JTextArea resultArea;

    // Constructor to set up the UI
    public MAIN() {
        setTitle("Dice Roller");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel numberOfDiceLabel = new JLabel("Number of Dice:");
        numberOfDiceField = new JTextField();
        JLabel numberOfFacesLabel = new JLabel("Number of Faces per Die:");
        numberOfFacesField = new JTextField();
        JLabel rollTimesLabel = new JLabel("Number of Rolls:");
        rollTimesField = new JTextField();

        JButton rollButton = new JButton("Roll Dice");

        // Adding components to panel
        inputPanel.add(numberOfDiceLabel);
        inputPanel.add(numberOfDiceField);
        inputPanel.add(numberOfFacesLabel);
        inputPanel.add(numberOfFacesField);
        inputPanel.add(rollTimesLabel);
        inputPanel.add(rollTimesField);
        inputPanel.add(rollButton);

        // Text area for results
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Adding the panel and result area to the window
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action listener for the roll button
        rollButton.addActionListener(new RollDiceActionListener());
    }

    // Inner class for handling dice rolling actions
    private class RollDiceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int numDice = Integer.parseInt(numberOfDiceField.getText());
                int numFaces = Integer.parseInt(numberOfFacesField.getText());
                int numRolls = Integer.parseInt(rollTimesField.getText());

                if (numDice <= 0 || numFaces <= 0 || numRolls <= 0) {
                    resultArea.setText("Error: All values must be greater than 0.");
                    return;
                }

                DiceBag diceBag = new DiceBag(numDice, numFaces);

                // Single roll results
                List<Integer> results = diceBag.rollAllDice();
                int total = diceBag.getTotal();

                StringBuilder output = new StringBuilder("Single Roll Results:\n");
                for (int i = 0; i < results.size(); i++) {
                    output.append("Die ").append(i + 1).append(": ").append(results.get(i)).append("\n");
                }
                output.append("Total: ").append(total).append("\n\n");

                // Multiple rolls and frequency results
                Map<Integer, Integer> faceFrequency = diceBag.rollAndTrackFrequency(numRolls);
                output.append("Frequency Results (").append(numRolls).append(" rolls):\n");
                for (int face = 1; face <= numFaces; face++) {
                    output.append("Face ").append(face).append(": ").append(faceFrequency.getOrDefault(face, 0)).append(" times\n");
                }

                resultArea.setText(output.toString());
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Please enter valid integers.");
            } catch (IllegalArgumentException ex) {
                resultArea.setText(ex.getMessage());
            }
        }
    }

    // Main method to launch the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MAIN mainApp = new MAIN();
            mainApp.setVisible(true);
        });
    }
}
