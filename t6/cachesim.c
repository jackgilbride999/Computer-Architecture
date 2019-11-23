/* 
    Compute the number of hits and misses if the following list of hexadecimal addresses is
    applied to caches with the following organisations.
    (i)     128 byte 1-way cache with 16 bytes per line (direct mapped)
    (ii)    128 byte 2-way set associative cache with 16 bytes per line
    (iii)   128 byte 4-way set associative cache with 16 bytes per line
    (iv)    128 byte 8-way associative cache with 16 bytes per line (fully associative)
*/
char hex_addresses [32][4] = 
{
    "0000", "0004", "000c", "2200", "00d0", "00e0", "1130", "0028",
    "113c", "2204", "0010", "0020", "0004", "0040", "2208", "0008",
    "00a0", "0004", "1104", "0028", "000c", "0084", "000c", "3390",
    "00b0", "1100", "0028", "0064", "0070", "00d0", "0008", "3394"
}
/*
    L is the number of lines, N is the number of sets and K is the number of directories
    Assume that the first 4 bits of the address is used as the offset within
    the cache line, the next log2(N) bits select the set and remaining bits 
    form the tag. Furthermore, assume that all cache lines are initially
    invalid and that a LRU replacement policy is used.
*/

struct cache = {
    int N;                  // N = number of sets
    int K;                  // K = cache lines per set

    struct set * sets;

    int num_hits;
    int num_misses;
};

struct set{
    struct cache_line * lines;  // array of cache lines
}

struct cache_line{
    char ** contents;           //  array of four strings
}

int getOffset(char * address){
    // return the offset within the cache line determined by the
    // first four bits of the address
    return address[0]-'0';
}

int getNumSetBits(char * address, int N){
    // return the number of bits which hold the set number, which is log2(N)
    switch(N){
        case 8:
            return 3;
        case 4:
            return 2;
        case 2:
            return 1;
        case 1:
            return 0;
    }
}

int getNumTagBits(char * address, int N){
    // return the number of bits which hold the tag number
    return 16 - 4 - getNumSetBits(N); 
}

int getSetNumber(char * address, int numSetBits){
    switch(numSetBits){
        case 0:
            return 0;                               // cache is fully associative
        case 1:
            return ((address[1]-'0') & 128) >> 7;   // return bit 4 of the address
        case 2:
            return ((address[1]-'0') & 192) >> 6;   // return bits 4 and 5
        case 3:
            return ((address[1]-'0') & 224) >> 5;   // return bits 4, 5 and 6
    }
}

int main(){
    return 0;
}