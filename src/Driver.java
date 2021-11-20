public class Driver {

    public static void main(String[] args) {

        //Test1 for checking the balance
        BankAccount bankAccount = new BankAccount(1,"Savings", 1200, "1234");
        AccountHolder accountHolder1 = new AccountHolder(1,"Cameron", bankAccount);
        AccountHolder accountHolder2 = new AccountHolder(2,"Tamara", bankAccount);

        /*BalanceCheckingRunnable balanceObject = new BalanceCheckingRunnable(bankAccount);
        WithdrawRunnable withdrawObject = new WithdrawRunnable(bankAccount, 10);
        DepositRunnable depositObject = new DepositRunnable(bankAccount, 20);

       for (int i = 0; i < 100; i++){
            Thread balance = new Thread(balanceObject);
            Thread deposit = new Thread(depositObject);
            Thread withdraw = new Thread(withdrawObject);

            balance.setName("Balance");
            withdraw.setName("Withdraw");
            deposit.setName("Deposit");

            balance.start();
            withdraw.start();
            deposit.start();
        }*/

       BalanceCheckingRunnable balanceObject = new BalanceCheckingRunnable(bankAccount);
        Thread oneThread = new Thread(balanceObject);
        Thread twoThread = new Thread(balanceObject);

        oneThread.setName("Tamara");
        twoThread.setName("Cameron");

        oneThread.start();
        twoThread.start();
//----------------------------------------------------------------------------------------------------------------
        //Test2.1 for withdrawing money
        WithdrawRunnable withdrawObject1 = new WithdrawRunnable(bankAccount, accountHolder1,10);
        WithdrawRunnable withdrawObject2 = new WithdrawRunnable(bankAccount, accountHolder2,20);

        Thread threeThread = new Thread(withdrawObject1);
        threeThread.setName("Angel");
        threeThread.start();
        Thread abcThread = new Thread(withdrawObject2);
        abcThread.setName("Ramsey");
        abcThread.start();


        Thread fourThread = new Thread(balanceObject);
        fourThread.setName("Ramsey");
        fourThread.start();
//----------------------------------------------------------------------------------------------------------------
       /* //Test2.2 for depositing money
        DepositRunnable depositObject = new DepositRunnable(bankAccount, 20);

        Thread fiveThread = new Thread(depositObject);
        fiveThread.setName("Cameron");
        fiveThread.start();

        Thread sixThread = new Thread(balanceObject);
        sixThread.setName("Ramsey v2");
        sixThread.start();
//----------------------------------------------------------------------------------------------------------------
        //Test3
        AccountHolder accountHolder3 = new AccountHolder(1,"Connor", bankAccount);
        AccountHolder accountHolder4 = new AccountHolder(2,"Alexandro", bankAccount);

        Thread threadSeven = new Thread(withdrawObject);
        threadSeven.setName("Connor");
        threadSeven.start();

        Thread threadEight = new Thread(depositObject);
        threadEight.setName("Connor");
        threadEight.start();

        Thread threadNine = new Thread(balanceObject);
        threadNine.setName("Connor");
        threadNine.start();

        Thread threadTen = new Thread(withdrawObject);
        threadTen.setName("Alexandro");
        threadTen.start();

        Thread threadEleven = new Thread(depositObject);
        threadEleven.setName("Alexandro");
        threadEleven.start();

        Thread threadTwelve = new Thread(balanceObject);
        threadTwelve.setName("Alexandro");
        threadTwelve.start();
//----------------------------------------------------------------------------------------------------------------
        //Test4  */

        




    }

}
