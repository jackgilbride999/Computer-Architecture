
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
		for(Set set : this.sets) {
			set = new Set(this.K, this.L);
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
}
