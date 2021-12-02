import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int id;
    private String accountType;
    private AccountHolder accountHolder;
    private double balance = 0;
    private String accountNumber;
    private ReentrantLock reentrantLock;
    private Condition condition;
    private int accountHolderCount = 0;
    List<AccountHolder> list = new ArrayList<>();

    public BankAccount() {
        this.id = 0;
        this.accountType = "";
        this.accountNumber = "";
        this.accountHolder = null;
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();

    }

    public BankAccount(int id, String accountType, AccountHolder accountHolder, String accountNumber) {
        this.id = id;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();

    }

    public BankAccount(int id, String accountType, double balance, String accountNumber) {
        this.id = id;
        this.accountType = accountType;
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();
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

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<AccountHolder> getList() {
        return list;
    }

    public void addAccountHolder(AccountHolder accountHolder) {
        list.add(accountHolder);
        accountHolderCount++;
    }

    /**
     * Method used for checking the balance
     * @return
     */
    public double getBalance() {

        reentrantLock.lock();
        try {
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() +
                    " is checking the balance for account " + accountNumber);
            System.out.println("The balance is: " + balance + Calendar.getInstance().getTime());
            condition.signalAll();
            return balance;
        } finally {
            reentrantLock.unlock();
        }
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

    /**
     * Deposit method used to deposit money to the bank account
     * @param amount
     */
    public void deposit(double amount) {
        System.out.println("The current balance in account number:" + accountNumber + " is: " + balance);

        reentrantLock.lock();
        try {
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() +
                    " is deposing money for account " + accountNumber + Calendar.getInstance().getTime());
            this.balance += amount;
            System.out.println("The new balance is: " + balance);
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    /**
     * Withdraw method used to withdraw money from the bank account
     * @param amount
     * @throws InterruptedException
     */
    public void withdraw(double amount) throws InterruptedException {
        System.out.println("The current balance in account number: " + accountNumber + " is: " + balance);
        boolean stillWaiting = true;
        reentrantLock.lock();
        try {
            while (balance < amount) {
                if (!stillWaiting) {
                    Thread.currentThread().interrupt();
                }

                stillWaiting = condition.await(10, TimeUnit.SECONDS);
            }
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() +
                    " is withdrawing money from account " + accountNumber + Calendar.getInstance().getTime());
            this.balance -= amount;
            System.out.println("The left balance is: " + balance);
            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

}
