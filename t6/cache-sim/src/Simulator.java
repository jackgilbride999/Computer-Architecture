
public class Simulator {

	static final String[] addresses = 
		{
				"0000", "0004", "000c", "2200", "00d0", "00e0", "1130", "0028",
			    "113c", "2204", "0010", "0020", "0004", "0040", "2208", "0008",
			    "00a0", "0004", "1104", "0028", "000c", "0084", "000c", "3390",
			    "00b0", "1100", "0028", "0064", "0070", "00d0", "0008", "3394"
		};
	
	static int numHits;
	static int numMisses;
	
	public static void main(String[] args) {
		numHits = 0;
		numMisses = 0;
	}
}