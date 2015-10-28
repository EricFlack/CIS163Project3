package BankProgram;

import java.util.GregorianCalendar;

/**
 * Created by flackeri on 10/23/15.
 */
public class SavingsAccount extends Account {

    private static final long serialVersionUID = 1L;

    private double minBalance;

    private double interestRate;

    public SavingsAccount (int number, String owner, GregorianCalendar dateOpened, double balance, double minBalance,
                           double interestRate) {
        super(number, owner, dateOpened, balance);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
