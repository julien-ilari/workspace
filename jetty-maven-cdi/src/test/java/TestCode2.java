public class TestCode2 {
	
	private static long totalValeur(final long valeur, final long s) {
		long totalValeur = 0;
		long reste = s;
		
		// optimal nombre de valeur
		if (reste >= valeur) {
			// Si il reste 1€ avec prend la plus petie monaie on va essayer de rendre avec une valeur inférieure
			if (valeur != 2 && reste % 2 == 1) {
				totalValeur = (reste - valeur) / valeur;
				reste = reste% valeur +valeur;
			} else {
				totalValeur = reste / valeur;
				reste = reste % valeur;
			}
		}
		return totalValeur;
	}

	private static Change optimalChange(final long s) {
		Change c = new Change();
		
		if(s > 1) {
			long reste = s;
			c.bill10 = totalValeur(10, reste);
			reste = reste - (c.bill10 * 10);
			c.bill5 = totalValeur(5, reste);
			reste = reste - (c.bill5 * 5);
			c.coin2 = totalValeur(2, reste);
			reste = reste - (c.coin2 * 2);
			
			if(reste == 1) {
				System.out.println(c.coin2*2 + c.bill5*5 + c.bill10*10);
				reste = s +1;
				System.out.println("Reste :" + reste);
				
				
				
				c.bill10 = totalValeur(10, reste);
				reste = reste - (c.bill10 * 10);
				
				c.bill5 = totalValeur(5, reste);
				reste = reste - (c.bill5 * 5);
				
				c.coin2 = totalValeur(2, reste);
				reste = reste - (c.coin2 * 2);
			}
			System.out.println(c.coin2*2 + c.bill5*5 + c.bill10*10);
			System.out.println("Valeur 2 :" + c.coin2);
			System.out.println("Valeur 5 :" + c.bill5);
			System.out.println("Valeur 10 :" + c.bill10);
			System.out.println("reste :" + reste);
			
			
			
		}
		
		return c;
	}

	public static void main(String args[]) {

		

		optimalChange(151654651681618587L);

	}

}
