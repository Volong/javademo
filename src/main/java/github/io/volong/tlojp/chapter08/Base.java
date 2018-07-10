package github.io.volong.tlojp.chapter08;

public class Base implements Comparable<Base>{

	private int sortOrder;
	
	public Base(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public int compareTo(Base o) {
		return sortOrder - o.sortOrder;
	}

}
