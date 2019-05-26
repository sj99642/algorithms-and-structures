#ifndef Queue_H
#define Queue_H

#include "unistd.h"
#include "stdbool.h"

/**
 * Holds the queue data. The int* is an array of length maxLength. start is
 * the index of the first item, and nextEmpty is the index of the first unoccupied 
 * item. This will not be implemented as a circular queue, but if an item is 
 * added and the nextEmpty pointer is too far along, if there is space to the
 * start full of unused values, everything will be shifted back along.
 */
typedef struct Queue {
    int* values;
    int start;
    int nextEmpty;
    int maxLength;
} Queue;

Queue* initialiseQueue(Queue* queue, int length);
bool isQueueEmpty(Queue* queue);
bool isQueueFull(Queue* queue);
void enqueue(Queue* queue, int value);
int nextInQueue(Queue* queue);
int dequeue(Queue* queue);

#endif