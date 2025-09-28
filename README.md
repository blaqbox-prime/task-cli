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
