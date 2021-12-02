public class Transaction extends Thread {

    public static enum Type {
        DEPOSIT(1), WITHDRAW(2), BALANCE(3), TRANSFERIN(4), TRANSFEROUT(5), EDIT(6);

        private Type(int value) {
        }
    }
    ;

    private Type transactionType;
    private BankAccount bankAccount;
    private Employee employee;
    private double amount;
    private AccountHolder holder;
    private int id;
    private String newAccountNumber;
    private String newAccountType;


    public Transaction(Type transactionType, BankAccount bankAccount, double amount, Employee employee) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.employee = employee;
    }

    public Transaction(Type transactionType, BankAccount bankAccount, double amount, AccountHolder holder) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.holder = holder;
    }

    public Transaction(Type transactionType, BankAccount bankAccount) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
    }

    public Transaction(Type transactionType, BankAccount bankAccount, int id, String newAccountNumber, String newAccountType, Employee employee) {
        this.transactionType = transactionType;
        this.bankAccount = bankAccount;
        this.id = id;
        this.newAccountNumber = newAccountNumber;
        this.newAccountType = newAccountType;
        this.employee = employee;
    }

    public void run() {
        switch (this.transactionType) {
            case DEPOSIT:
                this.bankAccount.deposit(this.amount);
                break;

            case WITHDRAW:
                try {
                    this.bankAccount.withdraw(this.amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case BALANCE:
                this.bankAccount.getBalance();
                break;
            case TRANSFERIN:
                this.employee.transferMoneyIn(bankAccount, amount);
                break;

            case TRANSFEROUT:
                this.employee.transferMoneyOut(bankAccount, amount);
                break;

            case EDIT:
                this.employee.editAccount(id, newAccountNumber, newAccountType);
                break;

            default:
                System.out.println("The transaction is not valid");

        }
    }
}
