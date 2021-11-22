public class Transaction extends Thread{

    public static enum Type{
        DEPOSIT(1), WITHDRAW(2), BALANCE(3), TRANSFERIN(4), TRANSFEROUT(5);

        private Type(int value){
        }
    };

    private Type transactionType;
    private BankAccount bankAccount;
    private Employee employee;
    private double amount;
    private AccountHolder holder;



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

    public void run(){
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

            default:
                System.out.println("The transaction is not valid");

        }
    }
}
