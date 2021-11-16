public class BalanceCheckingRunnable implements Runnable {

    BankAccount bankAccount;
    private static final int DELAY = 1;

    public BalanceCheckingRunnable(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        try {
            bankAccount.getBalance();
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
