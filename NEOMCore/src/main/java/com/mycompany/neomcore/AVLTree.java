/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neomcore;

public class AVLTree {
    SectorNode root;
    int comparisons;

    int height(SectorNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalance(SectorNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    SectorNode rightRotate(SectorNode y) {
        SectorNode x = y.left;
        SectorNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    SectorNode leftRotate(SectorNode x) {
        SectorNode y = x.right;
        SectorNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insertTask(Task task) {
        root = insert(root, task);
    }

    private SectorNode insert(SectorNode node, Task task) {
        if (node == null) {
            SectorNode newNode = new SectorNode(task.sectorId);
            newNode.addTask(task);
            return newNode;
        }

        if (task.sectorId < node.sectorId) {
            node.left = insert(node.left, task);
        } else if (task.sectorId > node.sectorId) {
            node.right = insert(node.right, task);
        } else {
            node.addTask(task);
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // LL
        if (balance > 1 && task.sectorId < node.left.sectorId)
            return rightRotate(node);

        // RR
        if (balance < -1 && task.sectorId > node.right.sectorId)
            return leftRotate(node);

        // LR
        if (balance > 1 && task.sectorId > node.left.sectorId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && task.sectorId < node.right.sectorId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public SectorNode search(int sectorId) {
        comparisons = 0;
        return searchRec(root, sectorId);
    }

    private SectorNode searchRec(SectorNode node, int sectorId) {
        comparisons++;

        if (node == null) return null;

        if (sectorId == node.sectorId) return node;

        if (sectorId < node.sectorId)
            return searchRec(node.left, sectorId);

        return searchRec(node.right, sectorId);
    }

    public void removeTask(Task task) {
        SectorNode sector = search(task.sectorId);
        if (sector != null) {
            sector.removeTask(task.taskId);
        }
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(SectorNode node) {
        if (node == null) return;

        inOrderRec(node.left);

        System.out.println("Sector " + node.sectorId + ":");
        node.printTasks();

        inOrderRec(node.right);
    }
}
