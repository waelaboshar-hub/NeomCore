/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.neomcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class NEOMCore {

    static AVLTree avl = new AVLTree();
    static TaskQueue queue = new TaskQueue();
    static ArchiveList archive = new ArchiveList();
    static TaskStack stack = new TaskStack();

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("NEOM Core System Started");
    loadArchiveFromFile();

    while (true) {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Load Tasks From File");
        System.out.println("2. Process Next Task");
        System.out.println("3. Emergency Undo");
        System.out.println("4. System Audit");
        System.out.println("5. View Archive History");
        System.out.println("6. Search Sector");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int command = input.nextInt();

        if (command == 1) {
            System.out.print("Enter filename: ");
            String filename = input.next();
            loadTasksFromFile(filename);
        }

        else if (command == 2) {
            if (queue.isEmpty()) {
                System.out.println("No tasks in queue.");
            } else {
                Task t = queue.dequeue();

                t.status = "Completed";

                archive.append(t);
                stack.push(t);
                saveCompletedTask(t);

                System.out.println("Completed: " + t);
            }
        }

        else if (command == 3) {
            if (stack.isEmpty()) {
                System.out.println("No completed task to undo.");
            } else {
                Task t = stack.pop();

                t.status = "Pending";

                archive.remove(t.taskId);
                removeFromArchiveFile(t.taskId);
                queue.enqueueFront(t);

                System.out.println("Undo completed: " + t);
            }
        }

        else if (command == 4) {
            System.out.println("[Audit] Printing Global Sector Index:");
            avl.inOrder();
        }

        else if (command == 5) {
            System.out.println("[Archive] Completed Tasks History:");
            archive.printHistory();
        }

        else if (command == 6) {
            System.out.print("Enter Sector ID: ");
            int sectorId = input.nextInt();

            SectorNode sector = avl.search(sectorId);

            if (sector != null) {
                System.out.println("[Search] Sector " + sectorId + " found.");
                sector.printTasks();
            } else {
                System.out.println("[Search] Sector " + sectorId + " not found.");
            }

            System.out.println("[Analytics] N (Total Sectors) = " + countSectors(avl.root)
                    + " | Comparisons = " + avl.comparisons);
        }

        else if (command == 0) {
            System.out.println("System stopped.");
            break;
        }

        else {
            System.out.println("Invalid choice.");
        }
    }

    input.close();
}
    public static void loadTasksFromFile(String filename) {
    try {
        File file = new File(filename);
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(",", 3);

            int sectorId = Integer.parseInt(parts[0].trim());
            String taskId = parts[1].trim();
            String description = parts[2].trim();

            Task task = new Task(sectorId, taskId, description);
if (isTaskArchived(taskId)) {
    task.status = "Completed";
}
            avl.insertTask(task);

if (!task.status.equals("Completed")) {
    queue.enqueue(task);
}
        }

        fileScanner.close();
        System.out.println("Tasks loaded successfully.");

    } catch (FileNotFoundException e) {
        System.out.println("File not found.");
    } catch (Exception e) {
        System.out.println("Error reading file.");
    }
}
    public static void saveCompletedTask(Task task) {
    try {
        FileWriter writer = new FileWriter("archive.txt", true);

        writer.write(task.sectorId + ", " + task.taskId + ", " + task.description + "\n");

        writer.close();
    } catch (Exception e) {
        System.out.println("Error saving task.");
    }
}
    public static void loadArchiveFromFile() {
    try {
        File file = new File("archive.txt");

        if (!file.exists()) {
            return;
        }

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) continue;

            String[] parts = line.split(",", 3);

            int sectorId = Integer.parseInt(parts[0].trim());
            String taskId = parts[1].trim();
            String description = parts[2].trim();

            Task task = new Task(sectorId, taskId, description);
            task.status = "Completed";

            archive.append(task);
            stack.push(task);
        }

        fileScanner.close();

    } catch (Exception e) {
        System.out.println("Error loading archive.");
    }
}
    public static boolean isTaskArchived(String taskId) {
    try {
        File file = new File("archive.txt");

        if (!file.exists()) {
            return false;
        }

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) continue;

            String[] parts = line.split(",", 3);

            if (parts[1].trim().equals(taskId)) {
                fileScanner.close();
                return true;
            }
        }

        fileScanner.close();

    } catch (Exception e) {
        return false;
    }

    return false;
}
    public static void removeFromArchiveFile(String taskId) {
    try {
        File inputFile = new File("archive.txt");
        File tempFile = new File("temp.txt");

        if (!inputFile.exists()) return;

        Scanner scanner = new Scanner(inputFile);
        java.io.FileWriter writer = new java.io.FileWriter(tempFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (!line.contains(taskId)) {
                writer.write(line + "\n");
            }
        }

        scanner.close();
        writer.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

    } catch (Exception e) {
        System.out.println("Error updating archive file.");
    }
}
    public static int countSectors(SectorNode node) {
    if (node == null) return 0;
    return 1 + countSectors(node.left) + countSectors(node.right);
}
}
