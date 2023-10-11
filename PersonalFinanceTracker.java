import java.io.*;
import java.util.Scanner;

public class PersonalFinanceTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPersonal Finance Tracker Menu:");
            System.out.println("1. Record an Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> recordExpense();
                case 2 -> viewExpenses();
                case 3 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void recordExpense() {
        try {
            FileWriter fileWriter = new FileWriter("expenses.txt", true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the description of the expense: ");
            String description = scanner.nextLine();

            System.out.print("Enter the amount: INR ");
            double amount = scanner.nextDouble();

            writer.write(description + "," + amount + "\n");
            writer.close();

            System.out.println("Expense recorded successfully.");
        } catch (IOException e) {
            System.err.println("Error recording the expense: " + e.getMessage());
        }
    }

    private static void viewExpenses() {
        try {
            FileReader fileReader = new FileReader("expenses.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            double totalExpenses = 0.0;

            System.out.println("\nExpenses:");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String description = parts[0];
                double amount = Double.parseDouble(parts[1]);

                System.out.println(description + ": INR " + amount);
                totalExpenses += amount;
            }

            System.out.println("\nTotal Expenses: INR " + totalExpenses);

            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading expenses: " + e.getMessage());
        }
    }
}
