package ru.shcheglov.homework1.task4.message;

import ru.shcheglov.homework1.task4.command.Command;

public class Printer {

    public static void printWelcome() {
        System.out.println("Welcome to TASK MANAGER!");
        System.out.println("Type HELP for assistance.");
        printSeparator();
    }

    public static void printRequest(String request) {
        System.out.print("{Enter} " + request + ": ");
    }

    public static void printSeparator() {
        System.out.println("-----------------------------");
    }

    public static void printError(String message) {
        System.out.println("{Error} " + message);
        printSeparator();
    }

    public static void printSuccess(String message) {
        System.out.println("{Success} " + message);
        printSeparator();
    }

    public static void printByeBye() {
        System.out.println("Bye-bye...See you again!");
    }

    public static void printHelp() {
        for (Command command : Command.values()) {
            System.out.println(" - " + command.getCommand() + ": " + command.getDescription());
        }
        printSeparator();
    }

}
