class BankAccount{
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber=accountNumber;
    }
    public String getAccountHolderName(){
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName){
        this.accountHolderName=accountHolderName;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("Deposit successful: "+amount);
        }
    }
    public void withdraw(double amount){
        if(balance>=amount && amount>0){
            balance-=amount;
            System.out.println("Withdrawal successful: "+amount);
        }
        else{
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
    public void displayAccountInfo(){
        System.out.println("Account Number: "+getAccountNumber());
        System.out.println("Account Holder Name: "+getAccountHolderName());
        System.out.println("Balance: "+getBalance());
    }
}

public class bankoops {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.setAccountNumber("123456789");
        account.setAccountHolderName("John Doe");
        account.deposit(1000);
        account.displayAccountInfo();
        account.withdraw(500);
        account.displayAccountInfo();
        account.withdraw(600);
    }
}
