/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class SectorNode {
    int sectorId;
    TaskNode head;

    SectorNode left;
    SectorNode right;
    int height;

    public SectorNode(int sectorId) {
        this.sectorId = sectorId;
        this.head = null;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
            return;
        }

        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public boolean removeTask(String taskId) {
        if (head == null) {
            return false;
        }

        if (head.task.taskId.equals(taskId)) {
            head = head.next;
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.task.taskId.equals(taskId)) {
                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void printTasks() {
        TaskNode current = head;

        while (current != null) {
            System.out.println("   " + current.task);
            current = current.next;
        }
    }
}