# NEOMCore - Data Structures

NEOMCore is a Java command-line task management system that simulates the coordination of operational tasks across multiple NEOM sectors.

The project demonstrates the practical implementation of fundamental data structures including **AVL Trees, Queues, Stacks, and Linked Lists** while supporting task processing, emergency undo, sector searching, system auditing, and persistent task history.

## Features

- Load operational tasks from text files
- Organize sectors using a self-balancing **AVL Tree**
- Store multiple tasks within each sector using a **Linked List**
- Process pending tasks in **FIFO** order using a Queue
- Undo the most recently completed task using a **Stack**
- Maintain an archive of completed tasks
- Save completed tasks to `archive.txt`
- Restore archived tasks when the application restarts
- Search sectors using their Sector ID
- Display AVL Tree search comparisons
- Display the total number of sectors
- Perform a full system audit with sectors displayed in sorted order

## Data Structures

| Data Structure | Purpose |
|---|---|
| AVL Tree | Stores and indexes sectors efficiently using `sectorId` |
| Queue | Manages pending tasks using FIFO processing |
| Stack | Supports emergency undo of the latest completed task |
| Linked List | Stores tasks inside sectors and maintains archive history |

## Project Structure

```text
NEOMCore/
├── pom.xml
├── archive.txt
├── tasks_morning.txt
└── src/
    └── main/
        └── java/
            └── com/
                └── mycompany/
                    └── neomcore/
                        ├── NEOMCore.java
                        ├── Task.java
                        ├── TaskNode.java
                        ├── TaskQueue.java
                        ├── TaskStack.java
                        ├── ArchiveList.java
                        ├── SectorNode.java
                        └── AVLTree.java
```

## How It Works

Each task contains four main attributes:

- `sectorId` — identifies the sector responsible for the task
- `taskId` — identifies the individual task
- `description` — describes the operation
- `status` — indicates whether the task is `Pending` or `Completed`

When a task file is loaded, every task is inserted into the AVL Tree according to its sector.

Tasks that have not already been completed are also added to the processing queue.

When a task is processed:

1. The task is removed from the queue.
2. Its status changes to `Completed`.
3. It is added to the archive.
4. It is pushed onto the undo stack.
5. It is stored permanently in `archive.txt`.

The **Emergency Undo** feature restores the most recently completed task by:

1. Removing it from the stack.
2. Changing its status back to `Pending`.
3. Removing it from the archive.
4. Removing it from `archive.txt`.
5. Returning it to the front of the processing queue.

## Input File Format

Task files follow this format:

```text
sectorId, taskId, description
```

Example:

```text
101, T_01, Install Solar Panels
102, T_02, Weld Support Beams
101, T_03, Paint North Wall
103, T_04, Lay Fiber Cable
105, T_05, Calibrate Sensor
```

A sample file called `tasks_morning.txt` is included with the project.

## Main Menu

When the application starts, the following menu is displayed:

```text
========== MAIN MENU ==========
1. Load Tasks From File
2. Process Next Task
3. Emergency Undo
4. System Audit
5. View Archive History
6. Search Sector
0. Exit
```

### 1. Load Tasks From File

Loads tasks from a comma-separated text file and inserts them into the system.

### 2. Process Next Task

Removes the next pending task from the queue and marks it as completed.

### 3. Emergency Undo

Restores the most recently completed task back to the pending queue.

### 4. System Audit

Traverses the AVL Tree using **in-order traversal** and displays all sectors and their tasks in sorted Sector ID order.

### 5. View Archive History

Displays all completed tasks stored in the archive.

### 6. Search Sector

Searches the AVL Tree for a specific Sector ID and displays:

- Tasks associated with the sector
- Total number of sectors
- Number of comparisons performed during the search

## Requirements

- Java 24
- Apache Maven

The project is configured through Maven using Java release 24.

## Running the Project

Clone the repository:

```bash
git clone <your-repository-url>
cd NEOMCore
```

Compile the project:

```bash
mvn compile
```

Run the application:

```bash
java -cp target/classes com.mycompany.neomcore.NEOMCore
```

The project can also be opened and executed using Java IDEs such as:

- IntelliJ IDEA
- Apache NetBeans
- Eclipse

Run the `NEOMCore.java` class to start the application.

## Example Workflow

1. Start NEOMCore.
2. Select **Load Tasks From File**.
3. Enter:

```text
tasks_morning.txt
```

4. Process tasks using **Process Next Task**.
5. Search for tasks belonging to a particular sector.
6. Use **Emergency Undo** if the latest task needs to be restored.
7. Run **System Audit** to inspect all sectors.
8. Use **View Archive History** to inspect completed operations.

## AVL Tree Implementation

NEOMCore uses an AVL Tree to maintain balanced sector indexing.

The implementation supports the four standard AVL balancing cases:

- Left-Left (LL)
- Right-Right (RR)
- Left-Right (LR)
- Right-Left (RL)

Rotations are automatically performed during insertion to maintain the balance of the tree.

This helps maintain efficient sector lookup as the number of sectors increases.

## Concepts Demonstrated

NEOMCore demonstrates several important computer science and software engineering concepts:

- Object-Oriented Programming
- Data Structures and Algorithms
- AVL Trees
- Binary Search Trees
- Tree rotations
- Tree traversal
- Queues
- Stacks
- Singly Linked Lists
- File Input/Output
- Persistent storage
- Search algorithms
- Modular class design
- Task state management
- Basic algorithm performance analysis

## Future Improvements

Possible improvements include:

- Task priority levels
- Task deadlines
- Task editing and deletion
- Improved input validation
- Automated unit testing
- Database integration
- User authentication
- Graphical user interface
- Web-based dashboard
- Sector statistics
- Task completion analytics
- Audit report exporting
- REST API support

## License

This project is currently intended for educational and portfolio purposes.

A license such as MIT can be added if the project will be publicly distributed or reused.
