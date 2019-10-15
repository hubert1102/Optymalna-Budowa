
// Klasa Zachlanne rozwiazuje metody maksymalistycze
// i minimalistyczne szukania rozwiazania.
public class Zachlanne {
	private ZestawPretow zp;
	private Cennik c;

	private Wynik wynik;
	
	Zachlanne(ZestawPretow zp, Cennik c) {
		this.zp = zp;
		this.c = c;
		
		wynik = new Wynik();
		
		wynik.setKoszty(0);
		wynik.setOdpady(0);
	}
	
	// upchaj dla dlugosci preta d wycina z niego za kazdym razem
	// mozliwie duze prety i zapisuje ten podzial do wyniku.
	private void upchaj(int d) {
		wynik.nowyPodzial();
		wynik.addPret(d);
		int i = zp.dopasuj(d);

		while (i != -1) {
			int di = zp.getDlugosc(i);
			wynik.addPret(di);
			
			d -= di;
			zp.remove(i);
			
			if (!zp.isEmpty())
				i = zp.dopasuj(d);	
			else 
				i = -1;
		}
		
		wynik.dodajOdpady(d);
	}
	
	public void ProcessMaksimum() {
		int n = c.size();
		
		while(!zp.isEmpty()) {
			int d = c.getDlugosc(n-1);
			int cena = c.getCena(n-1);
			
			wynik.dodajKoszty(cena);
			upchaj(d);
		}
		
		wynik.wypisz();
	}
	
	void ProcessMinimum() {
		while (!zp.isEmpty()) {
			int i = c.dopasuj(zp.maxPret());
			
			if (i == -1 || zp.maxPret() != c.getDlugosc(i))
				i++;
			
			wynik.dodajKoszty(c.getCena(i));
			upchaj(c.getDlugosc(i));
		}
		wynik.wypisz();
	}
}
