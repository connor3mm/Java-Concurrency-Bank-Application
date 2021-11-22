public class EmployeeTransfrerRunnable implements Runnable{

    BankAccount sender;
    BankAccount receiver;
    Employee employee;
    private double amount;
    private static final int DELAY = 1;

    public EmployeeTransfrerRunnable(BankAccount sender, BankAccount receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            employee.transferMoneyIn(receiver, amount);
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
