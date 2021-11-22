public class DriverTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1, "Savings", 1200, "1234");
        Employee employee = new Employee(1, "John");
        Employee employee2 = new Employee(1, "Jim");
        double amount = 100;

        //Test 1
        testBalance(bankAccount);
        System.out.println("--------------------------------");

        //Test2
        checkBalanceAndDeposit(employee, employee2, bankAccount, amount);
        checkBalanceAndWithdraw(employee,employee2, bankAccount, amount);

        System.out.println("--------------------------------");
        //Test3
        testDepositAndWithdraw(employee, bankAccount);

    }


    /**
     * Test 1
     * @param bankAccount
     */
    public static void testBalance(BankAccount bankAccount) {
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }
    }


    /**
     * Test 2
     * @param bankAccount
     */
    public static void checkBalanceAndDeposit(Employee employee,Employee employee2, BankAccount bankAccount, Double amount) {



        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, employee);
            transaction.start();
        }
    }


    public static void checkBalanceAndWithdraw(Employee employee,Employee employee2, BankAccount bankAccount, Double amount) {

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, employee);
            transaction.start();
        }
    }


    /**
     * Test 3
     * @param bankAccount
     */
    public static void testDepositAndWithdraw(Employee employee, BankAccount bankAccount) {
            for (int i = 0; i < 10; i++) {
                Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, employee);
                transaction.start();
            }

            for (int i = 0; i < 10; i++) {
                Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, employee);
                transaction.start();
            }
        }


}
