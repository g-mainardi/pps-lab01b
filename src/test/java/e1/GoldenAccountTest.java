package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static e1.CoreBankAccount.INITIAL_BALANCE;
import static e1.GoldenBankAccount.FEE_AMOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GoldenAccountTest extends BankAccountAbstractTest {

    public static final int WITHDRAW_TOO_HIGH_AMOUNT = 1501;

    @BeforeEach
    void init() {
        this.account = new GoldenBankAccount(new CoreBankAccount());
    }

    @Test
    @Override
    public void testCanWithdraw() {
        var expectedAmount = INITIAL_BALANCE;
        this.account.deposit(DEPOSIT_AMOUNT);
        expectedAmount += DEPOSIT_AMOUNT;
        this.account.withdraw(WITHDRAW_AMOUNT);
        expectedAmount -= WITHDRAW_AMOUNT;
        assertEquals(expectedAmount, this.account.getBalance());
    }

    @Test
    @Override
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(WITHDRAW_TOO_HIGH_AMOUNT));
    }

}
