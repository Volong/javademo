package demo01;

public class UserFactory {

	public static User user = new User();
	
	public static User getUser() {
		return user;
	}
	
	public static void main(String[] args) {
		
		// 我爱北京天安门，天安门上彩旗飞，伟大领袖毛主席，指引我们向前进，向前进
		System.out.println("我爱北京天安门，天安门上彩旗飞，伟大领袖毛主席，指引我们向前进，向前进".length());
	}
}
