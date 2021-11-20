public class WithdrawRunnable implements Runnable{
    BankAccount bankAccount;
    AccountHolder accountHolder;
    private static final int DELAY = 1;

    public double amount;

    public WithdrawRunnable(BankAccount bankAccount) {
      accountHolder = new AccountHolder(0, "", bankAccount);
    }

   /* public WithdrawRunnable(BankAccount bankAccount ,double amount) {
        this.bankAccount = bankAccount;

        this.amount = amount;
    }*/

    @Override
    public void run() {
        try {
            bankAccount.withdraw(amount);
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
