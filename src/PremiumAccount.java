public class PremiumAccount extends BankAccount {
    public PremiumAccount(int id, String accountType, AccountHolder accountHolder, String accountNumber) {
        super(id, accountType, accountHolder, accountNumber);
    }

    private void payMonthlyFee() {
        this.setBalance(this.getBalance() - 20);
    }
}
