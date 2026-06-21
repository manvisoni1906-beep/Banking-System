import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Account{
    private String userName;
    private float Balance;
    private long accountNumber;
    private int accountPin;
public float Amount;
 private String account_type;

private ArrayList<Transaction> TransactionHistory = new ArrayList<>();
    Account(String userName,float Balance,long accountNumber,int accountPin,String account_type){
        this.userName = userName;
        this.Balance = Balance;
        this.accountNumber = accountNumber;
        this.accountPin = accountPin;
        this.account_type = account_type;
//        Initializing the list in constructor
        this.TransactionHistory = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public float getBalance() {
        return Balance;
    }
    public void setBalance(float balance) {
        Balance = balance;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public void setAccountPin(int accountPin) {
        this.accountPin = accountPin;
    }
    public int getAccountPin() {
        return accountPin;
    }
public String getAccount_type() {
        return account_type;
}
public void setAccount_type(String account_type) {
        this.account_type = account_type;
}
 public ArrayList<Transaction> getTransactionHistory() {
        return TransactionHistory;
 }
}

class Transaction{
    float amount;
    String type;
    String dateTime;
    String Status;

    Transaction(float amount,String type,String dateTime,String Status){
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
        this.Status = Status;
    }
}
public class BankingSystem {
    float minimumBalance = 10000.0f;

    ArrayList<Account> accounts = new ArrayList<>();
Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public void init() {
        accounts.add(new Account("Manvi",200000.0f,12345679,4567,"Current Account"));
        accounts.add(new Account("Sunidhi",50000.0f,25345678,7014,"Saving Account"));
        accounts.add(new Account("Aarchi",180000.0f,12387789,1010,"Saving Account"));
        accounts.add(new Account("Arti",80000.0f,17456789,4011,"Saving Account"));
        accounts.add(new Account("Bhumika",89000.0f,12345429,1107,"Current Account"));
        accounts.add(new Account("Meenu Soni",800000.0f,22446789,8090,"Current Account"));
    }

    public void createAccount() {
        System.out.println("Enter Your name: ");
        String username = sc.nextLine();
        System.out.println("Enter Your Age: ");
        int age = sc.nextInt();
        System.out.println("Enter 10-Digit Mobile Number: ");
        long mobile = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter Account Type:");
        String account_type = sc.nextLine();
        long accountNumber = rand.nextLong(12000000, 30000000);
        System.out.println("Your Account Number is : " + accountNumber);
        int accountPin = rand.nextInt(1000, 10000);
        System.out.println("Your Account Pin is : " + accountPin);
        System.out.println("Your Account Is Successfully Created!");
        System.out.println("---- Welcome To The HDFC Bank ----");

        System.out.println("Enter The Initial 10,000 Amount To Deposit In Your Account: ");
        float initialAmount = sc.nextFloat();
        float totalAmount = initialAmount;

        while (totalAmount < minimumBalance) {
            float remainingAmount = minimumBalance - totalAmount;
            System.out.println("Minimum Balance Requirement Is 10,000! Your Amount Is Short By: " + remainingAmount);
            System.out.println("Enter " + remainingAmount + " Amount: ");
            float moreAmount = sc.nextFloat();
            sc.nextLine();
            totalAmount += moreAmount;
        }

        System.out.println("Amount Deposited!");
        Account newAccount = new Account(username, totalAmount, accountNumber, accountPin, account_type);
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Transaction firstTransaction = new Transaction(totalAmount, "Initial Deposit", formattedDate, "Success");
        newAccount.getTransactionHistory().add(firstTransaction);
        accounts.add(newAccount);
    }

    public void deposit(){
        System.out.println("Enter amount to deposit: ");
        float Amount = sc.nextFloat();
        System.out.println("Enter Your Account Number: ");
        long enteredAccountNumber = sc.nextLong();
        System.out.println("Enter Your Account Pin: ");
        int enteredAccountPin = sc.nextInt();
        sc.nextLine();
        for(Account account:accounts){
            if(enteredAccountNumber == account.getAccountNumber() &&  enteredAccountPin == account.getAccountPin()){
                account.setBalance(account.getBalance()+Amount);
                System.out.println(Amount + " Successfully deposited To Your Account!");
                String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                Transaction t = new Transaction(Amount,"Deposit",formattedDate,"Success");
                account.getTransactionHistory().add(t);
            }
        }
    }


    public void withdrawal() {
        System.out.println("Enter amount to withdraw: ");
        float Amount = sc.nextFloat();
        System.out.println("Enter Your Account Number: ");
        long enteredAccountNumber = sc.nextLong();
        System.out.println("Enter Your Account Pin: ");
        int enteredAccountPin = sc.nextInt();
        sc.nextLine();
        for (Account account : accounts) {
            if (enteredAccountNumber == account.getAccountNumber() && enteredAccountPin == account.getAccountPin()) {
                if (account.getBalance()-Amount >= minimumBalance) {
                    account.setBalance(account.getBalance() - Amount);
                    System.out.println(Amount + " Successfully Withdrawn!");
                    String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    Transaction t = new Transaction(Amount,"Withdraw",formattedDate,"Success");
                    account.getTransactionHistory().add(t);
                }
                else{
                    System.out.println("Insufficient Balance!");
                }
            }
        }
    }


boolean found;
    public void checkBalance() {
        found = false;
        System.out.println("Enter Your Account Number:");
        long enteredAccountNumber = sc.nextLong();
        System.out.println("Enter Your Account Pin:");
        int enteredAccountPin = sc.nextInt();
        for (Account acc : accounts) {
if(acc.getAccountNumber() == enteredAccountNumber &&  acc.getAccountPin() == enteredAccountPin){
    found = true;
    System.out.println("Your balance is " + acc.getBalance());
    break;
}
        }
        if(!found){
            System.out.println("Invalid Details!");
        }
    }

public void changePin(){
        boolean found = false;
    System.out.println("Enter Your Account Number:");
    long enteredAccountNumber = sc.nextLong();
    System.out.println("Enter Your Account Pin:");
    int enteredAccountPin = sc.nextInt();
    sc.nextLine();
    for (Account acc : accounts) {
        if(acc.getAccountNumber() == enteredAccountNumber && acc.getAccountPin() == enteredAccountPin) {
            found = true;
            System.out.println("Enter New Pin:");
            int newPin = sc.nextInt();
            System.out.println("Please Enter New Pin Again:");
            int ConfirmPin = sc.nextInt();
            if (newPin == ConfirmPin) {
                acc.setAccountPin(newPin);
                System.out.println("Pin Changed Successfully!");
            }
        }
    }
        if(!found){
            System.out.println("Invalid Pin!");
        }
}


boolean accType = false;
public void checkAccountType() {
    accType = false;
    System.out.println("Enter Your Account Number:");
    long enteredAccountNumber = sc.nextLong();
    System.out.println("Enter Your Account Pin:");
    int enteredAccountPin = sc.nextInt();
    sc.nextLine();
    for (Account acc : accounts) {
        if (acc.getAccountNumber() == enteredAccountNumber && acc.getAccountPin() == enteredAccountPin) {
            accType = true;
            System.out.println("Your Account Type is " + acc.getAccount_type());
        }
    }

    if (!accType) {
        System.out.println("Invalid Account!");
    }
}

public void showTransactions(){
    System.out.println("Enter Your Account Number:");
    long enteredAccountNumber = sc.nextLong();
    System.out.println("Enter Your Account Pin:");
    int enteredAccountPin = sc.nextInt();
    sc.nextLine();
    boolean found = false;
    for (Account acc : accounts) {
        if(enteredAccountNumber == acc.getAccountNumber() && enteredAccountPin == acc.getAccountPin()) {
            found = true;
            System.out.println("--- Transaction History For " +acc.getUserName()+ " --- ");
            for(Transaction t : acc.getTransactionHistory()){
                System.out.println("Type: " + t.type+ "| Amount: " + t.amount + "| Date: "+t.dateTime + "| Status: "+t.Status);
            }
            break;
        }
    }
    if(!found){
        System.out.println("Invalid Details! ");
    }
}

public void saveData() {
    try {
        FileWriter writer = new FileWriter("bank_data.txt");
        for(Account acc : accounts){
            writer.write(acc.getUserName() + "," + acc.getAccountNumber() + "," + acc.getAccountPin() + "," + acc.getBalance()+","+ acc.getAccount_type());
        }
        writer.close();
        System.out.println("Data Saved Successfully!");
    } catch(IOException e) {
        System.out.println("Error Saving Data!");
    }
}

public void moneyTransfer() {
    boolean senderFound = false;
    System.out.println("Enter Your Account Number:");
    long senderAccountNumber = sc.nextLong();
    System.out.println("Enter Your Account Pin:");
    int senderAccountPin = sc.nextInt();
    sc.nextLine();

    for (Account sender : accounts) {
        if (senderAccountNumber == sender.getAccountNumber() && senderAccountPin == sender.getAccountPin()) {
            senderFound = true;
            System.out.println("Enter the Account Number Of The Person You Want To Transfer:");
            long receiverAccountNumber = sc.nextLong();
            boolean receiverFound = false;
            for (Account receiver : accounts) {
                if (receiverAccountNumber == receiver.getAccountNumber()) {
                    receiverFound = true;
                    System.out.println("The Name Of the Receiver Is: " + receiver.getUserName());
                    System.out.println("Enter The Amount: ");
                    float transferAmount = sc.nextFloat();
                    sc.nextLine();

                    if (sender.getBalance() - transferAmount >= minimumBalance) {
sender.setBalance(sender.getBalance() - transferAmount);
receiver.setBalance(receiver.getBalance() + transferAmount);

                        System.out.println("--- Processing Your Payment ---");
                        System.out.println(transferAmount + " Amount Successfully Transferred To:" + receiver.getUserName());
                        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                        Transaction senderT = new Transaction(transferAmount, " Transferred To: " + receiver.getUserName(), formattedDate, "Success");
                        sender.getTransactionHistory().add(senderT);

                        Transaction receiverT = new Transaction(transferAmount, " Received From: " + sender.getUserName(), formattedDate, "Success");
                        receiver.getTransactionHistory().add(receiverT);
                    }

                    else{
                        System.out.println("Transferred Failed! Insufficient Balance.");
                    }
                    break;
                }
            }
            if(!receiverFound){
                System.out.println("Receiver Not Found!");
            }
            break;
        }
    }
    if(!senderFound){
        System.out.println("Invalid Sender Details!");
    }
}


    public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
BankingSystem bs = new BankingSystem();
bs.init();
while(true){
    System.out.println("---- Welcome To The HDFC Banking System -----");
    System.out.println("1. Create Account 2. Deposit Amount 3. Withdraw Amount 4. Check Account Balance 5.Check Account Type 6. Change Pin 7.Transfer Money 8.Transaction History  9.Exit");
    int choice = sc.nextInt();
    sc.nextLine();
    switch (choice){
        case 1:
            System.out.println("--- Creating Account ---");
            bs.createAccount();
        break;

        case 2: bs.deposit();
        break;

        case 3: bs.withdrawal();
        break;

        case 4: bs.checkBalance();
        break;

        case 5:
            System.out.println("--- Checking Your Account Type ---");
            bs.checkAccountType();
        break;

        case 6:
            System.out.println("--- Changing Pin ---");
            bs.changePin();
        break;

        case 7:
            System.out.println("--- Money Transfer ---");
            bs.moneyTransfer();
            break;

        case 8:
            System.out.println("--- Showing Your Transactions ---");
            bs.showTransactions();
            break;

        case 9 :bs.saveData();
            System.out.println("--- ThankYou For Visiting Banking System ---");
            System.out.println("Welcome Again!");
            return;

        default :
            System.out.println("Invalid Choice! (Please Enter Between The Range (1-9))");
            System.out.println("ThankYou!");
            break;
    }
}
    }
}
