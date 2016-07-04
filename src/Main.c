#include <stdio.h>

// 0 = false;
// 1 = true;
char version[16] = "v0.5";
int verbose = 0;

int main(/*int argc, char **argv*/) {
    printf("MMMCalc %s, Copyright (c) 2016 Nicolás A. Ortega.\n", version);
    printf("This program comes with ABSOLUTELY NO WARRANTY.\n");
    printf("This program is free software and you are welcome to redistribute\n");
    printf("under the terms and conditions of the GNU GPLv3 or higher.\n\n");
    return 0;
}
