package module1.activity;

//Sample Bank account
public class BankAccount {

    //attributes
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    //constructor
    public BankAccount(String accountNumber, String accountHolderName, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    //methods
    public void deposit(double amount){
        this.balance += amount;
    }

    //deduct the specified amount from the account's balance
    //display error if known
    public void withdraw(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
        }else{
            System.out.println("Insufficient funds");
        }
    }
    public double getBalance(){
        return this.balance;
    }

    //overload
    public String toString(){
        return "Account Number: " + this.accountNumber + "\nAccount Holder Name: " 
        + this.accountHolderName + "\nBalance: " + this.balance;
    }
}


/*
 * Create a Java class called ‘BankAccount’ with the following attributes:
‘accountNumber’ (string): A unique account number for each account
‘accountHolderName’ (string): Name of the account holder
‘balance’ (double): current balance in the account
Note: remember to name your file the same name as the class. (i.e. BankAccount.java)

Create a constructor
Write a constructor for ‘BankAccount’ class that allows you to initialize the account number, 
account holder’s name and balance when an object of the class is created

Create the following methods in the ‘BankAccount’ class;
deposit(double amount): method should add the specified amount to account’s balance
withdraw(double amount): This method should deduct the specified amount from the account's balance 
if there are sufficient funds. Otherwise, it should display an error message.
getBalance(): This method should return the current balance of the account.
toString(): Override the toString method to provide a meaningful string representation of the account, including account number, account holder's name, and balance
 */