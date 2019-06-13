#ifndef BHeap_H
#define BHeap_H

/**
 * Holds everything needed for an array-based heap. The length is just the length
 * of the arrays, and will be used to prevent overflows. `keys` is the array of keys
 * (the raw heap itself), and `data` is an array of void*s. This can be unused, or
 * it can hold a piece of data whose key is at the corresponding position in the key
 * array.
 */
typedef struct {
    int length;
    int* keys;
    void** data;
} BHeapArray;

void* BHA_findMin(BHeapArray* heap);
int BHA_findDepth(BHeapArray* heap);


// Data modification
BHeapArray* BHA_insert(BHeapArray* node, BHeapArray* heap);

#endif