import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private int id;
    private String name;

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

    public AccountHolder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BankAccount openAccount(int id, String accountType, String accountHolder, String accountNumber) {
        new BankAccount();
        return switch (accountType) {
            case "Savings" -> new SavingsAccount(id, accountType, accountHolder, accountNumber);
            case "Student" -> new StudentAccount(id, accountType, accountHolder, accountNumber);
            case "Premium" -> new PremiumAccount(id, accountType, accountHolder, accountNumber);
            default -> new BankAccount();
        };
    }
}
