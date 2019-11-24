
public class Simulator {

	static final int[] addresses = 
		{
				0x0000, 0x0004, 0x000c, 0x2200, 0x00d0, 0x00e0, 0x1130, 0x0028,
			    0x113c, 0x2204, 0x0010, 0x0020, 0x0004, 0x0040, 0x2208, 0x0008,
			    0x00a0, 0x0004, 0x1104, 0x0028, 0x000c, 0x0084, 0x000c, 0x3390,
			    0x00b0, 0x1100, 0x0028, 0x0064, 0x0070, 0x00d0, 0x0008, 0x3394
		};
	
	public static void main(String[] args) {
		int[] Ns = {8, 4, 2, 1};
		int[] Ks = {1, 2, 4, 8};
		for(int i=0; i<4; i++) {
			Cache cache = new Cache(Ns[i], Ks[i], 16);
			int numHits = 0;
			int numMisses = 0;
			for(int a=0; a<addresses.length; a++) {
				if(cache.accessAddress(addresses[a]))
					numHits++;
				else
					numMisses++;
			}
			System.out.println("N = " + Ns[i] + ". K = " + Ks[i] + ". Number hits = " + numHits + ". Number misses = " + numMisses);
		}
	}
}
