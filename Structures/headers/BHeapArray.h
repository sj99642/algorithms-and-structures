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
    int arrLength;
    int size;
    int* keys;
    void** data;
} BHeapArray;

typedef struct {
    int key;
    void* datum;
} IVPair;

// Creating heaps
BHeapArray* BHA_createEmpty(int length);
void BHA_initialise(BHeapArray* heap, int length);

// Reading data
void* BHA_findMin(BHeapArray* heap);
bool BHA_isEmpty(BHeapArray* heap);
bool BHA_isFull(BHeapArray* heap);


// Data modification
void BHA_insert(BHeapArray* heap, int key, void* data);
IVPair BHA_deleteMin(BHeapArray* heap);
void BHA_swapItems(BHeapArray* heap, int index1, int index2);

#endif