#ifndef Stack_H
#define Stack_H

#include "unistd.h"
#include "stdbool.h"

typedef struct Stack {
    int* values;
    int maxLength;
    int stackPointer;
} Stack;

Stack* initialiseStack(Stack* stack, int length);
bool isStackEmpty(Stack* stack);
bool isStackFull(Stack* stack);
void pushStack(Stack* stack, int value);
int stackTop(Stack* stack);
int stackPop(Stack* stack);


#endif