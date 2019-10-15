import java.util.ArrayList;

// Klasa Wynik odpowiada za konstruowanie wyniku.
public class Wynik {
	private int odpady;
	private int koszty;
	private ArrayList<ZestawPretow> wyn;
	
	Wynik() {
		odpady = Integer.MAX_VALUE;
		koszty = Integer.MAX_VALUE;
		wyn = new ArrayList<ZestawPretow>();
	}
	
	public int getKoszty() {
		return koszty;
	}
	
	public int getOdpady() {
		return odpady;
	}
	
	void setKoszty(int k) {
		koszty = k;
	}
	
	void setOdpady(int o) {
		odpady = o;
	}
	
	void dodajKoszty(int k) {
		koszty += k;
	}
	
	void dodajOdpady(int o) {
		odpady += o;
	}
	
	public Wynik clone() {
		Wynik w = new Wynik();
		
		w.odpady = odpady;
		w.koszty = koszty;
		
		int size = wyn.size();

		for (int i = 0; i < size; i++)
			w.wyn.add(wyn.get(i).clone());

		return w;
	}
	
	// czyLepszy sprawdza, czy podany w
	// argumencie wynik jest lepszy.
	public boolean czyLepszy(String s, Wynik w) {
		if (0 == s.compareTo("ekonomiczna"))
			return this.koszty <= w.getKoszty();
		else
			return this.odpady <= w.getOdpady();			
	}
	
	// addPret dodaje pret dlugosci d na koniec
	// ostatniego zestawu pretow.
	public void addPret(int d) {
		int size = wyn.size();
		if (size == 0) {
			wyn.add(new ZestawPretow());
			size++;
		}
		
		wyn.get(size-1).addPret(d);
	}
	
	//nowyPodzial tworzy nowy zestaw pretow.
	public void nowyPodzial() {
		wyn.add(new ZestawPretow());
	}
	
	// addPoczatek dodaje pret dlugosci d na pierwsze
	// miejsce ostatniego zestawu pretow.
	public void addPoczatek(int d) {
		int size = wyn.size();
		wyn.get(size-1).addPoczatek(d);
	}
	
	public void addZestaw(ZestawPretow zp) {
		wyn.add(zp);	
	}
	
	public void popZestaw() {
		wyn.remove(wyn.size()-1);
	}
	
	// wypisz wypisuje wynik koncowy.
	public void wypisz() {
		System.out.println(koszty);
		System.out.println(odpady);

		for (int i = 0; i < wyn.size(); i++)
			System.out.println(wyn.get(i).toString());
	}
}
