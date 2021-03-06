#include <malloc.h>

#include "HashTable.h"
#include "LinkedListDict.h"

/**
 * Takes in a key and the table size, and returns a hash. 
 */
int HT_hash(int key, int tableSize)
{
    return key % tableSize;
}

/**
 * Return the value of the entry with the given key
 */
void* HT_get(HashTable* table, int key)
{
    // "table->data" gets the array, and adding the hash gets a pointer to the right box
    return LLD_getByKey(table->data + HT_hash(key, table->size), key);
}

/**
 * Puts the given key->value pair into the table
 */
void HT_put(HashTable* table, int key, void* value)
{
    // Using pointer arithmetic to get a pointer to the bucket
    LinkedListDict* bucket = table->data + HT_hash(key, table->size);

    // Make the new item
    LinkedListDict* newItem = (LinkedListDict*) malloc(sizeof(LinkedListDict));
    newItem->key = key;
    newItem->value = value;

    // Add to the list
    bucket = LLD_addFirst(bucket, newItem);

    // Write the new bucket back to the table
    table->data[HT_hash(key, table->size)] = *bucket;
}

/**
 * Deletes the item of the given key
 */
void HT_delete(HashTable* table, int key)
{
    LLD_deleteByKey(table->data + HT_hash(key, table->size), key);
}