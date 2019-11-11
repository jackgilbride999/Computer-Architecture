/*
Determine: 
- the number of procedure calls 
- the maximum register window depth 
- the number of register window overflows 
- the number of register window underflows 
that would occur during the calculation of ackerman(3,6) 
given a RISC-I processor with 6, 8 and 16 register sets 
respectively
*/

#include <stdio.h>
#include <time.h>   	// for clock_t, clock(), CLOCKS_PER_SEC
#include <unistd.h> 	// for sleep()

int MAX_REGISTER_WINDOW;
int procedure_calls = 0;
int max_window_depth = 0;
int current_window_depth = 0;
int current_register_window = 0;
int number_overflows = 0;
int number_underflows = 0;
int register_window;

int ackermann(int x, int y) {
    /* Deal with window depth & procedure calls */
    procedure_calls++;        // every time the procedure is called, the procedure count increases
    current_window_depth++;    // every time the procedure is entered, we increase the window depth
    if(current_window_depth>max_window_depth){
        // update the max depth if our current depth is our max so far
        max_window_depth = current_window_depth;
    }
    /* Deal with overflow */
    register_window++;
    if(register_window>MAX_REGISTER_WINDOW){
        // register overflow
        number_overflows++;
        register_window--;
    }
    /* Deal with ackermann function */
   int return_value;
    if (x == 0) {
        return_value = y+1;
    } else if (y == 0) {
        return_value = ackermann(x-1, 1);
    } else {
        return_value = ackermann(x-1, ackermann(x, y-1));
    }
    /* Deal with underflow*/
    register_window--;
    if(register_window<1){
        // register underflow
        number_underflows++;
        register_window++;
    }
    current_window_depth--;    // leaving the function so decrease the register depth
    return return_value;
}

int main(){
    // Implementing Q2
    for(int count=0; count<3; count++){
        switch(count){
            case 0: MAX_REGISTER_WINDOW = 5; break;
            case 1: MAX_REGISTER_WINDOW = 7; break;
            default: MAX_REGISTER_WINDOW = 15; break;
        }
        printf("\nFor %d register windows:\n", MAX_REGISTER_WINDOW+1);
        procedure_calls = 0;
        max_window_depth = 0;
        current_window_depth = 0;
        current_register_window = 0;
        number_overflows = 0;
        number_underflows = 0;
        register_window = 1;
        int result = ackermann(3,6);
        printf("result = %d\nprocedure calls = %d\ncurrent window depth = %d\nmax window depth = %d\nregister window = %d\nnumber of overflows = %d\nnumber of underflows = %d\n", result, procedure_calls, current_window_depth, max_window_depth, register_window, number_overflows, number_underflows);
    }

    // Implementing Q3
    double time_spent = 0.0;
    double num_iterations = 1000;
    clock_t begin = clock();
    for(int i = 0; i<num_iterations; i++){
        // to store execution time of code
        int result = ackermann(3,6);
    }
    clock_t end = clock();
    time_spent += ((double)(end - begin)/CLOCKS_PER_SEC)/num_iterations;
    printf("\nTime to execute ackermann is %f seconds\n", time_spent);
    return 0;
}

/*
Q2)
For 6 register windows:
    number of procedure calls = 172233
    maximum window depth = 511
    number of overflows = 84885
    number of underflows = 84885

For 8 register windows:
    number of procedure calls = 172233
    max window depth = 511
    number of overflows = 83911
    number of underflows = 83911

For 16 register windows:
    number of procedure calls = 172233
    max window depth = 511
    number of overflows = 80142
    number of underflows = 80142


Q3)
    Time to execute ackermann is ~0.001184 seconds, so approximately one millisecond
    This is a reasonably accurate result due to the approach. The function clock() takes
    the current CPU time. So subtracting the start CPU time from the end CPU time gives the
    total CPU time taken by the function. CPU time of the program is a much more accurate 
    measurement than real-time, as it does not depend on what other processes are running on
    the computer. As a result it is a much more consistent result than real-time and will not 
    vary as much when taken multiple times.
    However, because the ackermann function for inputs (3,6) is still relatively quick, running
    it once will give a clock() difference of 0. The solution is to test how long it takes to run
    the function a large number of times, then divide the time by this large number. The extra code
    in the for loop (the comparison and incrementing the counter) are negligible compared to the
    total run time of the function, so can be ignored. The time measured to execute ackermann on
    average is the same when exectued 100 times, 1000 times or 10000 times. We can see from this
    that the method truly gives us an average run time.

*/