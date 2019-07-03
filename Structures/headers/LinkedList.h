#ifndef LinkedList_H
#define LinkedList_H

typedef struct LinkedList {
    struct LinkedList* next;
    int key;
    void* value;
} LinkedList;

LinkedList* LL_addLast(LinkedList* list, LinkedList* newItem);
LinkedList* LL_addFirst(LinkedList* list, LinkedList* newItem);
LinkedList* LL_deleteByKey(LinkedList* list, int key);
LinkedList* LL_insert(LinkedList* list, LinkedList* item, int place);
LinkedList* LL_printList(LinkedList* list);
LinkedList* LL_listAsArray(LinkedList* list);
void* LL_getByKey(LinkedList* list, int key);

#endif