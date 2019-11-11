add r0, #4, R9  ;   need to initialise global varable g

min:
;   int min(int a, int b, int c){
;   parameters a:r26 b:r27 c:r28
    add r26, r0, r1    ;   int v = a
    sub r27, r1, r0 {C};   if(b<v)
    jle min0            ;   {
    xor r0, r0, r0      ;       nop in delay slot
    add r27, r0, r1     ;       v = b
min0:               ;   }
    sub r28, r1, r0 {C} ;   if(c < v)
    jge min1            ;   {
    xor r0, r0, r0      ;       nop in delay slot
    add r28, r0, r1     ;       v = b
min1:                   ;   }
    ret r25, 0          ;   return v
    xor r0, r0, r0      ;   nop in delay slot

p:
;   int p(int i, int j, int k, int l){
;   parameters i:r26 j:27 k:r28 l:r29
    add r9, r0, r10     ;   g as parameter for first min
    add r26, r0, r11    ;   i as parameter for first min
    call r25, min       ;   min(g, i, j)
    add r27, r0, r12    ;   j as parameter for first min
    add r1, r0, r10     ;   min as parameter for second min
    add r28, r0, r11    ;   k as parameter for second min
    call r25, min       ;   min(min, k, j)
    add r29, r0, r12    ;   j as paramter for second min
    ret r25, 0          ;   return min
    xor r0, r0, r0      ;   nop in delay slot

gcd:
;   gcd(int a, int b){
;   parameters a:r26, b:r27
    sub r28, r0, r0 {C} ;   if(b==0)
    jne gcd0            ;   {
    xor r0, r0, r0      ;       nop in delay slot
    ret r25, 0          ;       return a
    add r27, r0, r1     ;   } (a as return value)
gcd0                    ;   else{
    add r26, r0, r10    ;       a as parameter for mod
    call r25, mod       ;       mod(a, b)
    add r27, r0, r11    ;       b as parameter for mod
    add r27, r0, r10    ;       b as parameter for gcd
    call r25, gcd       ;       return gcd(b, mod)
    add r1, r0, r11     ;       mod as parameter for gcd
    ret r25, 0          ;   }    
    xor r0, r0, r0      ;   nop in delay slot