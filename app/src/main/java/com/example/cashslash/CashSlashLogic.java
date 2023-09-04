
//-----------this is the bacic loic behind the main functionality of spliting expence amoung the groups-------------

package com.example.cashslash;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CashSlashLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of friends: ");
        int numFriends = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] friendNames = new String[numFriends];
        double[] expenses = new double[numFriends];

        for (int i = 0; i < numFriends; i++) {
            System.out.print("Enter the name of friend " + (i + 1) + ": ");
            friendNames[i] = scanner.nextLine();

            System.out.print("Enter the expense of " + friendNames[i] + ": ");
            expenses[i] = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
        }

        // Calculate the total expenses
        double totalExpenses = 0;
        for (double expense : expenses) {
            totalExpenses += expense;
        }

        // Calculate the average expense
        double averageExpense = totalExpenses / numFriends;

        // Calculate how much each friend owes or is owed
        double[] owes = new double[numFriends];
        for (int i = 0; i < numFriends; i++) {
            owes[i] = expenses[i] - averageExpense;
        }

        // Create a list to store payment details
        List<String> paymentDetails = new ArrayList<>();

        // Generate payment details
        for (int i = 0; i < numFriends; i++) {
            for (int j = 0; j < numFriends; j++) {
                if (i != j && owes[i] < 0 && owes[j] > 0) {
                    double amount = Math.min(Math.abs(owes[i]), owes[j]);
                    paymentDetails.add(friendNames[i] + " should pay Rs- " + amount + " to " + friendNames[j]);
                    owes[i] += amount;
                    owes[j] -= amount;
                }
            }
        }

        // Print payment details
        System.out.println("Payment details:");
        for (String detail : paymentDetails) {
            System.out.println(detail);
        }

        // Print who should receive from whom
        System.out.println("\nReceiving details:");
        for (int i = 0; i < numFriends; i++) {
            for (int j = 0; j < numFriends; j++) {
                if (i != j && owes[i] > 0 && owes[j] < 0) {
                    double amount = Math.min(Math.abs(owes[i]), Math.abs(owes[j]));
                    System.out.println(friendNames[i] + " should receive Rs- " + amount + " from " + friendNames[j]);
                    owes[i] -= amount;
                    owes[j] += amount;
                }
            }
        }

        scanner.close();
    }
}
