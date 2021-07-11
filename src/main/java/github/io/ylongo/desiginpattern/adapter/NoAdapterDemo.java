package github.io.ylongo.desiginpattern.adapter;

public class NoAdapterDemo {

	public static void main(String[] args) {
		OldInterface oldInterface = new OldInterfaceImpl();
		oldInterface.oldExecute();
		
		NewInterface newInterface = new NewInterfaceImpl();
		newInterface.newExecute();
	}
	
	public interface OldInterface {
		
		void oldExecute();
	}
	
	public static class OldInterfaceImpl implements OldInterface {
		
		@Override
		public void oldExecute() {
			System.out.println("oldExecute");	
		}
	}
	
	public interface NewInterface {
		
		void newExecute();
	}
	
	public static class NewInterfaceImpl implements NewInterface {
		
		@Override
		public void newExecute() {
			System.out.println("newExecute");	
		}
	}
}
