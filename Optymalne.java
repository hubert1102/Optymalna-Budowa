
// Klasa Optymalne znajduje rozwiazania
// dla metody ekonomicznej i ekologicznej.
public class Optymalne {
	private ZestawPretow zp;
	private Cennik c;
	private String sposob;
	
	private Wynik wynik;
	private Wynik akt_wynik;
	private ZestawPretow set1;
	private ZestawPretow set2;
	Optymalne(ZestawPretow zp, Cennik c, String sposob) {
		this.zp = zp;
		this.c = c;
		this.sposob = sposob;
		
		wynik = new Wynik();
		akt_wynik = new Wynik();
		
		akt_wynik.setOdpady(0);
		akt_wynik.setKoszty(0);
		
		set1 = new ZestawPretow();
		set2 = new ZestawPretow();
	}
	
	// optymalny znajduje dla zestawu pretow dlugosci d najlepszy
	// pret z cennika, z ktorego da sie wyciac ten zestaw,
	// z uwzglednieniem tego, czy szukamy rozwiazania ekologicznego czy
	// ekonomicznego. Zwraca -1 jezeli nie istnieje taki pret z cennika.
	private int optymalny(int d) {
		int index = c.dopasuj(d);
	
		if (index == -1 || c.getDlugosc(index) != d)
			index++;
		
		if (c.size() <= index)
			return -1;
		
		if (0 == sposob.compareTo("ekonomiczna")) {
			int min_c = c.getCena(index);
			for (int i = index; i < c.size(); i++) {
				if (c.getCena(i) < min_c) {
					min_c = c.getCena(i);
					index = i;
				}
			}
		}
		
		return index;
	}
	
	
	// czyLepsze sprawdza czy wynik uzyskany w
	// akt_wynik jest lepszy od tego w wynik.
	private boolean czyLepsze() {
		return akt_wynik.czyLepszy(sposob, wynik);
	}
	
	// podziel dzieli zestaw pretow zp na dwa zbiory set1 i set2,
	// przy czym set set1 jest niepusty. Elementy na pozycji takiej, 
	// gdzie 1 wystepuje w zapisie binarnym n, sa umieszczone w set1,
	// pozostale w set2.
	private void podziel(int n, ZestawPretow zp) {
		int licznik = 0;
		int size = zp.size();
		while (licznik < size) {
			if (n % 2 == 1)
				set1.addPret(zp.getDlugosc(licznik));
			else
				set2.addPret(zp.getDlugosc(licznik));
		
		licznik++;
		n /= 2;
		}
	}
	
	// ProcessOptymalnie rozpatruje kazdy podzial zbioru zp
	// na niepuste podzbiory. Dla kazdego podzbioru wyznacza
	// optymalny pret z cennika, z jakiego mozna go wyciac.
	public void ProcessOptymalnie() {
		double k = Math.pow(2, zp.size());
		
		if (zp.size() == 0 && czyLepsze())
			wynik = akt_wynik.clone();
				
		for (int i = 1; i < k; i++) {
			set1.clear();
			set2.clear();
			podziel(i, zp);
			int d = set1.sumaDlugosci();
			int index = optymalny(d);
			
			// Jezeli nie da sie wyciac zestawu pretow w set2 z jednego preta
			// lub dla optymalnego preta z cennika dla tego zestawu istnieje pret
			// w set1 taki, ze sie "zmiesci" w tym optymalnym precie, to nie
			// rozpatruje tego przypadku.
			if (index != -1 && 
			   (set2.size() == 0 || c.getDlugosc(index) < d + set2.getDlugosc(0))) {

				set1.addPoczatek(c.getDlugosc(index));
				akt_wynik.addZestaw(set1.clone());
				akt_wynik.dodajKoszty(c.getCena(index));
				akt_wynik.dodajOdpady(c.getDlugosc(index)-d);
				ZestawPretow pom = zp.clone();
				zp = set2.clone();
				
				ProcessOptymalnie();
				
				zp = pom.clone();
				pom = null;
				akt_wynik.dodajKoszty(-c.getCena(index));
				akt_wynik.dodajOdpady(-c.getDlugosc(index)+d);
				akt_wynik.popZestaw();
			}
		}
	}
	
	
	public void wypisz() {
		wynik.wypisz();
	}
}
