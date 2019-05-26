#include "Queue.h"

#include <stdlib.h>
#include <limits.h>

#ifndef NULL
#define NULL (void*) 0
#endif

Queue* initialiseQueue(Queue* queue, int length)
{
    queue->values = (int*) malloc(length * sizeof(int));
    queue->start = 0;
    queue->nextEmpty = 0;
    queue->maxLength = length;
}

bool isQueueEmpty(Queue* queue)
{
    return queue->nextEmpty == queue->start;
}

bool isQueueFull(Queue* queue)
{
    // The next item is beyond the end and there is no space at the start
    return (queue->nextEmpty == queue->maxLength) && 
           (queue->start == 0);
}

void enqueue(Queue* queue, int value)
{
    // First check if we are at the end
    if (queue->nextEmpty < queue->maxLength) {
        // There is space! Good.
        queue->values[queue->nextEmpty] = value;
        queue->nextEmpty++;
        return;
    }

    // We are at the end. If there is no space before the start, it is full.
    if (queue->start == 0) {
        return;
    }

    // There is some space at the start. Shift everything as left as it will go.
    for (int i = queue->start; i < queue->nextEmpty; i++) {
        // Loop through everything that has a real value
        queue->values[i - queue->start] = queue->values[i];
    }
    // Now bring the values back
    queue->nextEmpty -= queue->start;
    queue->start = 0;

    // Finally, we can add the value in as normal
    queue->values[queue->nextEmpty] = value;
    queue->nextEmpty++;
}

int nextInQueue(Queue* queue)
{
    // If empty there is no next value
    if (queue->start == queue->nextEmpty) {
        return INT_MIN;
    }

    return queue->values[queue->start];
}

int dequeue(Queue* queue)
{
    // If empty there is no value to dequeue
    if (queue->start == queue->nextEmpty) {
        return INT_MIN;
    }

    // Move the start along before we return, since it can't happen after
    queue->start += 1;
    return queue->values[queue->start - 1];
}