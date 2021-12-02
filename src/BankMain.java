import java.util.ArrayList;
import java.util.List;

public class BankMain {

    private static  BankMain INSTANCE = null;
    private static List<BankAccount> bankAccountList;

    private BankMain() { }

    /**
     * Public static method that returns the instance of the class
     * @return INSTANCE
     */
    public static BankMain getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankMain();
            bankAccountList = new ArrayList<>();
        }
        return INSTANCE;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }


}
