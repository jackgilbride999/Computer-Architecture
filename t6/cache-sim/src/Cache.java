
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
	
	int getOffset(int address) {
		return (address & 0xF000) >>> 12;
	}
	
	int getSet(int address) {
		return (address & 0x0FFF) >>> (12-numSetBits);
	}
	
	int getTag(int address) {
		switch(numSetBits) {
		case 0:
			return address & 0x0FFF;
		case 1:
			return address & 0x07FF;
		case 2:
			return address & 0X03FF;
		default:
			return address & 0x01FF;
		}
	}
	
	int accessAddress(String address) {
		
		return 0;
	}
}
