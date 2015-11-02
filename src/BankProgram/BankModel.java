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
        fireTableRowsInserted(0, accounts.size() - 1);
        /*
        int addLoc = accounts.indexOf(account);
        this.accounts.add(addLoc, account);
        fireTableRowsInserted(addLoc, addLoc);
        */
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

    public Account FindByBalance(double num){
        for (Account a : this.accounts)
        {
            if (a.getBalance() == num)
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

        if (out != null) {
            out.print("Type \t");
            out.print(colNames[0] + "\t");
            out.print(colNames[1] + "\t");
            out.print(colNames[2] + "\t");
            out.print(colNames[3] + "\t");
            out.println("Info");

            for (Account account : accounts) {
                if (account instanceof CheckingAccount) {
                    out.print("Checking");
                    out.println(account.toString());
                } else {
                    out.print("Savings");
                    out.println(account.toString());
                }
            }
            out.close();
        }
    }

    public void loadText(String filename){
        accounts.clear();
        try{
            // open the data file
            Scanner fileReader = new Scanner(new File(filename));

            while(fileReader.hasNext()){
                GregorianCalendar date = new GregorianCalendar(Calendar.JANUARY, 1, 1970);
                String type = fileReader.next();
                int number = fileReader.nextInt();
                String owner = fileReader.next();
                try{
                    String dateString = fileReader.next();
                    DateFormat format = new SimpleDateFormat("MM '/' DD '/' yyyy");
                    Date parsed = format.parse(dateString);
                    date.setTime(parsed);
                } catch(ParseException e){
                    System.err.println("ParseException");
                }
                double balance = fileReader.nextDouble();
                if(type.equals("Checking")) {
                    double monthlyFee = fileReader.nextDouble();
                    CheckingAccount newAccount = new CheckingAccount(number, owner, date, balance, monthlyFee);
                    accounts.add(newAccount);
                }
                else{
                    double minBalance = fileReader.nextDouble();
                    double interestRate = fileReader.nextDouble();
                    SavingsAccount newAccount = new SavingsAccount(number, owner, date,
                            balance, minBalance, interestRate);
                    accounts.add(newAccount);
                }
            }

            fileReader.close();
        }

        // could not find file
        catch(FileNotFoundException error) {
            System.out.println("File not found ");
        }
    }
    
    public void saveBinary(String fileName) {

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

    public void loadBinary(String fileName) throws IOException{

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

        PrintWriter out;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            out.write(s);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i  < accounts.size(); i++) {
            Account a = accounts.get(i);
            sb.append("<?xml version=1.0 encoding=utf-8?>\n");
            sb.append("<Account>\n");
            sb.append("  <Number>" + a.getNumber() + "</Number>\n");
            sb.append("  <Owner>" + a.getOwner() + "</Owner>\n");
            sb.append("  <DateOpened>" + a.getDateOpened() + "</DateOpened>\n");
            sb.append("  <Balance>" + a.getBalance() + "</Balance>\n");

            if (a instanceof SavingsAccount) {
                sb.append("  <SavingsAccount>\n");
                sb.append("    <miniBalance>" + ((SavingsAccount) a).getMinBalance() + "</iniBalance>\n");
                sb.append("    <interestRate>" + ((SavingsAccount) a).getInterestRate() + "0</interestRate>\n");
                sb.append("  </SavingsAccount>\n");
                sb.append("</Account>\n");
            } else if(a instanceof CheckingAccount) {
                sb.append("  <CheckingAccount>\n");
                sb.append("    <monthlyFee>" + ((CheckingAccount) a).getMonthlyFee() + "</monthlyFee>\n");
                sb.append("  </CheckingAccount>\n");
                sb.append("</Account>\n");
            }
        }
    }

    public void loadXML(String fileName){
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            StringBuilder sbFile = new StringBuilder();

            String line = br.readLine();

            while (line != null) {
                sbFile.append(line);

                line = br.readLine();
            }

            String readFile = sbFile.toString();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
