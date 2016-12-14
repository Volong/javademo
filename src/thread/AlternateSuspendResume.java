package thread;

public class AlternateSuspendResume implements Runnable{

	private volatile int firstVal;
	private volatile int secondVal;
	
	private volatile boolean suspended;
	
	@Override
	public void run() {
		System.out.println("run...");
		suspended = false;
		firstVal = 0;
		secondVal = 0;
		try {
			workMethod();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean areValueEqual(){
		return (firstVal == secondVal);
	}
	
	private void workMethod() throws InterruptedException{
		System.out.println("workMethod...");
		int val = 1;
		while(true){
			System.out.println("workMethod循环中...");
			waitWhileSuspended();
			stepOne(val);
			stepTwo(val);
			val ++;
			System.out.println("val:" + val);
			waitWhileSuspended();
			Thread.sleep(1000);
		}
	}
	
	private void waitWhileSuspended(){
		System.out.println("waitWhileSuspended");
		while(suspended){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void stepOne(int val){
		firstVal = val;
	}
	
	private void stepTwo(int val){
		secondVal = val;
	}
	
	public void suspendRequest() {
        suspended = true;
    }
 
    public void resumeRequest() {
        suspended = false;
    }
    
	public static void main(String[] args) {
		AlternateSuspendResume asr = new AlternateSuspendResume();
		Thread t = new Thread(asr);
		t.start();
		
		try {
			System.out.println("暂停开始");
			Thread.sleep(1000);//这是为了保证线程t执行完 不然firstVal与SecondVal不能保证相等
			System.out.println("暂停结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("for is begging...");
		for(int i = 0;i < 10;i++){
			asr.suspendRequest();
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("firstVal==secondVal:" + asr.areValueEqual());
			
			asr.resumeRequest();
			
		}
		System.exit(0);
	}
}
