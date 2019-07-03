#ifndef HashTable_H
#define HashTable_H

#include "LinkedList.h"

typedef struct {
    LinkedList* data;
    int numEntries;     // The number of entries in the table
    int size;           // The number of buckets
} HashTable;

// Hash generation
int HT_hash(int key, int size);

// Table access
void* HT_get(HashTable* table, int key);

// Table modification
void HT_put(HashTable* table, int key, void* value);
void HT_delete(HashTable* table, int key);


#endif