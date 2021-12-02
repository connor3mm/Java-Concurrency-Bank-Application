import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Employee {
    private int id;
    private String name;
    private ReentrantLock reentrantLock;
    private Condition condition;
    BankMain banklist = BankMain.getInstance();

    AccountHolder holder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        reentrantLock = new ReentrantLock();
        condition = reentrantLock.newCondition();
    }

    public void openAccount(int id, String accountType, AccountHolder accountHolder, String accountNumber) {
        new BankAccount();
        BankAccount newAccount = switch (accountType) {
            case "Savings" -> new SavingsAccount(id, accountType, accountHolder, accountNumber);
            case "Student" -> new StudentAccount(id, accountType, accountHolder, accountNumber);
            case "Premium" -> new PremiumAccount(id, accountType, accountHolder, accountNumber);
            default -> new BankAccount();
        };

        banklist.addBankAccount(newAccount);
    }

    public void deleteAccount(int id) {
        banklist.getBankAccountList().removeIf(currentAccount -> currentAccount.getId() == id);
    }

    public void editAccount(int currentId, String newAccountNumber, String newAccountType) {
        //Get account to edit
        BankAccount accountToEdit = null;

        reentrantLock.lock();

        try {
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", ID " + Thread.currentThread().getId() +
                    " trying to change details of account " + currentId  + ", Date and Time: " + Calendar.getInstance().getTime());

            for (BankAccount currentAccount : banklist.getBankAccountList()) {
                if (currentAccount.getId() == currentId) {
                    accountToEdit = new BankAccount();
                    accountToEdit.setId(currentId);
                    currentAccount = accountToEdit;

                    accountToEdit.setAccountNumber(newAccountNumber);
                    accountToEdit.setAccountType(newAccountType);

                    banklist.getBankAccountList().remove(currentAccount);
                }

            }

            if (accountToEdit == null) return;

            //put account bank in the list
            banklist.getBankAccountList().add(accountToEdit);
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", ID: " + Thread.currentThread().getId() +
                    " has modified the account" + ", Date and Time: " + Calendar.getInstance().getTime());

            System.out.println("The new details are: " + accountToEdit.getAccountNumber() + " " + accountToEdit.getAccountType() + " " + accountToEdit.getId() + "\n");

//            condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }

    }

    public void transferMoneyIn(BankAccount receiver, double amount) {

        reentrantLock.lock();
        try {
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", ID: " + Thread.currentThread().getId() +
                    " transferring money to " + receiver.getAccountNumber() + ", Date and Time: " + Calendar.getInstance().getTime() + "\n");
            receiver.deposit(amount);
           condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("The new balance is: " + receiver.getBalance());
    }

    public void transferMoneyOut(BankAccount sender, double amount) {
        reentrantLock.lock();
        try {
            System.out.println("Thread name: " + Thread.currentThread().getName() + ", ID: " + Thread.currentThread().getId() +
                    " transferring money to  " + sender.getAccountNumber() + ", Date and Time: " + Calendar.getInstance().getTime() + "\n");
            sender.withdraw(amount);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("The new balance is: " + sender.getBalance());
    }

}
