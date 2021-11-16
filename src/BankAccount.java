import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int id;
    private String accountType;
    private String accountHolder;
    private double balance = 0;
    private String accountNumber;
    private ReentrantLock reentrantLock;

    public BankAccount() {
    }


    public BankAccount(int id, String accountType, String accountHolder, String accountNumber) {
        this.id = id;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        //this.balance = 0;
        this.accountNumber = accountNumber;
        reentrantLock = new ReentrantLock();

    }

    public BankAccount(int id, String accountType, double balance, String accountNumber) {
        this.id = id;
        this.accountType = accountType;
        reentrantLock = new ReentrantLock();
        this.balance = balance;
        this.accountNumber = accountNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        reentrantLock.lock();

        try {
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() + " is checking the balance for account " + accountNumber);
            System.out.println("The balance is: " + balance);
        } finally {
            reentrantLock.unlock();
        }

        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
//        if (amount > balance) {
//            System.out.println("Not enough money in account");
//            return;
//        }
        this.balance -= amount;
    }

    public void transferMoney(BankAccount sender, BankAccount receiver, double amount) {
        sender.setBalance(balance - amount);
        receiver.setBalance(balance + amount);
    }
}
