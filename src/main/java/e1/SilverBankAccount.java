package e1;

public class SilverBankAccount extends AbstractBankAccount {

    public static final int FEE_AMOUNT = 1;

    public SilverBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    public int getFee(final int amount) {
        return FEE_AMOUNT;
    }

    public boolean canWithdraw(int amount) {
        return this.getBalance() >= amount;
    }
}
