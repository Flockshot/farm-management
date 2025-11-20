# Farm Management Application (FarmApp)

Designed and implemented a comprehensive farm management system (“FarmApp”) in Java, progressing through the entire software development lifecycle — from core OOP design and data structures to GUI development, database integration, and data security.

![Java](https://img.shields.io/badge/Java-11+-ED8B00.svg?logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java_Swing-GUI-596D78.svg)
![JDBC](https://img.shields.io/badge/Database-JDBC_&_SQL-00758F.svg)

---

## 🚀 Project Evolution: From CLI to Secure GUI

This project was built iteratively in four distinct phases, transforming a simple console program into a robust, database-driven desktop application.

### Phase 1: Core OOP & Console Application
* **Goal:** Build the foundational domain classes from a UML diagram.
* **Features:**
    * Created core classes (`Cow`, `Veterinary`) using proper encapsulation.
    * Used `ArrayList`s for data management.
    * Implemented a full command-line interface (CLI) for user interaction.
    * Packaged the final application as an executable **`FarmApp.jar`** file.

### Phase 2: Advanced OOP (Inheritance & Polymorphism)
* **Goal:** Refactor the architecture for scalability using advanced OOP principles.
* **Features:**
    * **Inheritance:** Re-architected the data model with abstract superclasses:
        * `Animal` -> `Cow`, `Sheep`
        * `Employee` -> `Veterinary`, `FarmWorker`
    * **Polymorphism:** Implemented a `Payment` interface to allow polymorphic calculation of salaries for different employee types.
    * **Data Structures:** Upgraded from `ArrayList` to `HashMap` for efficient data retrieval (e.g., milking records).
    * **Robustness:** Added exception handling for invalid data.

### Phase 3: GUI Development & File Persistence
* **Goal:** Transition the application from a CLI to a user-friendly graphical interface.
* **Features:**
    * **Java Swing GUI:** Replaced the console menu with a full Java Swing interface (`JFrame`, `JPanel`, `JButton`).
    * **Data Visualization:** Used `JTable` to display live data for animals and employees.
    * **Event-Driven:** Implemented `ActionListeners` to handle user interactions.
    * **Persistence:** Added data persistence using **File I/O (Object Serialization)** to save and restore the application's state between sessions.

### Phase 4: Database Integration & Data Integrity
* **Goal:** Replace file I/O with a professional database backend and add a security verification layer.
* **Features:**
    * **Database Persistence:** Integrated a **SQL database via JDBC** (`DataStorage.java`) to manage all animal and employee data. The schema is defined in `data.sql`.
    * **Data Integrity Check:** Implemented a security system to prevent data tampering:
        1.  All `Animal` data is serialized to a binary file.
        2.  An **MD5 hash** is generated from this file and stored.
        3.  On application startup, a **new thread** is launched (`MD5Handler.java`) to validate that the serialized file's *current* hash matches the *stored* hash.
        4.  This ensures the integrity of the data files before they are loaded.

---

## 🏛️ Final Architecture & Class Diagram

The final system design, based on the project specification, includes modules for animals, employees, and treatments, all managed by the main `FarmApp` and `FarmGUI` classes.

![Image: UML Class Diagram](.media/UML.png)

---

## ✨ Key Features of the Final Application

* **Full GUI:** A multi-tabbed Java Swing interface for managing all farm assets.
* **Database-Backed:** All data is persistently stored in a SQL database via JDBC.
* **Polymorphic Design:** Easily add new animal or employee types without breaking existing code, thanks to the abstract `Animal` and `Employee` superclasses.
* **Secure Data Integrity:** A multithreaded **MD5 hash validator** ensures that serialized data files have not been tampered with.
* **CRUD Operations:** The GUI provides full Create, Read, Update, and Delete (CRUD) functionality for all data models.

![Image: Screenshot of the FarmApp Main GUI Window](.media/main_GUI)
![Image: Screenshot of the FarmApp Sheep GUI Window](.media/sheep_GUI)
![Image: Screenshot of the FarmApp Cow GUI Window](.media/cow_GUI)
![Image: Screenshot of the FarmApp Farm Worker GUI Window](.media/farm_worker_GUI)
![Image: Screenshot of the FarmApp Veterinary GUI Window](.media/vet_GUI)
![Image: Screenshot of the FarmApp Treatments GUI Window](.media/treatments_GUI)

---

## 🚀 How to Run

### Requirements
* Java JDK
* A SQL database server
* The `mysql-connector-java.jar` (or other JDBC driver) in the classpath.

### Setup
1.  **Set up the Database:** Run the `data.sql` script on your SQL server to create the necessary tables.
2.  **Configure Connection:** Update the database connection string, username, and password in the `DataStorage.java` file.
3.  **Compile & Run:**
    * Compile all `.java` files:
        ```bash
        javac me/somaan/farmapp/FarmApp.java
        ```
    * Run the application:
        ```bash
        java me.somaan.farmapp.FarmApp
        ```
4.  **Executable JAR:**
    * Alternatively, you can run the pre-packaged `FarmApp.jar` (if built with the JDBC driver included):
        ```bash
        java -jar FarmApp.jar
        ```