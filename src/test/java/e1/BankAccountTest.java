package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static e1.CoreBankAccount.INITIAL_BALANCE;
import static e1.SilverBankAccount.FEE_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    public static final int DEPOSIT_AMOUNT = 1000;
    public static final int WITHDRAW_AMOUNT = 200;
    public static final int WITHDRAW_TOO_HIGH_AMOUNT = 1200;
    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = new SilverBankAccount(new CoreBankAccount());
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(DEPOSIT_AMOUNT);
        assertEquals(INITIAL_BALANCE + DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        var expectedAmount = INITIAL_BALANCE;
        this.account.deposit(DEPOSIT_AMOUNT);
        expectedAmount += DEPOSIT_AMOUNT;
        this.account.withdraw(WITHDRAW_AMOUNT);
        expectedAmount -= (WITHDRAW_AMOUNT + FEE_AMOUNT);
        assertEquals(expectedAmount, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(WITHDRAW_TOO_HIGH_AMOUNT));
    }

}
