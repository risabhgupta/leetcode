package com.comppractice.july.justforfun;

interface Process {
    void execute();
}

public class FunClass {
    public static void main(String[] args) {
        Process[] processes = new Process[20];
        for (int i = 0; i < processes.length; i++) {
            final int processId = i;
            processes[i] = () -> System.out.println("Process " + processId + " is running");
        }
    }

}