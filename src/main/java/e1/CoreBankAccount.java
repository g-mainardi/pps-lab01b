package e1;

class CoreBankAccount implements BankAccount{

    public static final int INITIAL_BALANCE = 0;
    private int balance = INITIAL_BALANCE;

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }
}
