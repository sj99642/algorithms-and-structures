#include "BHeapArray.h"

#ifndef NULL
#define NULL (void*) 0
#endif

#define LEFT_CHILD(x) (2*(x) + 1)
#define RIGHT_CHILD(x) (2*(x) + 1)

// Creating new heaps

/**
 * Create a new heap of the given length (this function will handle memory)
 */
BHeapArray* BHA_createEmpty(int length)
{
    // Create a new heap in memory (on the other heap) and initialise it
    BHeapArray* newHeap = (BHeapArray*) malloc(sizeof(BHeapArray));
    BHA_initialise(newHeap, length);
    return newHeap;
}

void BHA_initialise(BHeapArray* heap, int length)
{
    heap->arrLength = length;
    heap->size = 0;
    heap->keys = (int*) malloc(sizeof(int) * length);
    heap->data = (void**) malloc(sizeof(void*) * length);
}


// FINDING DATA

/**
 * Returns the datum of the root (i.e. minimum key) of the heap
 */
void* BH_findMin(BHeapArray* heap)
{
    // Root node is at the beginning of the array
    return heap->data[0];
}

/**
 * Inserts the item into the array
 */