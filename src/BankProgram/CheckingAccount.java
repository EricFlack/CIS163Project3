package BankProgram;

import java.util.GregorianCalendar;
import java.io.Serializable;
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
}
