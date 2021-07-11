package github.io.ylongo.desiginpattern.builder;

public class BuilderDemo {

	public static void main(String[] args) {

		Product product = Product.newBuilder()
								 .field1("value1")
								 .field2("value2")
								 .field3("value3")
								 .create();

		System.out.println(product);
		
	}
	
}
