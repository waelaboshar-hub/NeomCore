/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class TaskStack {
    TaskNode top;

    public TaskStack() {
        top = null;
    }

    public void push(Task task) {
        TaskNode newNode = new TaskNode(task);
        newNode.next = top;
        top = newNode;
    }

    public Task pop() {
        if (top == null) {
            return null;
        }

        Task task = top.task;
        top = top.next;
        return task;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
