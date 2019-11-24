import java.util.LinkedList;

public class Cache {

	int N;
	int K;
	int L;
	int numSetBits;
	LinkedList<Integer>[] sets;
	
	Cache(int N, int K, int L) {
		this.N = N;
		this.K = K;
		this.L = L;
		this.numSetBits = getNumSetBits();
		this.sets = new LinkedList[N];
		for(int n=0; n<N; n++) {
			sets[n] = new LinkedList<Integer>();
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
	
	int getSet(int address) {
		switch(this.numSetBits) {
		case 1:
			return (address >>> 4) & 0x1;
		case 2:
			return (address >>> 4) & 0x3;
		case 3:
			return (address >>> 4) & 0x7;
		default:
			return 0;
		}
	}
	
	int getTag(int address) {
		return address >>> (4 + numSetBits);
	}
	
	/*
	 * access a specific address and run it through the cache,
	 * returning whether it is a hit or a miss. 
	 * In the case of a hit: The relevant set contains the tag,
	 * so remove it and add it to the start of the list (i.e. 
	 * set it as the most recently used). Return a hit.
	 * In the case of a miss: The relevant set does not contain
	 * the tag. If the set is full, evict the least recently used.
	 * Either way, add the new tag to the start of the list. Return
	 * a miss.
	 */
	boolean accessAddress(int address) {
		int setNumber = getSet(address);
		int tag = getTag(address);
		if(this.sets[setNumber].contains(tag)) {
			this.sets[setNumber].remove((Object)tag);
			this.sets[setNumber].addFirst(tag);
			return true;
		}
		else {
			if(this.sets[setNumber].size() == this.K) {
				this.sets[setNumber].removeLast();
			}
			this.sets[setNumber].addFirst(tag);
			return false;
		}
	}
}
