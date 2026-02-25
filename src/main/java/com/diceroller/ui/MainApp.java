package com.diceroller.ui;

import com.diceroller.repository.DatabaseManager;
import com.diceroller.repository.RollHistoryRepository;
import com.diceroller.service.DiceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame {
    private final JTextField numberOfDiceField;
    private final JTextField numberOfFacesField;
    private final JTextField rollTimesField;
    private final JTextArea resultArea;
    private final DiceService diceService;

    public MainApp() {
        DatabaseManager.initializeDatabase();
        RollHistoryRepository repository = new RollHistoryRepository();
        this.diceService = new DiceService(repository);

        setTitle("Dice Roller Pro");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Number of Dice:"));
        numberOfDiceField = new JTextField("3");
        inputPanel.add(numberOfDiceField);

        inputPanel.add(new JLabel("Faces per Die:"));
        numberOfFacesField = new JTextField("6");
        inputPanel.add(numberOfFacesField);

        inputPanel.add(new JLabel("Number of Rolls (for Frequency):"));
        rollTimesField = new JTextField("1");
        inputPanel.add(rollTimesField);

        JButton rollButton = new JButton("Roll Single Set");
        JButton freqButton = new JButton("Roll Frequency");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(rollButton);
        buttonPanel.add(freqButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        rollButton.addActionListener(e -> executeRoll(false));
        freqButton.addActionListener(e -> executeRoll(true));
    }

    private void executeRoll(boolean isFrequency) {
        try {
            int numDice = Integer.parseInt(numberOfDiceField.getText().trim());
            int numFaces = Integer.parseInt(numberOfFacesField.getText().trim());
            int numRolls = Integer.parseInt(rollTimesField.getText().trim());

            if (numDice <= 0 || numFaces <= 0 || numRolls <= 0) {
                resultArea.setText("Error: All values must be greater than 0.");
                return;
            }

            String result;
            if (isFrequency) {
                result = diceService.rollFrequencyAndSave(numDice, numFaces, numRolls);
            } else {
                result = diceService.rollDiceAndSave(numDice, numFaces);
            }
            resultArea.setText(result + "\n------------------------\n" + resultArea.getText());

        } catch (NumberFormatException ex) {
            resultArea.setText("Error: Please enter valid integer values.\n" + resultArea.getText());
        } catch (IllegalArgumentException ex) {
            resultArea.setText("Error: " + ex.getMessage() + "\n" + resultArea.getText());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}
