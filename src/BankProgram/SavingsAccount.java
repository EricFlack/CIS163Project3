package BankProgram;

import java.util.GregorianCalendar;
import java.util.Objects;

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

    @Override
    public boolean equals(Object other){
        if (other instanceof SavingsAccount) {
            SavingsAccount otherSavings = (SavingsAccount) other;
            if (otherSavings.getNumber() == this.getNumber())
                if (Objects.equals(otherSavings.getOwner(), this.getOwner()))
                    if (otherSavings.getDateOpened() == this.getDateOpened())
                        if (otherSavings.getBalance() == this.getBalance())
                            if (otherSavings.getMinBalance() == this.getMinBalance())
                                if (otherSavings.getInterestRate() == this.getInterestRate()) return true;
            return false;
        }
        else return false;
    }

    @Override
    public String toString(){
        String savings;
        savings = "" + this.getNumber() + "\t" + this.getOwner() + "\t" + this.getDateOpened() + "\t" +
                this.getBalance() + "\t Minimum Balance:" + this.getMinBalance() + " Interest Rate:" +
                this.getInterestRate();
        return savings;
    }
}
