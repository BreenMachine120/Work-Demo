/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.Random;
/**
 *
 * @author mbreen
 * @author nyoung
 */
public class Account {
    // ***Variables***
    
    private String lastname, firstname, dob, ssn, accountNumber;
    private char sex, accountType;
    private int person;
    private double balance;
    private final double interest = 1.01;
    
   
    private int countSSN =0;//used to keep track of how many elements are in array
    private int cAccountNumber=0;//used to keep track of how many elements are in array
    private int sArrayLength = 15;//ssn array length
    private int aArrayLength=15;//account number array length
    private String[] ssnList = new String [sArrayLength];
    private String[] accountNumberList = new String[aArrayLength];
    
    private static final Random generator = new Random();
    
    
    // Constructor Methods
    
    // Constructor with initial balance
    public Account(String lastname, String firstname, char sex, String dob, char accountType, int person, double balance) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.sex = sex;
        this.dob = dob;
        this.accountType = accountType;
        this.person = person;
        this.balance = balance;
        ssn = generateSSN();
        accountNumber = generateAccountNumber();
    }
    
    // Constructor without initial balance
    public Account(String lastname, String firstname, char sex, String dob, char accountType, int person) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.sex = sex;
        this.dob = dob;
        this.accountType = accountType;
        this.person = person;
        balance = 0;
        ssn = generateSSN();
        accountNumber = generateAccountNumber();
    }
    
    
    // Private Methods
    
    // Generate random SSN
    private String generateSSN() {
        boolean repeat;
        if(sArrayLength == countSSN ){
            sArrayLength= sArrayLength*2;
             String [] copy = new String [sArrayLength];
    
                        for(int i = 0; i< countSSN; i++){
                            copy[i]= ssnList [i];}
                        ssnList = null;
                        
                        ssnList=copy;
        }
        do {
            repeat = false;
            // generate number
            ssn = "";
            for (int i = 0; i < 9; i++) {
                if (i == 3 || i == 5) {
                    ssn += "-";
                }
                ssn += generator.nextInt(10);
            }
            // check list
            if (ssnList[0]!= null) {
                for (int i = 0; i < sArrayLength; i++) {
                    if (!ssn.equals(ssnList[i])) {
                        ssnList[i]=ssn;
                        countSSN++;
                     }
                    else{
                    repeat = true;}
                }
            }
            else{
                ssnList[0]=ssn;
                countSSN++;
            }
        } while (repeat);
        
        return ssn;
    }
    
    // Generate random account number
    private String generateAccountNumber() {
        boolean repeat;
        if(aArrayLength == cAccountNumber ){
            aArrayLength=aArrayLength*2;
             String [] copy = new String [aArrayLength];
    
                        for(int i = 0; i< cAccountNumber; i++){
                            copy[i]= accountNumberList [i];}
                        accountNumberList = null;
                        
                        accountNumberList=copy;
        }
        do {
            repeat = false;
            // generate number
            accountNumber = "";
            for (int i = 0; i < 5; i++) {
                accountNumber += generator.nextInt(10);
            }
            if (accountNumberList[0]!=null) {
                for (int i = 0; i < aArrayLength; i++) {
                    if (accountNumber.equals(accountNumberList[i])) {
                        repeat = true;
                    }
                    else{
                        accountNumberList[i+1]=accountNumber;
                        cAccountNumber++;
                    }
                }
            }
            else{
            accountNumberList[0]=accountNumber;
            cAccountNumber++;
            }
        } while (repeat);
        
        return accountNumber;
    }
    
    
    // Public Methods
    
    // Make a deposit
    public double deposit(double depositAmount) {
        balance += depositAmount;
        System.out.println("Deposit Successful");
        return balance;
    }
    
    // Add interest
    public double addInterest() {
        balance *= interest;
        return balance;
    }
    
    // Make a withdrawal
    public double withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > balance) {
            System.out.println("Error: Insufficient Balance");
            return balance;
        }
        else {
            balance -= withdrawalAmount;
            System.out.println("Withdrawal Successful");
            return balance;
        }
    }
    
    // Display Account information
    public void displayInfo() {
        System.out.println("Account number: " + accountNumber);
        
        String displayAccountType;
        switch (accountType) {
            case 'c':
                displayAccountType = "Checking";
                break;
            case 's':
                displayAccountType = "Savings";
                break;
            default:
                throw new IllegalArgumentException("Error: Unknown Account Type");
        }
        System.out.println("Account type: " + displayAccountType);
        
        System.out.println("Name: " + lastname + ", " + firstname);
        System.out.println("Sex: " + sex);
        System.out.println("Date of birth: " + dob);
        
        String displaySSN = ssn.substring(ssn.length()-4, ssn.length());
        System.out.println("Social Security Number: xxx-xx-" + displaySSN);
        
        String displayPerson;
        switch (person) {
            case 1:
                displayPerson = "Student";
                break;
            case 2:
                displayPerson = "Staff";
                break;
            case 3: 
                displayPerson = "Faculty";
                break;
            default:
                throw new IllegalArgumentException("Error: Unknown Person");
        }
        System.out.println("Person: " + person);
        
        System.out.println("Balance: $" + balance);
        
        System.out.println();
    }
    
    
    // Accessor Methods
    
    // lastname Accessor
    public String getLastname() {
        return lastname;
    }
    
    // firstname Accessor
    public String getFirstname() {
        return firstname;
    }
    
    // sex Accessor
    public char getSex() {
        return sex;
    }
    
    // dob Accessor
    public String getDob() {
        return dob;
    }
    
    // ssn Accessor
    public String getSSN() {
        return ssn;
    }
    
    // accountNumber Accessor
    public String getAccountNumber() {
        return accountNumber;
    }
    
    // accountType Accessor
    public char getAccountType() {
        return accountType;
    }
    
    // person Accessor
    public int getPerson() {
        return person;
    }
    
    // balance Accessor
    public double getBalance() {
        return balance;
    }
    
    
    // ***Mutator Methods*** - truncate doubles to 2 digits
    
    // lastname Mutator
    public String setLastname(String newLastname) {
        lastname = newLastname;
        return lastname;
    }
    
    // firstname Mutator
    public String setFirstname(String newFirstname) {
        firstname = newFirstname;
        return firstname;
    }
    
    // sex Mutator
    public char setSex(char newSex) {
        sex = newSex;
        return sex;
    }
    
    // dob Mutator
    public String setDob(String newDob) {
        dob = newDob;
        return dob;
    }
    
    // ssn Mutator
    public String setSSN(String newSSN) {
        ssn = newSSN;
        return ssn;
    }
    
    // accountNumber Mutator
    public String setAccountNumber(String newAccountNumber) {
        accountNumber = newAccountNumber;
        return accountNumber;
    }
    
    // accountType Mutator
    public char setAccountType(char newAccountType) {
        accountType = newAccountType;
        return accountType;
    }
    
    // person Mutator
    public int setPerson(int newPerson) {
        person = newPerson;
        return person;
    }
    
    // balance Mutator
    public double setBalance(double newBalance) {
        balance = newBalance;
        return balance;
    }
}
