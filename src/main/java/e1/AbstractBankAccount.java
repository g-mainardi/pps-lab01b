package e1;

public abstract class AbstractBankAccount implements BankAccount {
    protected BankAccount base;

    public AbstractBankAccount(BankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (!canWithdraw(amount)) {
            throw new IllegalStateException();
        }
        base.withdraw(amount + getFee(amount));
    }

    protected abstract int getFee(int amount);

    protected abstract boolean canWithdraw(int amount);

}
