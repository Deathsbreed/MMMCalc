#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>
#include <getopt.h>
#include <unistd.h>

const char *version = "v0.5";
bool helpFlag = false;
bool versionFlag = false;
bool verboseFlag = false;
char *infile = NULL;

inline void printHelp();

int main(int argc, char **argv) {
    int arg;

    while((arg = getopt(argc, argv, "hvVf:")) != -1) {
        switch(arg) {
            case 'h':
                helpFlag = true;
                break;

            case 'v':
                verboseFlag = true;
                break;

            case 'V':
                versionFlag = true;
                break;

            case 'f':
                infile = optarg;
                break;

            case '?':
                if(optopt == 'f') {
                    fprintf(stderr, "Option -%c requires an argument.\n", optopt);
                } else if(isprint(optopt)) {
                    fprintf(stderr, "Unknown option `-%c'.\n", optopt);
                } else {
                    fprintf(stderr, "Unknown character `\\x%x'.\n", optopt);
                }
                return 1;

            default:
                abort();
        }
    }

    if(helpFlag) {
        printHelp();
        return 0;
    } else if(versionFlag) {
        printf("%s\n", version);
        return 0;
    }

    printf("MMMCalc %s, Copyright (c) 2016 Nicolás A. Ortega.\n", version);
    printf("This program comes with ABSOLUTELY NO WARRANTY.\n");
    printf("This program is free software and you are welcome to redistribute\n");
    printf("under the terms and conditions of the GNU GPLv3 or higher.\n\n");
    return 0;
}

void printHelp() {
    printf("Usage: mmmcalc [options]\n");
    printf("Options:\n");
    printf(" -f <file> -- The file to read variables from.\n");
    printf(" -h -- Show this help information.\n");
    printf(" -v -- Be verbose, prints the equations used.\n");
    printf(" -V -- Print the version number.\n");
}
