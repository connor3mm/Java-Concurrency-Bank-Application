import java.util.Calendar;


public class DriverTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1, "Savings", 1200, "1234");
        Employee employee = new Employee(1,"John");
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount,100, employee);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount,100, employee);
            transaction.start();
        }


    }
}
