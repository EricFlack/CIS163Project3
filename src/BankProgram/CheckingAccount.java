package BankProgram;

import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by flackeri on 10/23/15.
 */
public class CheckingAccount extends Account{

    private static final long serialVersionUID = 1L;

    private double monthlyFee;

    public CheckingAccount(int number, String owner, GregorianCalendar dateOpened, double balance, double monthlyFee) {
        super(number, owner, dateOpened, balance);
        this.monthlyFee = monthlyFee;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof CheckingAccount) {
            CheckingAccount otherChecking = (CheckingAccount) other;
            if (otherChecking.getNumber() == this.getNumber())
                if (Objects.equals(otherChecking.getOwner(), this.getOwner()))
                    if (otherChecking.getDateOpened() == this.getDateOpened())
                        if (otherChecking.getBalance() == this.getBalance())
                            if (otherChecking.getMonthlyFee() == this.getMonthlyFee()) return true;
            return false;
        }
        else return false;
    }

    @Override
    public String toString(){
        String checking;
        checking = "" + this.getNumber() + "\t" + this.getOwner() + "\t" + this.getDateOpened() + "\t" +
                this.getBalance() + "\t Monthly Fee:" + this.getMonthlyFee();
        return checking;
    }
}
