package github.io.volong;

public class Main {

	public static void main(String[] args) {
	    
	    Integer[][] i = new Integer[3][2];
	    
	    i[0] = new Integer[] {1};
	    
	    for (int j = 0; j < i.length; j++) {
	        for (int j2 = 0; j2 < i[j].length; j2++) {
                System.out.print(i[j][j2] + "\t");
            }
	        
	        System.out.println();
	    }
	}
}
