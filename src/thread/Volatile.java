package thread;

public class Volatile implements Runnable{

	private int value;
	private volatile boolean missedIt;
	
	
	public Volatile() {
		this.value = 10;
		this.missedIt = false;
	}


	@Override
	public void run() {
		System.out.println("run...");
		System.out.println("into while value is:" + value);
		while(value < 20){
			if(missedIt){
				System.out.println("if...");
				int currValue = value;
				System.out.println("currValue:" + currValue);
				Object lock = new Object();
				synchronized(lock){
					
				}
				int valueAfterSync = value;
				System.out.println("valueAfterSync:" + valueAfterSync);
				break;
			}
		}
		System.out.println("leave while value is:" + value);
		System.out.println("run over...");
	}
	
	public void workMethod(){
		System.out.println("......................................working...");
		try {
			Thread.sleep(2000);
			value = 50;
			System.out.println("modify value:" + value);
			Thread.sleep(5000);
			missedIt = true;
			System.out.println("modify missedIt:" + missedIt);
			
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("work over");
	}

	public static void main(String[] args) {
		try {
			Volatile vo = new Volatile();
			Thread t = new Thread(vo);
			t.start();
			Thread.sleep(100);
			vo.workMethod();
//			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
