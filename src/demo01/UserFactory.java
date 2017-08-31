package demo01;

public class UserFactory {

	public static User user = new User();
	
	public static User getUser() {
		return user;
	}
}
