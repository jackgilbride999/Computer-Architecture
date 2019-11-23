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
    int set_count;          // number of sets in the cache
    int line_count;         // number of lines in the cache
    int ** tags;            // pointer to an array of arrays of tags
    char *** addresses;     // pointer to an array of arrays of strings
};

int getOffset(char * address){
    // return the offset within the cache line determined by the
    // first four bits of the address
    return address[0]-'0';
}

int getSet(char * address, int N){
    // return the set number, which is log2(N)
}

int getTag(char * address, int N){

}

int main(){

    return 0;
}