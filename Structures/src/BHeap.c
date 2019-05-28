#include "BHeap.h"

#ifndef NULL
#define NULL (void*) 0
#endif

/**
 * Returns the datum of the root (i.e. minimum key) of the heap
 */
void* BH_findMin(BHeap* heap)
{
    if (heap == NULL) {
        return NULL;
    }

    return heap->datum;
}