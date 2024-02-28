package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, String> studentAccounts = new HashMap<>();
    private static final Map<String, String> adminAccounts = new HashMap<>();

    static {
        studentAccounts.put("202310370311470", "StudentPassword123");
        adminAccounts.put("admin","admin");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Library System ====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.println("=====");

            int choice = getIntegerInput(scanner);

            switch (choice) {
                case 1:
                    loginAsStudent(scanner);
                    break;
                case 2:
                    loginAsAdmin(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }
        }

        scanner.close();
    }

    private static void loginAsStudent(Scanner scanner) {
        System.out.print("Enter your NIM: ");
        String nim = scanner.nextLine();
        if (studentAccounts.containsKey(nim)) {
            System.out.println("Successful Login as Student");
            // Add student functionality here
        } else {
            System.out.println("User Not Found");
        }
    }

    private static void loginAsAdmin(Scanner scanner) {
        System.out.print("Enter your username (admin): ");
        String username = scanner.nextLine();

        if (adminAccounts.containsKey(username)) {
            System.out.print("Enter your password (admin): ");
            String password = scanner.nextLine();

            if (adminAccounts.get(username).equals(password)) {
                System.out.println("Successful Login as Admin");
                // Add admin functionality here
            } else {
                System.out.println("Incorrect Password");
            }
        } else {
            System.out.println("Admin User Not Found");
        }
    }

    private static int getIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}