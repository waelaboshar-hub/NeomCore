/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class Task {
    int sectorId;
    String taskId;
    String description;
    String status;

    public Task(int sectorId, String taskId, String description) {
        this.sectorId = sectorId;
        this.taskId = taskId;
        this.description = description;
        this.status = "Pending";
    }

    @Override
    public String toString() {
        return sectorId + ", " + taskId + ", " + description + " [" + status + "]";
    }
}
