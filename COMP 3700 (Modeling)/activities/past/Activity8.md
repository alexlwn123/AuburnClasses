Activity 8
----------
### Alex Lewin

##### 1. Classes:  

  * **Account**: Generic parent abstract class, has ability Compute Interest and Compute Fees but does not implement these, has credit and debit
  * **Checking Account**: Extends Account, unlimited withdraws, no monthly fee, overdraft fee
  * **Savings Account**: Extends Account, limited withdraws, monthly fee if you don't deposit X dollars per month, no overdraft fee

##### 2. Fields:

  * **Account**:  
    ```java
    public String accountName;
    public double balance, interestRate;
    public double deposit(double amount);
    public double withdraw(double amount);
    public abstract double ComputeInterest(this.balance, this.interestRate);`
    public abstract double ComputeFees();```  
    ```
  * **ChekcingAccount**:  
    ```java
    extends Account;
    ```
  * **SavingsAccount**:  
    ```java
    extends Account;
    private double monthlyFee;
    private double chargedThisMonth;
    
    ```


3. 

```java
public class Account{
  private double balance;
  private String name;
  private double fees; 

  public Account(String name, double balance) {
    this.name = name;
    this.balance = balance;
  }

  public double getBalance(){ return this.balance; }
  public double getName(){ return this.name; }
  public void deposit(double mons){ this.balance += mons; }
  public void withdraw(double mons){ 
    if (mons > this.balance) System.out.println("Not Allowed!");
    else this.balance -= mons; 
  }
  @Overide
  public String toString() {
    return this.name + " has $" + this.balance + " as a balance";
  }
}
public class CheckingAccount extends Account {
  public static double OVERDRAFT_FEE = 35.0;
  
  public CheckingAccount(String name, double balance) {
    super(name, balance);
  }

  public void withdraw(double amount) {
    super.withdraw(amount);

    if (this.balance < amount) this.fees += OVERDRAFT_FEE;
  }

  public void deposit(double amount) {
    super.deposit(amount)
  }
  
  
}

public class SavingsAccount() extends Account {
  private double monthlyFee;
  private double chargedThisMonth; 

  public SavingsAccount(String name, double balance, double monthlyFee) {
    super(name, balance);
    this.monthlyFee = monthlyFee;
    this.chargedThisMonth = 0;
  }

  public void withdraw(double amount) {
    if (this.balance < amount) {
      System.out.println("Too Much!");
    }
    this.balance -= amount;
  }

  public void deposit(double amount) {
    chargedThisMonth += amount;
    balance += amount;
  }
 
}
```


