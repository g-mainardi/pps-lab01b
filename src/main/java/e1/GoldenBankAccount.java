package e1;

public class GoldenBankAccount extends AbstractBankAccount {

    public static final int FEE_AMOUNT = 0;

    public GoldenBankAccount(BankAccount bankAccount) {
        super(bankAccount);
    }

    @Override
    protected int getFee(final int amount) {
        return FEE_AMOUNT;
    }

    @Override
    protected boolean canWithdraw(int amount) {
        return this.base.getBalance() >= (amount - 500);
    }
}
