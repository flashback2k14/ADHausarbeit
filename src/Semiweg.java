import java.util.ArrayList;


public class Semiweg {

	private ArrayList<Integer> semiwegKnoten;

	public Semiweg() {
		this.semiwegKnoten = new ArrayList<Integer>();
	}

	public void addSemiwegKnoten(Integer semiwegKnoten) {
		this.semiwegKnoten.add(semiwegKnoten);
	}
	
	public void printSemiweg() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		for(Integer i : semiwegKnoten) {
			sb.append(i).append(" ");
		}
		
		sb.append("]");
		
		System.out.println(sb.toString());
	}
}