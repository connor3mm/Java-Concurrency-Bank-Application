public class DepositRunnable implements Runnable{
    BankAccount bankAccount;
    private static final int DELAY = 1;

    public double amount;
    public DepositRunnable(BankAccount bankAccount, double amount) {
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            bankAccount.deposit(amount);
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
