/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class TaskQueue {
    TaskNode front;
    TaskNode rear;

    public TaskQueue() {
        front = null;
        rear = null;
    }

    public void enqueue(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (rear == null) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    public void enqueueFront(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (front == null) {
            front = newNode;
            rear = newNode;
            return;
        }

        newNode.next = front;
        front = newNode;
    }

    public Task dequeue() {
        if (front == null) {
            return null;
        }

        Task task = front.task;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return task;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
