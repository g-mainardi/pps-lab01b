package e1;

public class SilverBankAccount implements BankAccount{

    public static final int FEE_AMOUNT = 1;
    private BankAccount base;

    public SilverBankAccount(BankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount){
            throw new IllegalStateException();
        }
        base.withdraw(amount + FEE_AMOUNT);
    }
}
