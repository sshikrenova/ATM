import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean checkPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM {
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        accounts.put("123456", new Account("123456", "1234", 1000.00));
        accounts.put("654321", new Account("654321", "4321", 500.00));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете номера на акаунта: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account == null) {
            System.out.println("Невалиден номер на акаунта.");
            return;
        }

        System.out.print("Въведете ПИН кода: ");
        String pin = scanner.nextLine();

        if (!account.checkPin(pin)) {
            System.out.println("Невалиден ПИН код.");
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Изберете опция:");
            System.out.println("1. Проверка на баланс");
            System.out.println("2. Депозит");
            System.out.println("3. Теглене");
            System.out.println("4. Изход");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Вашият баланс е: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Въведете сумата за депозит: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Сумата е депозирана успешно.");
                    break;
                case 3:
                    System.out.print("Въведете сумата за теглене: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Сумата е изтеглена успешно.");
                    } else {
                        System.out.println("Недостатъчен баланс или невалидна сума.");
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Изход.");
                    break;
                default:
                    System.out.println("Невалидна опция.");
                    break;
            }
        }

        scanner.close();
    }
}

