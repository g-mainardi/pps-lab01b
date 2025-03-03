package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static e1.CoreBankAccount.INITIAL_BALANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public abstract class BankAccountAbstractTest {
    public static final int DEPOSIT_AMOUNT = 1000;
    public static final int WITHDRAW_AMOUNT = 200;
    BankAccount account;

    @Test
    public void testInitiallyEmpty() {
        assertEquals(INITIAL_BALANCE, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(DEPOSIT_AMOUNT);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    public abstract void testCanWithdraw();

    @Test
    public abstract void testCannotWithdrawMoreThanAvailable();
}
