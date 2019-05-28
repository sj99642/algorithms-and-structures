#ifndef BTree_H
#define BTree_H


typedef struct {
    int key;
    void* datum;
    BTree* left;
    BTree* right;
} BTree;


#endif