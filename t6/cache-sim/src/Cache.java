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
	
	int getOffset(int address) {
		return (address & 0xF000) >>> 12;
	}
	
	int getSet(int address) {
		return (address & 0x0FFF) >>> (12-numSetBits); // something wrong here somewhere
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
		int offset = getOffset(address);
		int setNumber = getSet(address);
		int tag = getTag(address);
	//	System.out.println("Tag = " + tag + ", set = " + setNumber + ", offset = " + offset);
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
