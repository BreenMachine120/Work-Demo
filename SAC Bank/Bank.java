/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
import java.util.Scanner;
/**
 *
 * @author nyoung
 * @author mbreen
 */

public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        // used to count time it takes for searches
        long StartTime;
        long EndTime = 0;
        //compare linear and binary search
        long linearTime=0;
        
        
        int ArrayLength = 16; // used to easily double array if needed
        int countAccount; // used for cycling through the account during a for loop
        
        String lastname, firstname, dob; // used to create new accounts
        char sex, accountType;
        int person;
        double balance;
        
        String choiceMain, choiceSub;  // used to select from main menu and submenu options
        String errorMessage = "Invalid input; please choose from the menu options";
        
        System.out.println("\nWelcome to Bank of Saint Anselm! \nInitializing...");
        
        // Create Array to hold accounts
        Account [] accountList = new Account[ArrayLength];
        
        // Set up existing accounts and add them to the array
        accountList[0] = new Account("Smith","James",'m',"03/7/2001",'s',1,100.30);
        accountList[1] = new Account("Hill","Janet",'f',"08/01/1997",'c',1);
        accountList[2] = new Account("Jameson","Jacob",'m',"10/11/1986",'c',2,50000.99);
        accountList[3] = new Account("Carmine","Collette",'f',"07/28/1970",'s',2);
        accountList[4] = new Account("Williams","Tabitha",'f',"09/01/1995",'c',3,6000.60);
        accountList[5] = new Account("No","One",'m',"0",'c',1);
        accountList[6] = new Account ("Knows","How hard it is",'f',"1",'c',1);
        accountList[7] = new Account ("Blue","Eyes",'m',"2",'s',2,300);
        accountList[8] = new Account("Wake","Me",'m',"11/11/1986",'c',2,50600.99);
        accountList[9] = new Account("When","Sept",'f',"12/11/1986",'s',2,60000.99);
        accountList[10] = new Account("Ends","Booo",'m',"1/11/1986",'c',2,58000.99);
        accountList[11] = new Account("Cult","Personality",'f',"12/01/1995",'c',3,16000.60);
        accountList[12] = new Account("Rhodes","Alice",'f',"09/05/1995",'c',3,6780.60);
        accountList[13] = new Account("Shepard","Adam",'m',"09/07/1995",'s',3,16000.60);
        accountList[14] = new Account("Williams","Michelle",'f',"09/21/1995",'s',3,6000.60);
        countAccount = 15;
        
        while(true){
            // print main menu options
            System.out.print("\nMain Menu"
                    + "\n 1. Display information for all accounts"
                    + "\n 2. Display number of accounts"
                    + "\n 3. Open an account with initial deposit"
                    + "\n 4. Open an account without initial deposit"
                    + "\n 5. Add interest to all accounts"
                    + "\n 6. Display student accounts where balance is less than $100"
                    + "\n 7. Display employee accounts where balance is more than $5000"
                    + "\n 8. Search for an account by last name (linear search)"
                    + "\n 9. Display savings accounts sorted by account number (bubble sort)"
                    + "\n10. Display number of student and employee accounts"
                    + "\n11. Display accounts where balance is less than $50 and add fine of $5"
                    + "\n12. Display accounts sorted by last name (selection sort)"
                    + "\n13. Display accounts sorted by first name (insertion sort)"
                    + "\n14. Search for an account by first name (binary search)"
                    + "\n15. Exit"
                    + "\n    Your selection: ");
            choiceMain = sc.next();                 // input for switch statement
            System.out.println();                   // breaks up the information so it easier to read
            
            
            switch(choiceMain) {
                case "1": // show all accounts
                    for (int i = 0; i < countAccount; i++) {
                        accountList[i].displayInfo();
                    }
                    break;
                    
                case "2": // display how many accounts there are
                    do { // loop over submenu until user chooses to exit
                        System.out.print("\n1. Number of savings accounts "
                                + "\n2. Number of checking accounts "
                                + "\n3. Return to Main Menu "
                                + "\n   Your selection: ");
                        choiceSub = sc.next();
                        int countType = 0;

                        switch (choiceSub) {
                            case "1": // display number of savings accounts
                                for (int i = 0; i < countAccount; i++) {
                                    if (accountList[i].getAccountType() == 's') {
                                        countType++;
                                    }
                                }
                                System.out.println("\nNumber of savings accounts: " + countType);
                                break;

                            case "2": // display number of checking accounts
                                for (int i = 0; i < countAccount; i++) {
                                    if (accountList[i].getAccountType() == 'c') {
                                        countType++;
                                    }
                                }
                                System.out.println("\nNumber of checking accounts: " + countType);
                                break;

                            case "3": // return to main menu
                                break;

                            default: // error message
                                System.out.println(errorMessage);
                                break;

                        }
                    } while (!choiceSub.equals("3"));
                    
                    break;
                    
                case "3": // add a new account with initial deposit
                    System.out.print("Enter last name: ");
                    lastname = sc.next();
                    System.out.print("Enter first name: ");
                    firstname = sc.next();
                    System.out.print("Enter sex (1 character): ");
                    sex = sc.next().charAt(0);
                    System.out.print("Enter date of birth: ");
                    dob = sc.next();
                    System.out.print("Enter account type ('c' for checking or 's' for savings): ");
                    accountType = sc.next().charAt(0);
                    System.out.print("Enter person (1: student, 2: staff, 3: faculty): ");
                    person = sc.nextInt();
                    System.out.print("Enter initial balance: ");
                    balance = sc.nextDouble();
                    
                    Account accountDepositTrue = new Account(lastname, firstname, sex, dob, accountType, person, balance);
                    
                    // Double array size if necessary
                    if(countAccount == ArrayLength){
                        ArrayLength *= 2;
                        Account[] copy = new Account[ArrayLength];
                        System.arraycopy(accountList, 0, copy, 0, countAccount);
                        accountList = copy;
                    }
                    
                    // Add new account to array
                    accountList[countAccount] = accountDepositTrue;
                    System.out.println("\nAccount successfuly opened! \n");
                    accountList[countAccount].displayInfo();
                    countAccount++;
                    break;
                    
                case "4": // add a new account without initial deposit
                    System.out.print("Enter last name: ");
                    lastname = sc.next();
                    System.out.print("Enter first name: ");
                    firstname = sc.next();
                    System.out.print("Enter sex (1 character): ");
                    sex = sc.next().charAt(0);
                    System.out.print("Enter date of birth: ");
                    dob = sc.next();
                    System.out.print("Enter account type ('c' for checking or 's' for savings): ");
                    accountType = sc.next().charAt(0);
                    System.out.print("Enter person (1: student, 2: staff, 3: faculty): ");
                    person = sc.nextInt();
                    
                    Account accountDepositFalse = new Account(lastname, firstname, sex, dob, accountType, person); 
                    
                    // Double array size if necessary
                    if(countAccount == ArrayLength){
                        ArrayLength = ArrayLength *2;
                        Account[] copy = new Account[ArrayLength];
                        System.arraycopy(accountList, 0, copy, 0, countAccount);
                        accountList = copy;
                    }
                    
                    // Add account to array
                    accountList[countAccount] = accountDepositFalse;
                    System.out.println("\nAccount successfuly opened! \n");
                    accountList[countAccount].displayInfo();
                    countAccount++;
                    
                    break;
                    
                case "5": // adds interest to all the accounts
                    for (int i = 0; i < countAccount; i++) {
                        accountList[i].addInterest();
                    }
                    System.out.println("Interest successfully added");
                    break;
                    
                case "6": // displays all student accounts with less than $100
                    for (int i = 0; i < countAccount; i++) {
                        if (accountList[i].getPerson() == 1 && accountList[i].getBalance() < 100) {
                            accountList[i].displayInfo();
                        }
                    }
                    break;
                    
                case "7": // displays all employee accounts with more than $5000
                    for (int i = 0; i < countAccount; i++) {
                        if (accountList[i].getPerson() == 2 || accountList[i].getPerson() == 3) {
                            if (accountList[i].getBalance() > 5000) {
                                accountList[i].displayInfo();
                            }
                        }
                    }
                    break;
                    
                case "8": // linear search by last name and perform operations
                    System.out.print("Search by last name: ");
                    lastname = sc.next();
                    
                    boolean accountFound = false;
                    int accountIndex = 0; // carries index                    
                    String confirm; // used to make account is the right one
                    
                    StartTime = System.nanoTime();
                    
                    for (int i = 0; i < countAccount; i++) {
                        if (lastname.equals(accountList[i].getLastname())) {
                            EndTime = System.nanoTime();
                            System.out.println("\nPlease confirm account details: \n");
                            accountList[i].displayInfo();
                            System.out.print("Is this the correct account? (y/n) ");
                            confirm = sc.next();
                            if (confirm.equals("y") || confirm.equals("Y")) {
                                accountIndex = i;
                                accountFound = true;
                                break;
                            }
                        }
                    }
                    
                    if (accountFound){ // if account was found run submenu
                        linearTime= EndTime - StartTime;//Will be able to compare to binary time without searching for this 
                        System.out.println("Time taken (linear search): " + linearTime + " ns");
                        
                        do { // loop over submenu until user chooses to exit
                            System.out.print("\n1. Check balance"
                                    + "\n2. Withdraw money"
                                    + "\n3. Deposit money"
                                    + "\n4. Add interest"
                                    + "\n5. Close the account"
                                    + "\n6. Change last name"
                                    + "\n7. Return to Main Menu"
                                    + "\n   Your selection: ");
                            choiceSub = sc.next();
                            System.out.println();

                            switch (choiceSub) {
                                case "1": // check balance
                                    System.out.println("Balance: " + accountList[accountIndex].getBalance());
                                    break;

                                case "2": // withdraw money
                                    double withdrawalValue;
                                    System.out.print("Enter amount to withdraw: ");
                                    withdrawalValue = sc.nextDouble();
                                    accountList[accountIndex].withdrawal(withdrawalValue);
                                    break;

                                case "3": // deposit money
                                    double depositValue;
                                    System.out.print("Enter amount to deposit: ");
                                    depositValue = sc.nextDouble();
                                    accountList[accountIndex].deposit(depositValue);
                                    break;

                                case "4": // add interest
                                    accountList[accountIndex].addInterest();
                                    break;

                                case "5": //close account
                                    char closeAccount;
                                    System.out.print("Are you sure you want to close the account? (y/n): ");
                                    closeAccount = sc.next().charAt(0);
                                    if (closeAccount == 'y' || closeAccount == 'Y') {
                                        
                                        Account ClosedAccount = accountList[accountIndex];
                                        int y = accountIndex + 1; // used to keep outbounds exception being thrown and closes account
                                        for (int j = accountIndex; j < countAccount; j++) { 
                                            if(y < countAccount) {
                                                accountList[j] = accountList[(y)];                                                
                                                y++;
                                            }
                                            else {
                                                accountList[j] = null;
                                            }
                                        }
                                        
                                        ClosedAccount = null;
                                        System.out.println("Account closed");                                      
                                        countAccount--;
                                    }
                                    choiceSub = "6";
                                    break;
                                    
                                case "6": // change last name
                                    System.out.print("Enter new last name: ");
                                    String newName = sc.next();
                                    accountList[accountIndex].setLastname(newName);
                                    System.out.println("\nName changed\n");
                                    break;

                                case "7": // return to main menu
                                    break;

                                default:
                                    System.out.println(errorMessage);
                            }
                        } while (!choiceSub.equals("7"));
                    }
                    
                    else { // if no account was found
                        System.out.println("\nAccount not found");
                    }
                    break;
                    
                case "9": // bubble sort and display savings accounts
                    
                    Account accountTemp;
                     for(int j = (countAccount-1); j >=0; j--){
                        for (int i = 0; i <= j-1; i++) {
                            
                            if (accountList[i].getAccountNumber().compareTo(accountList[(i+1)].getAccountNumber()) > 0) {
                                accountTemp = accountList[i];
                                accountList[i]= accountList[(i+1)];
                                accountList[(i+1)]= accountTemp;                               
                            }
                        }
                    }
                    
                    // display sorted savings accounts
                    for (int i = 0; i < countAccount; i++) {
                        if (accountList[i].getAccountType() == 's'){
                            accountList[i].displayInfo();
                        }
                    }
                    break;
                    
                 case "10": // Displays the number of student and employee accounts
                    int countStudent = 0;
                    int countEmployee = 0;
                    for (int i = 0; i < countAccount; i++) {
                        if(accountList[i].getPerson() == 1) { //counts student accounts
                            countStudent++;
                        }
                        else if (accountList[i].getPerson() == 2 || accountList[i].getPerson() == 3) { // counts employee accounts
                            countEmployee++;
                        }
                    }
                    
                    System.out.println("\nNumber of student accounts: " + countStudent + "\nNumber of employee accounts: " + countEmployee);
                    break;    
                    
                case "11": // displays any account with a balance less than 50 and fines that account $5
                    double fine;
                    for(int i = 0; i < countAccount; i++){
                        if(accountList[i].getBalance() < 50){
                            accountList[i].displayInfo();
                            System.out.println("Low balance; fine of $5 added\n\n");
                            fine = accountList[i].getBalance()-5;
                            accountList[i].setBalance(fine);
                        }
                    }
                    break;
                    
                case "12": // display accounts by last name using selection sort
                    StartTime = System.nanoTime();
                    
                    int min; 
                    for (int i = 0; i < countAccount - 1; i++) {
                        min = i;
                        for (int j = i + 1; j < countAccount; j++) {
                            int comparison = accountList[j].getLastname().compareTo(accountList[min].getLastname()); // sort by last name
                            if (comparison < 0) {
                                min = j;
                            }
                            else if (comparison == 0) { // if last name is same
                                if (accountList[j].getFirstname().compareTo(accountList[min].getFirstname()) < 0) { // sort by first name
                                    min = j;
                                }
                            }
                        }
                        Account temp = accountList[min];
                        accountList[min] = accountList[i];
                        accountList[i] = temp;
                    }
                    
                    System.out.println("Time taken (selection sort): " + (System.nanoTime() - StartTime) + " ns\n");
                    
                    for (int i = 0; i < countAccount; i++) {
                        accountList[i].displayInfo();
                    }
                    break;
                    
                case "13": // display accounts by first name using insertion sort
                    StartTime = System.nanoTime();
                    
                    for (int i = 1; i < countAccount; i++) {
                        Account key = accountList[i];
                        int position = i - 1;
                        
                        while (position >= 0 && accountList[position].getFirstname().compareTo(key.getFirstname()) > 0) {
                            accountList[position + 1] = accountList[position];
                            position--;
                        }
                        
                        accountList[position + 1] = key;
                    }
                    
                    System.out.println("Time taken (insertion sort): " + (System.nanoTime() - StartTime) + " ns\n");
                    
                    for (int i = 0; i < countAccount; i++) {
                        accountList[i].displayInfo();
                    }
                    break;
                    
                case "14": // binary search 
                    System.out.print("Enter first name: ");
                    String firstNameSearch = sc.next();
                    
                    // insertion sort by first name (not timed)
                    for (int i = 1; i < countAccount; i++) {
                        Account key = accountList[i];
                        int position = i - 1;
                        
                        while (position >= 0 && accountList[position].getFirstname().compareTo(key.getFirstname()) > 0) {
                            accountList[position + 1] = accountList[position];
                            position--;
                        }
                        
                        accountList[position + 1] = key;
                    }
                    
                    // binary search (timed)
                    
                    StartTime = System.nanoTime();
                    
                    accountIndex = 0;
                    accountFound = false;
                    int first = 0, last = countAccount - 1, mid;
                    while (!accountFound && first <= last) {
                        mid = (first + last) / 2;
                        if (accountList[mid].getFirstname().compareTo(firstNameSearch) == 0) {
                            accountIndex = mid;
                            accountFound = true;
                            EndTime = System.nanoTime();
                        }
                        else {
                            if (accountList[mid].getFirstname().compareTo(firstNameSearch) > 0) {
                                last = mid - 1;
                            }
                            else {
                                first = mid + 1;
                            }
                        }
                    }
                    
                    if (accountFound) {
                        System.out.println("Time taken (binary search): " + (EndTime - StartTime) + " ns");
                        if(linearTime !=0){//So it doesn't look like a linear search is faster no false results
                            System.out.println("Linear Search Time: "+linearTime+ "ns\n");// for quich comparision
                        }
                        
                        do { // loop over submenu until user chooses to exit
                            System.out.print("\n1. Check balance"
                                    + "\n2. Withdraw money"
                                    + "\n3. Deposit money"
                                    + "\n4. Change account type"
                                    + "\n5. Close the account"
                                    + "\n6. Return to Main Menu"
                                    + "\n   Your selection: ");
                            choiceSub = sc.next();
                            System.out.println();

                            switch (choiceSub) {
                                case "1": // check balance
                                    System.out.println("Balance: " + accountList[accountIndex].getBalance());
                                    break;

                                case "2": // withdraw money
                                    double withdrawalValue;
                                    System.out.print("Enter amount to withdraw: ");
                                    withdrawalValue = sc.nextDouble();
                                    accountList[accountIndex].withdrawal(withdrawalValue);
                                    break;

                                case "3": // deposit money
                                    double depositValue;
                                    System.out.print("Enter amount to deposit: ");
                                    depositValue = sc.nextDouble();
                                    accountList[accountIndex].deposit(depositValue);
                                    break;

                                case "4": // change account type
                                    if (accountList[accountIndex].getAccountType() == 'c') {
                                        accountList[accountIndex].setAccountType('s');
                                        System.out.println("\nAccount type changed from checking to savings");
                                    }
                                    else if (accountList[accountIndex].getAccountType() == 's') {
                                        accountList[accountIndex].setAccountType('c');
                                        System.out.println("\nAccount type changed from savings to checking");
                                    }
                                    break;

                                case "5": //close account
                                    char closeAccount;
                                    System.out.print("Are you sure you want to close the account? (y/n): ");
                                    closeAccount = sc.next().charAt(0);
                                    if (closeAccount == 'y' || closeAccount == 'Y') {
                                        
                                        Account ClosedAccount = accountList[accountIndex];
                                        int y = accountIndex + 1;//used to keep outbounds exception being thrown and closes account
                                        for (int j = accountIndex; j < countAccount; j++) { 
                                            if(y < countAccount) {
                                                accountList[j] = accountList[(y)];                                                
                                                y++;
                                            }
                                            else {
                                                accountList[j] = null;
                                            }
                                        }
                                        
                                        ClosedAccount = null;
                                        System.out.println("Account closed");                                      
                                        countAccount--;
                                    }
                                    choiceSub = "6";
                                    break;

                                case "6": // return to main menu
                                    break;

                                default:
                                    System.out.println(errorMessage);
                            }
                        } while (!choiceSub.equals("6"));
                    }
                    else {
                        System.out.println("\nAccount not found");
                    }
                    
                    break;
                    
                case "15": // exit the program
                    System.out.println("Thank you for choosing Bank of Saint Anselm! \n");
                    System.exit(0); // exits the program
                    break;
                    
                default: // error message
                    System.out.println(errorMessage);
                    
            }
        }
    }
}
