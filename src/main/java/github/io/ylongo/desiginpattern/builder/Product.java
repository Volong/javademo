package github.io.ylongo.desiginpattern.builder;

public class Product {

	public static void main(String[] args) {

		Product product = Product.newBuilder()
				.field1("value1")
				.field2("value2")
				.field3("value3")
				.create();

		System.out.println(product);

	}
	
	private String field1;

	private String field2;

	private String field3;

	public static ConcreteBuilder newBuilder() {
		return new ConcreteBuilder();
	}

	interface Builder {

		Builder field1(String value);

		Builder field2(String value);

		Builder field3(String value);

		Product create();
	}
	
	public static class ConcreteBuilder implements Builder {

		private final Product product = new Product();

		private ConcreteBuilder() {

		}

		@Override
		public ConcreteBuilder field1(String value) {
			System.out.println("处理field1相关的业务逻辑");
			product.setField1(value);
			return this;
		}

		@Override
		public ConcreteBuilder field2(String value) {
			System.out.println("处理field2业务逻辑");
			product.setField2(value);
			return this;
		}

		@Override
		public ConcreteBuilder field3(String value) {
			System.out.println("处理field3业务逻辑");
			product.setField3(value);
			return this;
		}

		@Override
		public Product create() {
			return product;
		}
	}
	
	private void setField1(String field1) {
		this.field1 = field1;
	}

	private void setField2(String field2) {
		this.field2 = field2;
	}

	private void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "Product{" +
				"field1='" + field1 + '\'' +
				", field2='" + field2 + '\'' +
				", field3='" + field3 + '\'' +
				'}';
	}
}



