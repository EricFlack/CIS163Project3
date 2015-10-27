package BankProgram;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.lang.Comparable;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

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

    }
    public Account findAccount(int acctNumber){
        sortAccountNumber(accounts);
        int i = search(accounts, 0, accounts.size() - 1, acctNumber);
        if(i != -1)
            return accounts.get(i);
        else
            return accounts.get(0); //
    }

    private <T extends Comparable<T>> int search(ArrayList<Account> data, int min, int max, int number){
        int location = -1;
        int mid = (min + max) / 2;

        if(compareTo((int) getValueAt(mid, 0), number) == 0)
            location = mid;
        else if(compareTo((int) getValueAt(mid, 0), number) > 0){
            if(min <= mid - 1)
                location = search(data, min, mid - 1, number);
        }
        else if(mid + 1 <= max)
            location = search(data, mid + 1, max, number);

        return location;
    }

    private int compareTo(int comparison, int other){
        if(comparison > other)
            return 1;
        else if(comparison == other)
            return 0;
        else
            return -1;
    }

    public void sortAccountNumber(ArrayList<Account> data){
        int position, scan;

        for(position = data.size() - 1; position >= 0; position++){
            for(scan = 0; scan <= position - 1; scan++){
                if(compareTo((int) getValueAt(scan, 0), (int) getValueAt(scan + 1, 0)) > 0)
                    swap(data, scan, scan + 1);
            }
        }
    }

    private void swap(ArrayList<Account> data, int swap1, int swap2){
        Account temp = data.get(swap1);
        data.set(swap1, data.get(swap2));
        data.set(swap2, temp);
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

        out.print(colNames[0]);
        out.print(colNames[1]);
        out.print(colNames[2]);
        out.println(colNames[3]);

        for(int i = 0; i <accounts.size(); i++) {
            out.print(getValueAt(i, 0));
            out.print(getValueAt(i, 1));
            out.print(getValueAt(i, 2));
            out.println(getValueAt(i, 3));
        }
        out.close();
    }

    public void loadText(String filename){

        try{
            // open the data file
            Scanner fileReader = new Scanner(new File(filename));

            int i = 0;
            while(fileReader.hasNext()){
                if(i > 4){
                    if(i % 4 == 0){
                        //accounts.add();
                        //create add function
                    }
                }
                i++;
            }

            fileReader.close();
        }

        // could not find file
        catch(FileNotFoundException error) {
            System.out.println("File not found ");
        }
    }
    
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
}
