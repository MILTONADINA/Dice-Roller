# Phase 1: System Map & Baseline Health Report

## A) System Map / Repo Inventory

**Overview:**
The `Dice-Roller` project is a command-line and Swing GUI-based dice rolling simulator written in Java SE. It uses a custom-built Model-View-Controller (MVC) style structure. No external build tool (e.g., Maven, Gradle) is present.

**Key Directories:**

- `DICE_ROLLER/` - Main project folder.
  - `src/` - Contains all Java source code.
    - `PD/` (Problem Domain) - Core domain logic (`Die.java`, `DiceBag.java`).
    - `UI/` (User Interface) - Main entry point (`MAIN.java`), utilizing Java Swing.
    - `TEST/` - Basic custom test driver classes (`DieTest.java`, `DIceBagTest.java`).
  - `bin/` - Expected compiled `.class` files output directory (currently empty/unused in README instructions).
  - `doc/` - Javadoc or documentation output.

**Stack & Tools:**

- **Language:** Java SE (version not explicitly enforced, likely 8+ given Swing usage).
- **Build Tool:** None (manual `javac` compilation).
- **Testing:** Custom test methods (no JUnit or Next-Gen testing framework used).
- **External Integrations:** None. Uses standard Java utils (`java.util.Random`, `javax.swing`).

## B) Flow Mapping

1. **Domain Logic (PD):**
   - `Die`: Encapsulates an individual die (number of faces, random value generator using `java.util.Random`).
   - `DiceBag`: Represents a collection of `Die` objects. Provides aggregation methods (`rollAllDice`, `getTotal`) and frequency tracking for single die multi-rolls (`rollAndTrackFrequency`).
2. **User Flow (UI -> PD):**
   - User launches `UI.MAIN`, bringing up a JFrame with input fields: `Number of Dice`, `Faces per Die`, `Number of Rolls`.
   - On clicking "Roll Dice", the UI parses integers, instantiates a `DiceBag`, and calls `rollAllDice()` and `rollAndTrackFrequency()`.
   - Results are written to a non-editable `JTextArea`.
3. **Exception Handling:**
   - UI catches `NumberFormatException` (invalid inputs) and `IllegalArgumentException` (e.g., < 1 faces or dice). It displays the error directly in the results area.

## C) Baseline Health Report ("Trust but verify")

**Execution Commands Attempted:**

```bash
cd DICE_ROLLER/src
javac PD/*.java UI/*.java TEST/*.java
java TEST.DieTest
```

**Results:**

- **Build/Run Failure:** Execution failed because `javac` and `java` are not available in the system `PATH` (or not installed). Therefore, the project cannot be built or tested from the command line in the current environment context.
- **Code Health Observations (Pre-Lint):**
  - **Testing Strategy:** Tests rely on manual `System.out.println` and basic `assert` keywords combined with a lack of a test runner. `DIceBagTest.java` does not even have a `main` method to run its static test blocks.
  - **Structure:** Package names (`PD`, `UI`, `TEST`) are non-standard. Java conventions dictate lowercase package names (e.g., `pd`, `ui`, `test`). Wait, `main` class is named `MAIN.java` (capitalized, violates conventions).

**Conclusion:**
Phase 1 is complete. The system is internalized. Proceed to Phase 2 (Audit) and Phase 3 (Refinement Plan).
