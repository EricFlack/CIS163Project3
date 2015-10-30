package BankProgram;

import java.util.Comparator;

/**
 * Created by flackeri on 10/30/15.
 */
public class AccountNumber implements Comparable<Account>, Comparator<Account> {
    public int compare(Account account1, Account account2) {
        return account1.getNumber() - account2.getNumber();
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
