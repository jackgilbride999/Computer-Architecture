
public class Set {
	int[] tags;
	String[][] cacheLines;
	
	Set(int K, int L){
		tags = new int[K];
		cacheLines = new String[K][L/4];
	}
}
