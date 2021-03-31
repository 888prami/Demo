package main;
import java.util.Vector;

//Class bank is created

public class Bank 
{
public int minimumBalance;
public Vector<Account> accounts = new Vector<Account>();
	public Bank(int minimumBalance) 
	{
		this.minimumBalance = minimumBalance;
	}
	
public Account createAccount(String accountNumber, String customerName, boolean isSalaryAccount) throws InvalidAccountNumber
	{
	Account ac = new Account(accountNumber, customerName, isSalaryAccount);
	depositAmount(ac, minimumBalance);
		accounts.add(ac);
		return ac;	
	}
	
public float depositAmount(Account acc, float amount)
	{
float amt = acc.getTotalBalance() + amount;
	acc.setTotalBalance(amt);
		return acc.getTotalBalance();
	}
	
	public float withdrawAmount(Account acc, float amount)	throws LowBalanceException
	{
		float temp = acc.getTotalBalance() - amount;
		
		if(temp >= minimumBalance)
			acc.setTotalBalance(temp);
		else
			throw new LowBalanceException("LOW BALANCE, CAN'T WITHDRAW");
		
		return acc.getTotalBalance();
	}
	
	public float generateBonus(Account acc)
	{
		float bonus = 0;
		
		if(!acc.isSalaryAccount())
		{
			if(acc.getTotalBalance() > 50000)
			{
				float exBal = (float) ((acc.getTotalBalance()-50000)*0.04);
				float regBal = (float) (50000*0.02);
				bonus = exBal + regBal;
			}
			else
			{
				bonus = (float) (acc.getTotalBalance()*0.02);
			}
			
			depositAmount(acc, bonus);
		}
		if(acc.isSalaryAccount())
		{
			if(acc.getTotalBalance() > 50000)
			{
				float exBal = (float) ((acc.getTotalBalance()-50000)*0.06);
				float regBal = (float) (50000*0.03);
				bonus = exBal + regBal;
			}
			else
			{
				bonus = (float) (acc.getTotalBalance()*0.03);
			}
			
			depositAmount(acc, bonus);
		}
		
		return bonus;
	}
	
	public float enquireBalance(Account acc)
	{
		return acc.getTotalBalance();
	}
	
	public static void main(String[] args)	throws LowBalanceException, InvalidAccountNumber
	{
		Bank sbi = new Bank(2000);
		
		Account a1 = sbi.createAccount("SAL12345", "Siddharth", true);
		Account a2 = sbi.createAccount("GEN12345", "Tiyasha", false);
		//Account a3 = sbi.createAccount("ABC12345", "Apurwa", true);
		
		System.out.println(sbi.enquireBalance(a1));
		System.out.println(sbi.enquireBalance(a2));
		
		System.out.println(sbi.depositAmount(a1, 20000));
		System.out.println(sbi.enquireBalance(a1));
		
		System.out.println(sbi.depositAmount(a2, 60000));
		System.out.println(sbi.enquireBalance(a2));
		
		System.out.println(sbi.withdrawAmount(a1, 10000));
		System.out.println(sbi.enquireBalance(a1));
		//System.out.println(sbi.withdrawAmount(a1, 61000));
		
		System.out.println(sbi.generateBonus(a1));
		System.out.println(sbi.enquireBalance(a1));
		
		System.out.println(sbi.generateBonus(a2));
		System.out.println(sbi.enquireBalance(a2));
	}
}


