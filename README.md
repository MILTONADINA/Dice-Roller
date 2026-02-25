# Dice Roller Pro (Full Stack Java Desktop)

A modern, production-ready dice rolling simulator developed in Java.
This project has been heavily refined from a basic OOP script into a full-featured Desktop Application utilizing an embedded SQLite database for roll history, adhering precisely to modern Java MVC and Clean Architecture conventions.

---

## 🚀 Key Features

- **Embedded History Database:** All rolls are persisted locally to `dice_history.db` using **SQLite** via JDBC.
- **Robust Domain Logic:** Encapsulated `Die` and `DiceBag` classes representing accurate stochastic probability without cross-contamination.
- **Industry Standard Architecture:**
  - **MVC Pattern:** Separation of concerns between `ui` (Views), `service` (Business Logic), and `repository` (Data Access).
  - **Maven Build System:** Standardized dependency management and build pipeline.
- **Testing:** Comprehensive JUnit 5 test suite validating the domain logic and edge cases.
- **Logging:** SLF4J integrated for production-ready audit trails vs standard `System.out.println`.

---

## 🧱 Tech Stack

- **Language:** Java 17+
- **Build Tool:** Maven 3.x
- **Database:** SQLite (Embedded, zero-config)
- **UI Framework:** Java Swing (System Look-and-Feel)
- **Testing:** JUnit 5 (Jupiter)
- **Logging:** SLF4J

---

## 🗂️ Project Structure

```text
dice-roller/
├── pom.xml
└── src/
    ├── main/java/com/diceroller/
    │   ├── domain/       -> Core logic (Die, DiceBag, RollHistory)
    │   ├── repository/   -> Database models and SQLite JDBC integration
    │   ├── service/      -> Dice roll orchestration and persistence logic
    │   └── ui/           -> MainApp Swing GUI
    └── test/java/com/diceroller/
        └── domain/       -> Unit tests
```

---

## 🚀 How to Run

### Option 1: Using Maven (Recommended)

1. Open a terminal in the project root directory.
2. Compile and run the tests:
   ```bash
   mvn clean test
   ```
3. Run the application! To execute via Maven compiler plugin natively:
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.diceroller.ui.MainApp"
   ```

### Option 2: IDE (IntelliJ IDEA / Eclipse / VSCode)

1. Open/Import the project folder as a **Maven project**.
2. Wait for dependencies to index.
3. Run `MainApp.java` located in `src/main/java/com/diceroller/ui/`.
