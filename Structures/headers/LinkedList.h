#ifndef LinkedList_H
#define LinkedList_H

typedef struct LinkedList {
    struct LinkedList* next;
    void* value;
} LinkedList;

LinkedList* LL_addLast(LinkedList* list, void* newValue);
LinkedList* LL_delete(LinkedList* list, int pos);
LinkedList* LL_insert(LinkedList* list, void* newValue, int pos);
void* LL_get(LinkedList* list, int pos);


#endif