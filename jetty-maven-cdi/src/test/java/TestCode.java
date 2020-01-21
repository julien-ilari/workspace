import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TestCode {

	public static void main(String args[]) {

		/* Voici comment déclarer TreeMap */
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();

		/* Ajout d'éléments à TreeMap */
		tmap.put(1, "Data1");
		tmap.put(23, "Data2");
		tmap.put(70, "Data3");
		tmap.put(4, "Data4");
		tmap.put(2, "Data5");

		for (Entry<Integer, String> entry : tmap.entrySet()) {
			 System.out.println("la clé est: " + entry.getKey () + " & Value est:" + entry.getValue());
		}

		/* Afficher le contenu à l'aide d'itérateur */
		Set set = tmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator.next();
			System.out.print("la clé est:" + mentry.getKey() + "& Value est:");
			System.out.println(mentry.getValue());
		}
	}

}
