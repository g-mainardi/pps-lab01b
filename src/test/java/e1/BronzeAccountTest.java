package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static e1.BronzeBankAccount.NO_FEE;
import static e1.BronzeBankAccount.OVER_LIMIT_FEE;
import static e1.CoreBankAccount.INITIAL_BALANCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BronzeAccountTest extends BankAccountAbstractTest {

    public static final int WITHDRAW_TOO_HIGH_AMOUNT = 1200;
    private static final int SECOND_WITHDRAW_AMOUNT = 50;

    @BeforeEach
    void init() {
        this.account = new BronzeBankAccount(new CoreBankAccount());
    }

    @Test
    @Override
    public void testCanWithdraw() {
        var expectedAmount = INITIAL_BALANCE;
        this.account.deposit(DEPOSIT_AMOUNT);
        expectedAmount += DEPOSIT_AMOUNT;
        this.account.withdraw(WITHDRAW_AMOUNT);
        expectedAmount -= (WITHDRAW_AMOUNT + OVER_LIMIT_FEE);
        assertEquals(expectedAmount, this.account.getBalance());
        this.account.withdraw(SECOND_WITHDRAW_AMOUNT);
        expectedAmount -= (SECOND_WITHDRAW_AMOUNT + NO_FEE);
        assertEquals(expectedAmount, this.account.getBalance());
    }

    @Test
    @Override
    public void testCannotWithdrawMoreThanAvailable(){
        this.account.deposit(DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(WITHDRAW_TOO_HIGH_AMOUNT));
    }

}
