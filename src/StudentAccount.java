public class StudentAccount extends BankAccount{
    public StudentAccount(int id, String accountType, String accountHolder, String accountNumber) {
        super(id, accountType, accountHolder, accountNumber);
    }

    public void addInterest() {
        this.setBalance(this.getBalance() + ((2 * 100) / this.getBalance()));
    }
}
