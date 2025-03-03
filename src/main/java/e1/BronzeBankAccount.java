package e1;

public class BronzeBankAccount extends AbstractBankAccount {

    public static final int FREE_WITHDRAW_LIMIT = 100;
    public static final int NO_FEE = 0;
    public static final int OVER_LIMIT_FEE = 1;

    public BronzeBankAccount(BankAccount bankAccount) {
        super(bankAccount);
    }

    @Override
    protected int getFee(final int amount) {
        return amount < FREE_WITHDRAW_LIMIT ? NO_FEE : OVER_LIMIT_FEE;
    }

    @Override
    protected boolean canWithdraw(int amount) {
        return this.getBalance() >= amount;
    }
}
