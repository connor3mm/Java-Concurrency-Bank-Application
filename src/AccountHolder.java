import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private int id;
    private String name;
    BankAccount bankAccount;


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

    public AccountHolder(int id, String name, BankAccount bankAccount) {
        this.id = id;
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public BankAccount openAccount(int id, String accountType, AccountHolder accountHolder, String accountNumber) {
        new BankAccount();
        return switch (accountType) {
            case "Savings" -> new SavingsAccount(id, accountType, accountHolder, accountNumber);
            case "Student" -> new StudentAccount(id, accountType, accountHolder, accountNumber);
            case "Premium" -> new PremiumAccount(id, accountType, accountHolder, accountNumber);
            default -> new BankAccount();
        };
    }
}
