#ifndef LinkedListDict_H
#define LinkedListDict_H

typedef struct LinkedListDict {
    struct LinkedListDict* next;
    int key;
    void* value;
} LinkedListDict;

LinkedListDict* LLD_addLast(LinkedListDict* list, LinkedListDict* newItem);
LinkedListDict* LLD_addFirst(LinkedListDict* list, LinkedListDict* newItem);
LinkedListDict* LLD_deleteByKey(LinkedListDict* list, int key);
LinkedListDict* LLD_insert(LinkedListDict* list, LinkedListDict* item, int place);
LinkedListDict* LLD_printList(LinkedListDict* list);
LinkedListDict* LLD_listAsArray(LinkedListDict* list);
void* LL_getByKey(LinkedListDict* list, int key);

#endif