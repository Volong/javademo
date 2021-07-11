package github.io.ylongo.desiginpattern.factor;

/**
 * 每个产品两两组合为一个产品
 */
public class NoAbstractFactoryDemo {


	public static void main(String[] args) {

		// A1 + B1
		ProductA productA1 = new ProductA1();
		ProductB productB1 = new ProductB1(); // 如果此时要将 B1 => B3，如果使用的地方太多了，则工作量太大
		productA1.execute();
		productB1.execute();
		
		// A2 + B2
		ProductA productA2 = new ProductA2();
		ProductB productB2 = new ProductB2();
		productA2.execute();
		productB2.execute();

		// A3 + B3
		ProductA productA3 = new ProductA3();
		ProductB productB3 = new ProductB3();
		productA3.execute();
		productB3.execute();
		
	}
	
	
	public interface ProductA {
		public void execute();
	}
	
	public static class ProductA1 implements ProductA {

		@Override
		public void execute() {
			System.out.println("productA1");
		}
	}

	public static class ProductA2 implements ProductA {

		@Override
		public void execute() {
			System.out.println("productA2");
		}
	}

	public static class ProductA3 implements ProductA {

		@Override
		public void execute() {
			System.out.println("productA3");
		}
	}
	
	public interface ProductB {
		public void execute();
	}

	public static class ProductB1 implements ProductB {

		@Override
		public void execute() {
			System.out.println("productB1");
		}
	}

	public static class ProductB2 implements ProductB {

		@Override
		public void execute() {
			System.out.println("productB2");
		}
	}
	
	public static class ProductB3 implements ProductB {
		
		@Override
		public void execute() {
			System.out.println("productB3");
		}
	}
}
