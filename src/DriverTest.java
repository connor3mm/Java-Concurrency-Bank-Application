public class DriverTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1, "Savings", 1200, "1234");
        Employee employee = new Employee(1, "John");
        AccountHolder holder = new AccountHolder(1, "Tamara", bankAccount);
        double amount = 100;

        //Test 1
        testBalance(bankAccount);

        //Test2
        checkBalanceAndDeposit(holder, bankAccount);
        checkBalanceAndWithdraw(holder, bankAccount);

        //Test3
        testDepositAndWithdraw(holder, bankAccount);

        //Test4
        testingEmployeeTransferPlusTestThree(employee, holder, bankAccount);
        testingEmployeeTransferPlusTestThreePart2(employee, holder, bankAccount);

        //Test5

    }


    /**
     * Test 1
     *
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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");

    }


    /**
     * Test 2
     *
     * @param bankAccount
     */
    public static void checkBalanceAndDeposit(AccountHolder holder, BankAccount bankAccount) {


        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");


    }


    public static void checkBalanceAndWithdraw(AccountHolder holder, BankAccount bankAccount) {

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.BALANCE, bankAccount);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");
    }


    /**
     * Test 3
     *
     * @param bankAccount
     */
    public static void testDepositAndWithdraw(AccountHolder holder, BankAccount bankAccount) {
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, holder);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");
    }


    /**
     * Test 4
     *
     * @param employee
     * @param holder
     * @param bankAccount
     */
    public static void testingEmployeeTransferPlusTestThree(Employee employee, AccountHolder holder, BankAccount bankAccount) {

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.TRANSFERIN, bankAccount, 100, employee);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, holder);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");
    }


    public static void testingEmployeeTransferPlusTestThreePart2(Employee employee, AccountHolder holder, BankAccount bankAccount) {

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.TRANSFEROUT, bankAccount, 100, employee);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.DEPOSIT, bankAccount, 100, holder);
            transaction.start();
        }

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("--------------------------------");
    }


    /**
     * Test 5
     * @param employee
     * @param holder
     * @param bankAccount
     */
    public static void testingInsufficientFunds(Employee employee, AccountHolder holder, BankAccount bankAccount) {

    }



}
