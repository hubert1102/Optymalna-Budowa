import java.util.ArrayList;

// Klasa Cennik dodaje cene do kazdego pretu.
public class Cennik extends ZestawPretow{
	private ArrayList<Integer> cennik;
	
	Cennik() {
		super();
		cennik = new ArrayList<Integer>();
	}
	
	public void addPret(int d, int c) {
		super.addPret(d);
		cennik.add(c);
	}
	
	public int getCena(int pos) {
		return cennik.get(pos);
	}
}
