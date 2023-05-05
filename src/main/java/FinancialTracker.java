import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FinancialTracker {
    static Scanner scanner = new Scanner(System.in);



    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static final String FILE_NAME = "transactions.csv";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static void main(String[] args) throws FileNotFoundException {
        loadTransactions(FILE_NAME);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

        scanner.close();
    }


    public static void loadTransactions(String fileName) throws FileNotFoundException {
        String line;

            Scanner scanner = new Scanner(new FileInputStream(fileName));
            while (scanner.hasNext()){
                line = scanner.nextLine();
                String[] parts = line.split("\\|");
                //String[] date = parts[0].split("-");
                LocalDate date = LocalDate.parse(parts[0]);
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                Transaction transaction = new Transaction(date, time, description,vendor, amount);
                transactions.add(transaction);
                System.out.println(transaction);

            }

        // This method should load transactions from a file with the given file name.
        // If the file does not exist, it should be created.
        // The transactions should be stored in the `transactions` ArrayList.
        // Each line of the file represents a single transaction in the following format:
        // <date>,<time>,<vendor>,<type>,<amount>
        // For example: 2023-04-29,13:45:00,Amazon,PAYMENT,29.99
        // After reading all the transactions, the file should be closed.
        // If any errors occur, an appropriate error message should be displayed.
    }

    private static void addDeposit(Scanner scanner) {


        try {
            System.out.println("Enter date: ") ;
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter time: " );
            String time = scanner.nextLine();
            System.out.println("Enter vendor: " );
            String vendor = scanner.nextLine();
            System.out.println("Enter amount: ");
            Double amount = scanner.nextDouble();
            System.out.println("Enter description: ");
            String description = scanner.next();
            System.out.println("Deposit added successfully! " );

            Transaction depositInfo = new Transaction(date, time, description, vendor, amount);
            transactions.add(depositInfo);
            String filepath = "transactions.csv";
            //FileWriter fileWriter = new FileWriter()
            FileWriter fileWriter =new FileWriter(filepath, true);
            BufferedWriter BW = new BufferedWriter(fileWriter);
                BW.write(depositInfo.getDate()+"\\|" + depositInfo.getTime()+"\\|" + depositInfo.getDescription()+"\\|"+ depositInfo.getVendor()+"\\|" + depositInfo.getAmount());
            BW.newLine();

// close the writer
            BW.close();
            // fileWriter = new FileWriter(filepath,true);



        } catch (IOException e) {
            System.out.println("an error has occurred! ");


        }
        //BufferedWriter BW = new BufferedWriter();

        // Perform necessary operations with the deposit amount
        // For example, you can store it in a data structure or process it in some way


    }
        // This method should prompt the user to enter the date, time, vendor, and amount of a deposit.
        // The user should enter the date and time in the following format: yyyy-MM-dd HH:mm:ss
        // The amount should be a positive number.
        // After validating the input, a new `Deposit` object should be created with the entered values.
        // The new deposit should be added to the `transactions` ArrayList.


    private static void addPayment(Scanner scanner) {

        try {
            System.out.println("Enter date: ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.println("Enter time: ");
            String time = scanner.nextLine();
            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Enter amount: ");
            Double amount = scanner.nextDouble();
            System.out.println("Enter description: ");
            String description = scanner.next();
            System.out.println("Payment added successfully! ");

            Transaction paymentInfo = new Transaction(date, time, description, vendor, amount);
            transactions.add(paymentInfo);
            String filepath = "transactions.csv";
            //FileWriter fileWriter = new FileWriter()
            FileWriter fileWriter = new FileWriter(filepath, true);
            BufferedWriter BW = new BufferedWriter(fileWriter);
            BW.write(paymentInfo.getDate() + "\\|" + paymentInfo.getTime() + "\\|" + paymentInfo.getDescription() + "\\|" + paymentInfo.getVendor() + "\\|" + paymentInfo.getAmount());
            BW.newLine();

// close the writer
            BW.close();
            // fileWriter = new FileWriter(filepath,true);


        } catch (IOException e) {
            System.out.println("an error has occurred! ");


            // Consume the remaining newline character

            // Perform necessary operations with the payment amount
            // For example, you can store it in a data structure or process it in some way


        }
    }   // This method should prompt the user to enter the date, time, vendor, and amount of a payment.
        // The user should enter the date and time in the following format: yyyy-MM-dd HH:mm:ss
        // The amount should be a positive number.
        // After validating the input, a new `Payment` object should be created with the entered values.
        // The new payment should be added to the `transactions` ArrayList.


    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private static void displayLedger() {

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getDate()+ " " +  transaction.getTime()+ " " + transaction.getDescription()+ " " + transaction.getVendor() + " " + transaction.getAmount());
            // This method should display a table of all transactions in the `transactions` ArrayList.
            // The table should have columns for date, time, vendor, type, and amount.
            // The total balance of all transactions should be displayed at the bottom of the table.
        }}

    private static void displayDeposits() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount()> 0)
            System.out.println(transaction.getDate()+ " " +  transaction.getTime()+ " " + transaction.getDescription()+ " " + transaction.getVendor() + " " + transaction.getAmount());
        // This method should display a table of all deposits in the `transactions` ArrayList.
        // The table should have columns for date, time, vendor, and amount.
        // The total amount of all deposits should be displayed at the bottom of the table.
    }
    }

    private static void displayPayments() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount()< 0)
                System.out.println(transaction.getDate()+ " " +  transaction.getTime()+ " " + transaction.getDescription()+ " " + transaction.getVendor() + " " + transaction.getAmount());
        // This method should display a table of all payments in the `transactions` ArrayList.
        // The table should have columns for date, time, vendor, and amount.
        // The total amount of all payments should be displayed at the bottom of the table.
    }}

    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");


            String input = scanner.nextLine().trim();


            switch (input) {

                case "1":
                    monthToDate();
                    // Generate a report for all transactions within the current month,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the month.
                    break;
                case "2":
                    previousMonth();
                    // Generate a report for all transactions within the previous month,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the month.
                    break;
                case "3":
                    yearToDate();
                    // Generate a report for all transactions within the current year,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the year.
                    break;

                case "4":
                    previousYear();
                    // Generate a report for all transactions within the previous year,
                    // including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the year.
                    break;
                case "5":
                    searchByVendor("");
                    // Prompt the user to enter a vendor name, then generate a report for all transactions
                    // with that vendor, including the date, vendor, and amount for each transaction.
                    // The report should include a total of all transaction amounts for the vendor.
                    break;
                case "0":
                    break;

                //running = false;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }


    private static void monthToDate() {
        LocalDate now = LocalDate.now();

        for (Transaction transaction : transactions) {

            if (transaction.getDate().getMonth() == now.getMonth() && transaction.getDate().getYear() == now.getYear()){
                System.out.println(transaction);
        }
            // Generate a report for all transactions within the current month,
        // including the date, vendor, and amount for each transaction.
        // The report should include a total of all transaction amounts for the month.


    }}

    private static void previousMonth() {
        LocalDate now = LocalDate.now();
        for (Transaction transaction: transactions){
            if (transaction.getDate().getMonth() == now.getMonth().minus(1) && transaction.getDate().getYear() == now.getYear()) {

                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription()
                        + "|" + transaction.getVendor() + "|" + transaction.getAmount());

        }
        // Generate a report for all transactions within the previous month,
        // including the date, vendor, and amount for each transaction.



    }}

    private static void yearToDate() {
            for (Transaction transaction : transactions) {
                LocalDate date = LocalDate.now();

                if (transaction.getDate().getYear() == date.getYear()) {

                    System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription()
                            + "|" + transaction.getVendor() + "|" + transaction.getAmount());
                }

        // This method filters the transactions by date and prints a report to the console.
        // It takes two parameters: startDate and endDate, which represent the range of dates to filter by.
        // The method loops through the transactions list and checks each transaction's date against the date range.
        // Transactions that fall within the date range are printed to the console.
        // If no transactions fall within the date range, the method prints a message indicating that there are no results.
    }}
    private static void previousYear() {
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.now();

            if (transaction.getDate().getYear() == date.getYear() - 1) {

                System.out.println(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription()
                        + "|" + transaction.getVendor() + "|" + transaction.getAmount());
            }

    }}


    private static void searchByVendor(String vendor) {
        // This method filters the transactions by vendor and prints a report to the console.
        // It takes one parameter: vendor, which represents the name of the vendor to filter by.
        // The method loops through the transactions list and checks each transaction's vendor name against the specified vendor name.
        // Transactions with a matching vendor name are printed to the console.
        // If no transactions match the specified vendor name, the method prints a message indicating that there are no results.
    }
}

























