import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestCodinGame {
	
	static boolean exists(Integer[] ints, int cle) {
		int deb,fin,milieu;

	    deb = 0;
	    fin = ints.length-1;
	    char test = "".charAt(1);
	    
	    "Toto".contains(String.valueOf(test));
	    do {
	      milieu = (deb + fin) / 2;
	      int comp = ints[milieu].compareTo(cle);
	      if (comp == 0) return true;
	      if (comp > 0) fin = milieu - 1;
	      else deb = milieu + 1;
	    } while (deb <= fin);

	    return false;
	}
	
	public boolean testdsjfhskf(int ints[], int k) {
		 if(ints.length == 0) return false;
	     
		 IntStream boxedArray = Arrays.stream(ints);
		 Collector<Integer, ?, ArrayList<Integer>> collectors = Collectors.toCollection(ArrayList::new);
		 List<Integer> listInts = boxedArray.boxed().collect(collectors);
		 
		 return listInts.contains(k);
	}

	@Test
	public void testMain() {
		
		int[] tab = { 10, 15, 20, 36,42,56,85};
		System.out.println(Arrays.asList(tab).contains(15));
 	
	}

}
