#include <malloc.h>

#include "LinkedList.h"

#ifndef NULL
#define NULL (void*) 0
#endif

/**
 * Progress along the list until the end, adding this to the end
 */
LinkedList* LL_addLast(LinkedList* list, void* newValue)
{
    // Make the list object
    LinkedList* newItem = (LinkedList*) malloc(sizeof(LinkedList));
    newItem->next = NULL;
    newItem->value = newValue;

    // Special case - if the list is just empty, this new value
    if (list == NULL) {
        return newItem;
    }

    // Navigate to the end of the list
    LinkedList* current = list;
    while (current->next != NULL) {
        // As long as there is a next one, progress along to it
        current = current->next;
    }

    // Next item is now NULL, so this is the last item
    current->next = newItem;
    return list;
}

/**
 * Deletes the item at the given position from the list
 */
LinkedList* LL_delete(LinkedList* list, int pos)
{
    // If pos==0, then just delete the first simply
    if (pos == 0) {
        if (list == NULL) {
            // There is nothing to delete - still empty
            return NULL;
        } else {
            return list->next;
        }
    }

    // Progress along to the next item until the next one is the one to be deleted
    LinkedList* current = list;
    while (pos > 1) {
        if (current == NULL) {
            // Already at the end of the list. Just return NULL.
            return NULL;
        }

        current = current->next;
        pos--;
    }

    // The next item is the one to be deleted
    if (current->next == NULL) {
        // Nothing to be deleted anyway
        return list;
    }

    // Keep a record in order to free the memory
    LinkedList* deleted = current->next;

    // Plug the pointers into the right place
    current->next = current->next->next;

    // Delete the other from the memory
    free(deleted);

    // Return the modified list
    return list;
}

/**
 * Inserts the given value into the position given
 */
LinkedList* LL_insert(LinkedList* list, void* newValue, int pos)
{
    // Make the list object
    LinkedList* newItem = (LinkedList*) malloc(sizeof(LinkedList));
    newItem->next = NULL;
    newItem->value = newValue;

    // If it is to go at the start, hook that up correctly
    if (pos == 0) {
        newItem->next = list;
        return newItem;
    }

    // Now go along until the item is to be placed next
    LinkedList* current = list;
    while (pos > 1) {
        if (current == NULL) {
            // Already at the end, just return null
            return NULL;
        }

        current = current->next;
        pos--;
    }

    // If the current item is NULL, this item is being added too late
    if (current == NULL) {
        return NULL;
    }

    // The item is to be placed next. Hook up the new item to the one after.
    newItem->next = current->next;
    
    // Hook up this one to the new item
    current->next = newItem;

    // Return the list
    return NULL;
}

/**
 * Gets the value at the given position
 */
void* LL_get(LinkedList* list, int pos)
{
    // Go along the list until we have reached the right position
    while (pos > 0) {
        if (list == NULL) {
            // List is too short
            return NULL;
        }

        list = list->next;
        pos--;
    }

    // Reached the right position
    if (list == NULL) {
        return NULL;
    }

    return list->value;
}