public class DriverTest {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1, "Savings", 1200, "1234");
        Employee employee = new Employee(1, "John");
        Employee employee2 = new Employee(2, "James");
        AccountHolder holder = new AccountHolder(1, "Tamara", bankAccount);
        employee.openAccount(1, "Savings", holder, "12");

        //Test 1
        testBalance(bankAccount);

        //Test2
        checkBalanceAndDeposit(holder, bankAccount);
        checkBalanceAndWithdraw(holder, bankAccount);

        //Test3
        testDepositAndWithdraw(holder, bankAccount);

        //Test4
        testingEmployeeTransferIn(employee, holder, bankAccount);
        testingEmployeeTransferOut(employee, holder, bankAccount);

        //Test5
        testingInsufficientFunds(holder, bankAccount);

        //Test6
        employeesModifyingAccountDetails(employee, employee2, bankAccount);


    }


    /**
     * Test 1
     * Two account holders are trying to check the balance simultaneously
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
     * One account holder is trying to check the balance while the other is depositing money
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

    /**
     * Test 2
     * One account holder is trying to check the balance while the other is withdrawing money
     * @param holder
     * @param bankAccount
     */
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
     * The two account holders are trying simultaneously to deposit/withdraw money & check the balance
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
     * Same as 3, but at the same time a bank employee is in the process of completing a money
     * transfer in the account
     * @param employee
     * @param holder
     * @param bankAccount
     */
    public static void testingEmployeeTransferIn(Employee employee, AccountHolder holder, BankAccount bankAccount) {

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


    /**
     * Test 4
     * Same as 3, but at the same time a bank employee is in the process of completing a money
     * transfer out the account
     * @param employee
     * @param holder
     * @param bankAccount
     */
    public static void testingEmployeeTransferOut(Employee employee, AccountHolder holder, BankAccount bankAccount) {

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
     * There are insufficient funds to complete a withdraw
     * @param holder
     * @param bankAccount
     */
    public static void testingInsufficientFunds(AccountHolder holder, BankAccount bankAccount) {

        bankAccount.setBalance(0);

        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.WITHDRAW, bankAccount, 100, holder);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
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

    /**
     * Test 6
     * Two bank employees are trying simultaneously to modify the details of a bank account
     * @param employee
     * @param employee2
     * @param bankAccount
     */
    public static void employeesModifyingAccountDetails(Employee employee, Employee employee2, BankAccount bankAccount) {

        //fix this
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.EDIT, bankAccount, 1, "4567", "Premium", employee);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }


        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction(Transaction.Type.EDIT, bankAccount, 1, "7890", "Premium", employee2);
            transaction.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("--------------------------------");
    }


}
