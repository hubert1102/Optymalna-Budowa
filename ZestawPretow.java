import java.util.ArrayList;
import java.util.Collections;

// Klasa definiujaca kolekcje pretow i operacje na nich.
// Prety w zestawie sa w kokretnej kolejnosci.
public class ZestawPretow {
	private ArrayList<Integer> prety;
	
	ZestawPretow() {
		prety = new ArrayList<Integer>();
	}

	public int size() {
		return prety.size();
	}
	
	// maxPret zwraca dlugosc najdluzszego pretu,
	// o ile prety jest posortowane niemalejaco.
	public int maxPret() {
		return prety.get(prety.size()-1);
	}
	
	public void addPret(int d) {
		prety.add(d);
	}
	
	public int getDlugosc(int pos) {
		return prety.get(pos);
	}
	
	// sumaDlugosci zwraca sume dlugosci wszystkich pretow w zestawie
	public int sumaDlugosci() {
		int s = 0;
		for (int i = 0; i < prety.size(); i++)
			s += prety.get(i);
		
		return s;
	}
	
	@SuppressWarnings("unchecked")
	public ZestawPretow clone() {
		ZestawPretow zp = new ZestawPretow();
		
		zp.prety = (ArrayList<Integer>)prety.clone();
		return zp;
	}
	
	public boolean isEmpty() {
		return prety.isEmpty();
	}
	
	public void remove(int index) {
		prety.remove(index);
	}
	
	// addPoczatek dodaje element na poczatek zestawu.
	public void addPoczatek(int d) {
		prety.add(0, d);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < prety.size(); i++)
			sb.append(prety.get(i)).append(" ");
		
		return sb.toString(); 
	}
	
	// doapsuj znajduje najkrotszy niekrotszy od d
	// drut z zestawu pretow prety.
	public int dopasuj(int d) {		
		int index = Collections.binarySearch(prety, d);
		
		if (index >= 0)
			return index;
		else
			return -(index+2);
	}
	
	public void clear() {
		prety.clear();
	}
}
