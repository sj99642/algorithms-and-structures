#ifndef LinkedList_H
#define LinkedList_H

typedef struct LinkedList {
    struct LinkedList* next;
    int key;
    void* value;
} LinkedList;

LinkedList* addLast(LinkedList* list, LinkedList* newItem);
LinkedList* addFirst(LinkedList* list, LinkedList* newItem);
LinkedList* deleteByKey(LinkedList* list, int key);
LinkedList* insert(LinkedList* list, LinkedList* item, int place);
LinkedList* printList(LinkedList* list);

#endif