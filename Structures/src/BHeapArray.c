#include <limits.h>
#include <malloc.h>

#include "BHeapArray.h"

#ifndef NULL
#define NULL (void*) 0
#endif

// Convenience/clarity macros for finding children and parents
#define LEFT_CHILD(x) (2*(x) + 1)
#define RIGHT_CHILD(x) (2*(x) + 2)
#define PARENT(x) (((x) - 1) / 2)

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
void* BHA_findMin(BHeapArray* heap)
{
    // Root node is at the beginning of the array
    return heap->data[0];
}

/**
 * Returns whether the heap is empty
 */
bool BHA_isEmpty(BHeapArray* heap) 
{
    // Empty if the size is 0
    return heap->size == 0;
}

/**
 * Returns whether the heap is full
 */
bool BHA_isFull(BHeapArray* heap)
{
    // Empty is the size equals the array length
    return heap->arrLength == heap->size;
}


// MODIFYING THE HEAP

/**
 * Inserts the item into the array
 */
void BHA_insert(BHeapArray* heap, int key, void* data)
{
    // Add to the heap in the next empty space
    int index = heap->size;
    heap->keys[index] = key;
    heap->data[index] = data;
    heap->size++;

    // Propagate upwards until this key is smaller than its parent
    // Keep going up, as long as:
    //   a) This is not the root; and
    //   b) The parent has a smaller key than this node
    while (index != 0 && heap->keys[PARENT(index)] < heap->keys[index]) {
        // Swap with the parent
        BHA_swapItems(heap, PARENT(index), index);
    }
}

/**
 * Deletes the minimum value, returning its key and value
 */
IVPair BHA_deleteMin(BHeapArray* heap)
{
    // Make the data pair to return
    IVPair root;
    root.key = heap->keys[0];
    root.datum = heap->data[0];

    // Copy the rightmost item to the root
    heap->keys[0] = heap->keys[heap->size - 1];
    heap->data[0] = heap->data[heap->size - 1];

    // Delete the rightmost node
    // (if the size is reduced the things there will be overwritten eventually)
    // Set it to be large, effectively at the back of the queue
    heap->keys[heap->size - 1] = INT_MAX;
    heap->size--;

    // Begin propagation
    // Starting at the root, if a child is smaller, pick the smaller child and
    // swap with them
    int index = 0;
    while (LEFT_CHILD(index) < heap->size) {
        if (heap->keys[LEFT_CHILD(index)] > heap->keys[index] && 
            heap->keys[RIGHT_CHILD(index)] > heap->keys[index]) {
                // This key is smaller than both children
                break;
        }

        // Swap with the smallest child
        if (heap->keys[LEFT_CHILD(index)] < heap->keys[RIGHT_CHILD(index)]) {
            BHA_swapItems(heap, index, LEFT_CHILD(index));
        } else {
            BHA_swapItems(heap, index, RIGHT_CHILD(index));
        }
    }
}

/**
 * Swaps around the keys and data for the two indices
 */
void BHA_swapItems(BHeapArray* heap, int index1, int index2)
{
    // Record the parent's key and data
    int tempKey = heap->keys[index1];
    void* tempData = heap->data[index1];

    // Swap the keys
    heap->keys[index1] = heap->keys[index2];
    heap->keys[index2] = tempKey;

    // Swap the data
    heap->data[index1] = heap->data[index2];
    heap->data[index2] = tempData;
}