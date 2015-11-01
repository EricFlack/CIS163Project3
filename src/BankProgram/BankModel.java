package BankProgram;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by flackeri on 10/23/15.
 */
public class BankModel extends AbstractTableModel {

    /** array list to hold account objects */
    private ArrayList<Account> accounts;

    /** array of strings for table column names */
    private String[] colNames;

    public BankModel() {
        accounts = new ArrayList<Account>();
        colNames = new String[] {"Number", "Date Opened", "Account Owner", "Current Balance"};
    }

    public Object getValueAt(int rowNum, int colNum) {
        Account a = accounts.get(rowNum);

        switch (colNum) {
            case 0:
                return a.getNumber();
            case 1:
                return a.getDateOpened();
            case 2:
                return a.getOwner();
            case 3:
                return a.getBalance();
            default:
                return null;
        }
    }

    public int getRowCount() {
        return accounts.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int num) {
        return colNames[num];
    }

    public ArrayList<Account> getAccts() {
        return accounts;
    }

    public void setAccts(ArrayList<Account> accts) {
        this.accounts = accts;
    }

    public void add(Account account){
        this.accounts.add(account);
        fireTableRowsInserted(0, accounts.size());
    }

    public void remove(Account account){
        int removeLoc = accounts.indexOf(account);
        this.accounts.remove(removeLoc);
        fireTableRowsDeleted(removeLoc, removeLoc);
    }

    public void update(Account account, int location){
        this.accounts.set(location, account);
        fireTableRowsUpdated(location, location);
    }
    
    public Account FindByOwner(String name){
    for (Account a : this.accounts)
    {
        if (a.getOwner().equals(name))
        {
            return a;
        }
    }
    return null;
    }

public Account FindByNumber(int num){
    for (Account a : this.accounts)
    {
        if (a.getNumber() == num)
        {
            return a;
        }
    }
    return null;
    }

public Account FindByDateOpened(GregorianCalendar date){
    for (Account a : this.accounts)
    {
        if (a.getDateOpened().equals(date))
        {
            return a;
        }
    }
    return null;
    }

//    public Account findAccount(int acctNumber){
//        sortNumber();
//        int i = search(accounts, 0, accounts.size() - 1, acctNumber);
//        if(i != -1)
//            return accounts.get(i);
//        else
//            return accounts.get(0);
//    }
//
//    private <T extends Comparable<T>> int search(ArrayList<Account> data, int min, int max, int number){
//        int location = -1;
//        int mid = (min + max) / 2;
//
//        if(compareTo((int) getValueAt(mid, 0), number) == 0)
//            location = mid;
//        else if(compareTo((int) getValueAt(mid, 0), number) > 0){
//            if(min <= mid - 1)
//                location = search(data, min, mid - 1, number);
//        }
//        else if(mid + 1 <= max)
//            location = search(data, mid + 1, max, number);
//
//        return location;
//    }

     public void sortNumber() {
        if (accounts.size() > 1) {
            Collections.sort(accounts, new AccountNumber());
            this.fireTableRowsUpdated(0, accounts.size() - 1);
        }

    }

    public void sortOwner() {
        if (accounts.size() > 1) {
            Collections.sort(accounts, new AccountOwner());
            this.fireTableRowsUpdated(0, accounts.size() - 1);
        }
    }

    public void sortDateOpened() {
        if (accounts.size() > 1) {
            Collections.sort(accounts, new AccountDateOpened());
            this.fireTableRowsUpdated(0, accounts.size() - 1);
        }
    }

    public void saveText(String filename){
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter
                    (new FileWriter(filename)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        out.print(colNames[0] + "\t");
        out.print(colNames[1] + "\t");
        out.print(colNames[2] + "\t");
        out.println(colNames[3]);

        for(int i = 0; i <accounts.size(); i++) {
            out.println(accounts.get(i).toString());
        }
        out.close();
    }

//    public void loadText(String filename){
//        accounts.clear();
//        try{
//            // open the data file
//            Scanner fileReader = new Scanner(new File(filename));
//
//            while(fileReader.hasNext()){
//                int number = fileReader.nextInt();
//                String owner = fileReader.nextLine();
//                try{
//                    String dateString = fileReader.nextLine();
//                    DateFormat format = new SimpleDateFormat("MM '/' DD '/' yyyy");
//                    Date parsed = format.parse(dateString);
//                    GregorianCalendar date = new GregorianCalendar();
//                    date.setTime(parsed);
//                } catch(ParseException e){
//                    System.err.println("ParseException");
//                }
//                double balance = fileReader.nextDouble();
//                Account newAccount = Account(number, owner, date, balance);
//                accounts.add(newAccount);
//            }
//
//            fileReader.close();
//        }
//
//        // could not find file
//        catch(FileNotFoundException error) {
//            System.out.println("File not found ");
//        }
//    }
    
     public void saveBinary(File fileName) {

        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            Account[] accounts1 = accounts.toArray(new Account[accounts.size()]);

            out.writeObject(accounts1);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBinary(File fileName) throws IOException{

        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);

        try {
            Account [] accounts1  = (Account []) in.readObject();

            accounts.clear();
            accounts.addAll(Arrays.asList(accounts1));
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        in.close();
    }
    
     public void saveXML(String fileName){
        StringBuilder sb = new StringBuilder();
        String s = sb.toString();

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            out.write(s);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        sb.append("<?xml version=1.0 encoding=utf-8?>\n");
        sb.append("\t<Account>\n");
        sb.append("\t<Number>" + accounts.getClass() + "<Number>\n" );
        sb.append("<Owner>" + accounts.getClass() + "<Owner>\n" );
        sb.append("<DateOpened>" + accounts.getClass() + "<DateOpened>\n" );
        sb.append("<Balance>" + accounts.getClass() + "<Balance>\n" );

        
        if(accounts instanceof SavingsAccount){
            sb.append("<miniBalance>" + accounts.getClass() + "<miniBalance>\n" );
            sb.append("<interestRate>" + accounts.getClass() + "<interestRate>\n" );
            sb.append("<Account>\n");
        }
        else {
            sb.append("<monthlyFee>" + accounts.getClass() + "<monthlyFee>\n");
            sb.append("<Account>\n");
        }
    }
}
