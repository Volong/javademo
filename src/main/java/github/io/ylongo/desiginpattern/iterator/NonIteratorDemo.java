package github.io.ylongo.desiginpattern.iterator;


import lombok.Data;

public class NonIteratorDemo {

	public static void main(String[] args) {
		
		Student student1 = new Student("小明");
		Student student2 = new Student("小王");
		
		Student[] students = new Student[2];
		students[0] = student1;
		students[1] = student2;

		ClassRoom classRoom = new ClassRoom();
		classRoom.setStudents(students);

		Student[] classRoomStudents = classRoom.getStudents();
		
		for (Student student : classRoomStudents) {
			System.out.println(student);
		}
		
	}
	
	@Data
	public static class Student {
		
		private String name;

		public Student(String name) {
			this.name = name;
		}
	}
	
	@Data
	private static class ClassRoom {
		
		private Student[] students;
		
		
	}
}
