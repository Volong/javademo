package github.io.ylongo.desiginpattern.factor;


public class AbstractFactoryDemo {

	public static void main(String[] args) {

		// A1 + B1
		ProductA productA1 = Factory1.get().createProductA();
		ProductB productB1 = Factory1.get().createProductB();// 如果此时要将 B1 => B3，只需要更改createProductB的实现类即可
		productA1.execute();
		productB1.execute();

		// A2 + B2
		ProductA productA2 = Factory2.get().createProductA();
		ProductB productB2 = Factory2.get().createProductB();
		productA2.execute();
		productB2.execute();

		// A3 + B3
		ProductA productA3 = Factory3.get().createProductA();
		ProductB productB3 = Factory3.get().createProductB();
		productA3.execute();
		productB3.execute();
	}


	private interface Factory {
		ProductA createProductA();
		ProductB createProductB();
	}

	public static class Factory1 implements Factory {

		private static final Factory1 instance = new Factory1();

		public static Factory1 get() {
			return instance;
		}

		@Override
		public ProductA createProductA() {
			return new ProductA1();
		}

		@Override
		public ProductB createProductB() {
			return new ProductB1();
		}
	}

	public static class Factory2 implements Factory {

		private static final Factory2 instance = new Factory2();

		public static Factory2 get() {
			return instance;
		}

		@Override
		public ProductA createProductA() {
			return new ProductA2();
		}

		@Override
		public ProductB createProductB() {
			return new ProductB2();
		}
	}

	public static class Factory3 implements Factory {

		private static final Factory3 instance = new Factory3();

		public static Factory3 get() {
			return instance;
		}

		@Override
		public ProductA createProductA() {
			return new ProductA3();
		}

		@Override
		public ProductB createProductB() {
			return new ProductB3();
		}
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
