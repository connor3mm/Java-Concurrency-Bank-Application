import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Employee {
    private int id;
    private String name;
    private ReentrantLock reentrantLock;
    private List<BankAccount> bankAccountList = new ArrayList<>();
    private Condition condition;

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
        this.bankAccountList.add(newAccount);
    }

    public void deleteAccount(int id) {
        bankAccountList.removeIf(currentAccount -> currentAccount.getId() == id);
    }

    public void editAccount(int id, String newAccountNumber, String newAccountType) {
        //Get account to edit
        BankAccount accountToEdit = null;
        for (BankAccount currentAccount : bankAccountList) {
            if (currentAccount.getId() == id) {
                accountToEdit = currentAccount;
                bankAccountList.remove(currentAccount);
            }
        }

        if (accountToEdit == null) return;

        //edit
        accountToEdit.setAccountNumber(newAccountNumber);
        accountToEdit.setAccountType(newAccountType);

        //put account bank in the list
        bankAccountList.add(accountToEdit);
    }
/*
    public void transferMoneyEmployee(BankAccount sender, BankAccount receiver, double amount) {
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
    }*/

    public void transferMoneyIn(BankAccount receiver, double amount) {
        reentrantLock.lock();
        try {
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() +
                    " is transferring money to account " + receiver.getAccountNumber());
            receiver.deposit(amount);
           condition.signalAll();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("The new balance is: " + receiver.getBalance());
    }

    public void transferMoneyOut(BankAccount receiver, double amount) {
        reentrantLock.lock();
        try {
            System.out.println("Thread with a name " + Thread.currentThread().getName() + " and id " + Thread.currentThread().getId() +
                    " is transferring money to account " + receiver.getAccountNumber());
            receiver.withdraw(amount);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("The new balance is: " + receiver.getBalance());
    }

}
