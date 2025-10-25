
# ⚡ task-cli

A simple, fast, and persistent command-line task manager built in Java. Manage your to-do list directly from your terminal with a clear, intuitive syntax.

---

## ✨ Features

* **Persistence:** Tasks are saved to a local `tasks.json` file, so your list remains intact between sessions.
* **Simple Syntax:** Intuitive command structure for quick task management.
* **Status Tracking:** Mark tasks as `todo`, `in-progress`, or `done`.
* **Filtering:** Easily list tasks based on their current status.

---

## 🚀 Installation & Setup

This guide assumes you have **Java (JRE/JDK 17 or newer)** installed on your system.

### 1. Download the Executable

1.  Download the latest **`task-cli.jar`** (the standalone executable JAR) from the releases page (or build it yourself using `mvn package`).
2.  Create a dedicated folder for your CLI tools (e.g., `~/CLI_Tools/task-cli`).
3.  Place `task-cli.jar` in that folder.

### 2. Set up the Wrapper Script

To avoid typing `java -jar ...` every time, we use a simple wrapper script.

| Platform | Script Name | Content |
| :--- | :--- | :--- |
| **Linux/macOS** | `task-cli` | Place this file next to `task-cli.jar`. |
| **Windows** | `task-cli.bat` | Place this file next to `task-cli.jar`. |

**`task-cli` (Linux/macOS):**

```bash
#!/bin/bash
# --- IMPORTANT: Update the path below to your actual task-cli.jar location ---
JAR_PATH="$(dirname "$0")/task-cli.jar" 

java -jar "$JAR_PATH" "$@"
````

**Don't forget to make it executable:** `chmod +x task-cli`

**`task-cli.bat` (Windows):**

```batch
@echo off
REM --- IMPORTANT: Update the path below to your actual task-cli.jar location ---
set JAR_PATH="%~dp0task-cli.jar"

java -jar %JAR_PATH% %*
```

### 3\. Add to PATH

Add the directory containing your wrapper script (`~/CLI_Tools/task-cli`) to your system's **PATH** environment variable. This allows you to run the command from any terminal window.

-----

## 💡 Usage

The primary executable is `task-cli`.

### Adding a New Task

```bash
task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)
```

### Listing Tasks

List all tasks or filter by status.

| Command | Description |
| :--- | :--- |
| `task-cli list` | Lists all tasks with their ID and status. |
| `task-cli list done` | Lists only completed tasks. |
| `task-cli list todo` | Lists tasks that are pending. |
| `task-cli list in-progress` | Lists tasks currently being worked on. |

### Updating and Deleting

Use the Task ID shown by the `list` command.

```bash
# Update the description of task with ID 1
task-cli update 1 "Buy groceries and cook dinner"

# Delete task with ID 1
task-cli delete 1
```

### Marking Status

Change a task's status easily.

```bash
# Mark task 1 as actively being worked on
task-cli mark-in-progress 1

# Mark task 1 as completed
task-cli mark-done 1
```

-----

## 🛠️ Development & Building

This project is built with **Java** and uses **picocli** for argument parsing and **Gson** for JSON persistence.

### Prerequisites

  * Java Development Kit (JDK) 17+
  * Apache Maven

### Building from Source

1.  Clone the repository:
    ```bash
    git clone [https://github.com/your-username/task-cli.git](https://github.com/your-username/task-cli.git)
    cd task-cli
    ```
2.  Use Maven to build the executable "Uber" JAR:
    ```bash
    mvn clean package
    ```
3.  The final runnable JAR will be in the `target/` directory (e.g., `target/task-cli-1.0-SNAPSHOT.jar`).

-----

## 🤝 Contributing

Contributions are welcome\! Feel free to open issues for bugs or feature requests, or submit pull requests with improvements.

-----

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](https://www.google.com/search?q=LICENSE) file for details.


