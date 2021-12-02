import java.util.ArrayList;
import java.util.List;

public class BankMain {

    private static  BankMain INSTANCE = null;
    private BankAccount bankAccount = null;

    public static BankMain getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankMain();
        }
        return  INSTANCE;
    }

    private BankMain() { }

    private List<BankAccount> bankAccountList = new ArrayList<>();

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }


}
