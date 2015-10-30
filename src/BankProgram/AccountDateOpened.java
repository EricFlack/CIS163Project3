package BankProgram;

import java.util.Comparator;

/**
 * Created by flackeri on 10/30/15.
 */
public class AccountDateOpened implements Comparable<Account>, Comparator<Account> {
    public int compare(Account account1, Account account2) {
        return account1.getDateOpened().compareTo(account2.getDateOpened());
    }

    @Override
    public int compareTo(Account o) {
        return 0;
    }
}
