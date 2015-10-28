package BankProgram;

/**
 * Created by flackeri on 10/23/15.
 */
import java.io.Serializable;
import java.util.GregorianCalendar;
public abstract class Account implements Serializable{

    private static final long serialVersionUID = 1L;

    private int number;

    private String owner;

    private GregorianCalendar dateOpened;

    private double balance;

    public Account(int number, String owner, GregorianCalendar dateOpened, double balance) {
        this.number = number;
        this.owner = owner;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public GregorianCalendar getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(GregorianCalendar dateOpened) {
        this.dateOpened = dateOpened;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
