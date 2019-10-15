import java.util.Scanner;

// Klasa Wczytaj dopowiada za wczytanie danych i
// rozpoczecie odpowiedniej metody szukania rozwiazania.
public class Wczytaj {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		Cennik c = new Cennik();
		
		for (int i = 0; i < n; i++) {
			int d = s.nextInt(), cena = s.nextInt();
			c.addPret(d, cena);
		}
		
		int m = s.nextInt();

		ZestawPretow zp = new ZestawPretow();
		for (int i = 0; i < m; i++) {
			int d = s.nextInt();
			zp.addPret(d);
		}
		
		String polecenie = s.next();
		
		if (polecenie.charAt(0) == 'e') {
			Optymalne policz = new Optymalne(zp, c, polecenie);
			//policz.ProcessOptymalnie();
			policz.ProcessOptymalnie();
			policz.wypisz();
		}
		else  {
			Zachlanne policz = new Zachlanne(zp, c);
			if (polecenie.compareTo("maksymalistyczna") == 0)
				policz.ProcessMaksimum();
			else 
				policz.ProcessMinimum();
		}
	
		s.close();
	}
}
