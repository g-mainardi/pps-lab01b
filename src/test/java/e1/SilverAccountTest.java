package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static e1.CoreBankAccount.INITIAL_BALANCE;
import static e1.SilverBankAccount.FEE_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverAccountTest extends BankAccountAbstractTest {
    public static final int WITHDRAW_TOO_HIGH_AMOUNT = 1200;

    @BeforeEach
    void init() {
        this.account = new SilverBankAccount(new CoreBankAccount());
    }

    @Test
    @Override
    public void testCanWithdraw() {
        var expectedAmount = INITIAL_BALANCE;
        this.account.deposit(DEPOSIT_AMOUNT);
        expectedAmount += DEPOSIT_AMOUNT;
        this.account.withdraw(WITHDRAW_AMOUNT);
        expectedAmount -= (WITHDRAW_AMOUNT + FEE_AMOUNT);
        assertEquals(expectedAmount, this.account.getBalance());
    }

    @Test
    @Override
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(WITHDRAW_TOO_HIGH_AMOUNT));
    }

}
