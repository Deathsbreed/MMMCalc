#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>
#include <getopt.h>
#include <unistd.h>

// Version string
const char *version = "v0.5";
// Whether or not the calculations should be verbose
bool verboseFlag = false;

// Function that prints help info
inline void printHelp();

int main(int argc, char **argv) {
    // There should always be at least be 2 arguments and no more than 4
    if(argc <= 1) {
        fprintf(stderr, "No arguments were provided. Use `-h' for help.\n");
        return 1;
    }

    // Variable where the argument is stored
    int arg;
    // If help info should be printed
    bool helpFlag = false;
    // If version info should be printed
    bool versionFlag = false;
    // The file to read the numbers from
    char *infile = NULL;

    while((arg = getopt(argc, argv, "hvVf:")) != -1) {
        switch(arg) {
            case 'h':
                // Only 2 arguments can be used
                if(argc != 2) {
                    fprintf(stderr, "Invalid use of arguments. Use `-h' for help.\n");
                    return 1;
                }
                helpFlag = true;
                break;

            case 'v':
                verboseFlag = true;
                break;

            case 'V':
                // Only 2 arguments can be used
                if(argc != 2) {
                    fprintf(stderr, "Invalid use of arguments. Use `-h' for help.\n");
                    return 1;
                }
                versionFlag = true;
                break;

            case 'f':
                // There needs to be between 3 and 4 arguments
                if(argc > 4 || argc < 3) {
                    fprintf(stderr, "Invalid use of arguments. Use `-h' for help.\n");
                }
                // Set the input file
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
                // If we get anything else that's weird just abort
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

    // Print copyright & license information
    printf("MMMCalc %s, Copyright (c) 2016 Nicolás A. Ortega.\n", version);
    printf("This program comes with ABSOLUTELY NO WARRANTY.\n");
    printf("This program is free software and you are welcome to redistribute\n");
    printf("under the terms and conditions of the GNU GPLv3 or higher.\n\n");

    // Open a file as read only
    FILE *numfile = fopen(infile, "r");
    // Temporary variable to see how many numbers are in this file
    double num;
    // The size of the coming nums array
    int size = 0;
    // Get the size for the array
    while(fscanf(numfile, "%lf", &num) == 1) size++;
    rewind(numfile);
    printf("There are %d numbers in the file.\n", size);
    // Create the array with that size
    double nums[size];
    for(int i = 0; i < size; i++) {
        // Store result of fscanf in err
        int err = fscanf(numfile, "%lf", &nums[i]);
        // If fscanf returned error exit out of here
        if(err != 1) {
            fprintf(stderr, "Error reading entire file.\n");
            return 1;
        }
        // If not let's continue printing
        printf("%f\n", nums[i]);
    }
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
