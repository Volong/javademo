package github.io.volong;

import java.util.Date;

public class Task1 implements Runnable{

	@SuppressWarnings("deprecation")
	public void run() {
		System.out.println("----task1 start--------"+new Date().toLocaleString());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----3s later, task1 end--------"+new Date().toLocaleString());
	}
	
}