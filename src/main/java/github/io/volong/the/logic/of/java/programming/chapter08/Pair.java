package github.io.volong.the.logic.of.java.programming.chapter08;

public class Pair<U, T> {
	U first;
	T second;
	
	public Pair(U fitst, T second) {
		this.first = fitst;
		this.second = second;
	}

	public U getFirst() {
		return first;
	}

	public void setFirst(U first) {
		this.first = first;
	}

	public T getSecond() {
		return second;
	}

	public void setSecond(T second) {
		this.second = second;
	}
	
	public static void main(String[] args) {
		
		Pair<String, Integer> pair = new Pair<>("yihailong", 100);
		System.out.println(pair.getFirst());
		System.out.println(pair.getSecond());
	}
}
