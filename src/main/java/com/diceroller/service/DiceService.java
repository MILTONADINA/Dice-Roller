package com.diceroller.service;

import com.diceroller.domain.DiceBag;
import com.diceroller.repository.RollHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DiceService {
    private static final Logger logger = LoggerFactory.getLogger(DiceService.class);
    private final RollHistoryRepository repository;

    public DiceService(RollHistoryRepository repository) {
        this.repository = repository;
    }

    public String rollDiceAndSave(int numDice, int faces) {
        logger.info("Rolling {}d{}", numDice, faces);
        DiceBag diceBag = new DiceBag(numDice, faces);

        List<Integer> results = diceBag.rollAllDice();
        int total = diceBag.getTotal();

        StringBuilder output = new StringBuilder("Single Roll Results:\n");
        for (int i = 0; i < results.size(); i++) {
            output.append(String.format("Die %d: %d\n", i + 1, results.get(i)));
        }
        output.append("Total: ").append(total).append("\n");

        String breakdown = results.toString();
        repository.saveRoll(numDice, faces, total, breakdown);

        return output.toString();
    }

    public String rollFrequencyAndSave(int numDice, int faces, int numRolls) {
        logger.info("Rolling {}d{} {} times for frequency.", numDice, faces, numRolls);
        DiceBag diceBag = new DiceBag(numDice, faces);

        Map<Integer, Integer> faceFrequency = diceBag.rollAndTrackFrequency(numRolls);

        StringBuilder output = new StringBuilder();
        output.append(String.format("Frequency Results (%d rolls of %dd%d):\n", numRolls, numDice, faces));
        for (int face = 1; face <= faces; face++) {
            output.append(String.format("Face %d: %d times\n", face, faceFrequency.getOrDefault(face, 0)));
        }

        repository.saveRoll(numDice * numRolls, faces, 0, "FREQ_DIST: " + faceFrequency);

        return output.toString();
    }
}
