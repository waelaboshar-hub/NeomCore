/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class ArchiveList {
    TaskNode head;
    TaskNode tail;

    public ArchiveList() {
        head = null;
        tail = null;
    }

    public void append(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    public void remove(String taskId) {
        if (head == null) return;

        if (head.task.taskId.equals(taskId)) {
            head = head.next;
            if (head == null) tail = null;
            return;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.task.taskId.equals(taskId)) {
                current.next = current.next.next;

                if (current.next == null) {
                    tail = current;
                }
                return;
            }
            current = current.next;
        }
    }

    public void printHistory() {
        TaskNode current = head;

        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}
