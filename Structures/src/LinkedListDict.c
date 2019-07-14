#include <stdio.h>

#include "LinkedListDict.h"

// For some reason VSCode doesn't know what NULL is
#ifndef NULL
#define NULL (void*) 0
#endif

/**
 * Goes all the way to the end of the list and adds the item.
 */
LinkedListDict* LLD_addLast(LinkedListDict* list, LinkedListDict* newItem)
{
    // If this list is already null, just return the item as the first item
    if (list == NULL) return newItem;

    // Iterate through the list until list->next is NULL
    while (list->next != NULL)
    {
        list = list->next;
    }

    // Now list->next is NULL, so add the new item
    list->next = newItem;
}

/**
 * Adds something to the start of the list.
 */
LinkedListDict* LLD_addFirst(LinkedListDict* list, LinkedListDict* newItem)
{
    newItem->next = list;
    return newItem;
}

/**
 * Removes the item with the given key in the list.
 */
LinkedListDict* LLD_deleteByKey(LinkedListDict* list, int key)
{
    // If already NULL, nothing can be done
    if (list == NULL) return list;

    // Iterate through until the next item is null
    while (list->next != NULL)
    {
        // Check if the next item needs deleting
        if (list->next->key == key)
        {
            // Delete the next item by linking this to its next
            list->next = list->next->next;
        }
    }
}

/**
 * Puts the given item at the given place in the list. 
 */
LinkedListDict* LLD_insert(LinkedListDict* list, LinkedListDict* item, int place)
{
    // Special case: if the item is to go at the start
    if (place == 0) {
        item->next = list;
        return item;
    }

    // Make a version of the list to move along
    LinkedListDict* current = list;

    // Move onto the next until this one will follow 'current'
    for (; place > 1; place--)
    {
        if (current->next != NULL) {
            current = current->next;
        } else {
            return list;
        }
    }

    // Add the item after 'current'
    item->next = current->next;
    current->next = item;

    // Return the list
    return list;
}

/**
 * Prints out the keys in the list
 */
LinkedListDict* LLD_printList(LinkedListDict* list)
{
    while (list != NULL)
    {
        printf("%d ", list->key);
        list = list->next;
    }
    printf("\n");
}

/**
 * Seeks through the list and returns the value corresponding to the given key
 */
void* LLD_getByKey(LinkedListDict* list, int key)
{
    while (list != NULL) {
        // See if this is a match and return
        if (list->key == key) {
            return list->value;
        }

        // Progress down the list
        list = list->next;
    }

    // If no matching value is found
    return NULL;
}