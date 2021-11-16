public class Driver {

    public static void main(String[] args) {

        //Test1 for checking the balance
        BankAccount bankAccount = new BankAccount(1,"Savings", 1200, "1234");
        AccountHolder accountHolder1 = new AccountHolder(1,"Cameron", bankAccount);
        AccountHolder accountHolder2 = new AccountHolder(2,"Tamara", bankAccount);
        BalanceCheckingRunnable object1 = new BalanceCheckingRunnable(bankAccount);
        Thread oneThread = new Thread(object1);
        Thread twoThread = new Thread(object1);

        oneThread.setName("Tamara");
        twoThread.setName("Cameron");

        oneThread.start();
        twoThread.start();

        //Test2



        //Test3
    }

}
