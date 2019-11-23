
public class Cache {

	int N;
	int K;
	int L;
	int numSetBits;
	Set[] sets;
	
	Cache(int N, int K, int L) {
		this.N = N;
		this.K = K;
		this.L = L;
		this.numSetBits = getNumSetBits();
		this.sets = new Set[N];
		for(int n=0; n<N; n++) {
			sets[n] = new Set(this.K, this.L);
		}
	}
	
	int getNumSetBits() {
		switch(this.N) {
		case 8:
			return 3;
		case 4:
			return 2;
		case 2:
			return 1;
		default:
			return 0;
		}
	}
	
	int getOffset(String address) {
		return address.charAt(0)-'0';
	}
	
	int getSet(String address) {
		int toMask = address.charAt(1) - '0';
		return toMask >>> (8-this.numSetBits);
	}
	
	String getTag(String address) {
		char mostSignificantBits = address.charAt(1);
		switch(this.numSetBits) {
		case 1:
			mostSignificantBits = (char) (mostSignificantBits & (128)); break;
		case 2:
			mostSignificantBits = (char) (mostSignificantBits & 192); break;
		case 3:
			mostSignificantBits = (char) (mostSignificantBits & 224); break;
		}
		return "" + mostSignificantBits + address.charAt(2) + address.charAt(3);	
	}
	
	int accessAddress(String address) {
		
		return 0;
	}
}
