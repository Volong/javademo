package github.io.ylongo.desiginpattern.iterator;

import lombok.Data;

public class IteratorDemo {

	
	
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
