#ifndef BHeap_H
#define BHeap_H

#include "BTree.h"

typedef BTree BHeap;

void* BH_findMin(BHeap* heap);
int BH_findDepth(BHeap* heap);


// Data modification
BHeap* BH_insert(BHeap* node, BHeap* heap);

#endif