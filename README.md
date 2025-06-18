# Java Dice Roller Simulator

A modular, object-oriented dice rolling simulator developed in Java. This project replicates the randomness and flexibility of physical dice rolls, making it suitable for use in tabletop games, simulations, or probability exercises.

---

## 🧠 Key Skills Demonstrated

- **Object-Oriented Programming (OOP):** Clean modeling of `Die`, `DiceBag`, and `Roll` logic
- **Testing & Validation:** Unit tests for key components using custom test classes
- **Modular Architecture:** Separation into packages (`PD`, `UI`, `TEST`) for clear concerns
- **Randomization Logic:** Secure and testable simulation of probability-based outputs
- **Scalability:** Easily extendable to include more dice types, modifiers, or game rules

---

## 🧱 Tech Stack

- **Language:** Java SE
- **Structure:** MVC-style modular package layout
- **Execution:** CLI (command line) or IDE-based
- **Testing:** Custom test drivers (manual)

---

## 🗂️ Project Structure
DICE_ROLLER/
├── src/
│ ├── PD/ → Core logic (Die, DiceBag)
│ ├── UI/ → Main entry point (MAIN.java)
│ └── TEST/ → Simple test classes to verify behavior



---

## 🚀 How to Run

### Option 1: IDE (Recommended)
1. Open the `DICE_ROLLER/src/` directory in IntelliJ or Eclipse
2. Run `MAIN.java` from the `UI` package

### Option 2: Terminal

```bash
cd DICE_ROLLER/src
javac PD/*.java UI/MAIN.java
java UI.MAIN

