import java.io.*;
import java.util.*;

public class MainCSV {
    private static final String FILE_CSV = "accounts.csv";

    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int nextId = 1;

        // Load CSV if exists
        File file = new File(FILE_CSV);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String code = parts[1];
                    String holder = parts[2];

                    if (parts[3].matches("\\d+(\\.\\d+)?")) { // Savings
                        double deposit = Double.parseDouble(parts[3]);
                        int term = Integer.parseInt(parts[4]);
                        accounts.add(new SavingsAccount(id, code, holder, deposit, term));
                    } else { // Checking
                        String card = parts[3];
                        double balance = Double.parseDouble(parts[4]);
                        accounts.add(new CheckingAccount(id, code, holder, card, balance));
                    }
                    nextId = Math.max(nextId, id + 1);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Cannot read CSV.");
            }
        }

        boolean run = true;
        while (run) {
            System.out.println("\n=== BANK ACCOUNT MANAGEMENT ===");
            System.out.println("1. Add new");
            System.out.println("2. Delete");
            System.out.println("3. Show list");
            System.out.println("4. Search");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Account type (1=Savings, 2=Checking): ");
                    String type = sc.nextLine();
                    System.out.print("Account code: ");
                    String code = sc.nextLine();
                    System.out.print("Account holder: ");
                    String holder = sc.nextLine();

                    BankAccount account = null;
                    if (type.equals("1")) {
                        account = new SavingsAccount(nextId++, code, holder, 1000, 12);
                    } else if (type.equals("2")) {
                        account = new CheckingAccount(nextId++, code, holder, "1234", 500);
                    } else {
                        System.out.println("Invalid type.");
                    }

                    if (account != null) {
                        accounts.add(account);
                        saveToCSV(accounts);
                        System.out.println("Added successfully.");
                    }
                    break;

                case "2":
                    System.out.print("Enter ID to delete: ");
                    int idDel = Integer.parseInt(sc.nextLine());
                    boolean removed = accounts.removeIf(a -> a.getId() == idDel);
                    if (removed) {
                        saveToCSV(accounts);
                        System.out.println("Deleted successfully.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case "3":
                    System.out.println("\n--- Account List ---");
                    for (BankAccount a : accounts) {
                        System.out.println(a);
                    }
                    break;

                case "4":
                    System.out.print("Enter search keyword: ");
                    String keyword = sc.nextLine();
                    System.out.println("\n--- Search Result ---");
                    for (BankAccount a : accounts) {
                        if (a.accountCode.contains(keyword) || a.accountHolder.contains(keyword)) {
                            System.out.println(a);
                        }
                    }
                    break;

                case "5":
                    run = false;
                    System.out.println("Exit program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    private static void saveToCSV(List<BankAccount> accounts) {
        try (PrintWriter pw = new PrintWriter(new File(FILE_CSV))) {
            for (BankAccount a : accounts) {
                pw.println(a.toCSV());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to CSV.");
        }
    }
}
