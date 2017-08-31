package demo01;

public class User {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 3; i++) {
			User user = UserFactory.getUser();
			System.out.println(user);
		}
	}
	
}
