package module1.activity;

public class RunBank{
    
    public static void main(String[] args){
        BankAccount aliceAcc = new BankAccount("12345","Alice", 100);
        BankAccount billAcc = new BankAccount("67890","Bill", 500);
        
        aliceAcc.deposit(200);
        billAcc.deposit(300);
        aliceAcc.withdraw(50);
        billAcc.withdraw(1000);

        System.out.println(aliceAcc.toString());
        System.out.println(billAcc.toString());

    }
}
/*
 * In ‘main’ method, create two ‘BankAccount’ objects with the following values:
accountNumber: “12345”, accountHolderName: “Alice”, balance: $100.0
accountNumber: “67890”, accountHolderName: “Bill”, balance: $500.0

Deposit $200.00 into Alice’s account and $300 into Bill’s account
Withdraw $50.00 from Alice’s account and $1000.00 from Bill’s account
Display both account details with the account number, account holder name and the account’s balance 

 */