package github.io.volong.java.core.volume.one.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double[] accounts;
	
	private Lock bankLock;
	
	private Condition sufficientFunds;
	
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}
	
	public void transfer(int from, int to, double amount) throws InterruptedException {
		bankLock.lock();
		try {
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			
			System.out.println(Thread.currentThread());
			
			accounts[from] -= amount;
			
			System.out.printf("%10.2f from %d to %d", amount, from, to);
			
			accounts[to] += amount;
			
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
		} finally {
			bankLock.unlock();
		}
		
		sufficientFunds.signalAll();
	}
	
	public double getTotalBalance()	{
		bankLock.lock();
		
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			
			return sum;
		} finally {
			bankLock.unlock();
		}
	}
	
	
}
