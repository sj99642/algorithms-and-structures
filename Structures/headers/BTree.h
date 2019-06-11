#ifndef BTree_H
#define BTree_H


typedef struct BTree {
    int key;
    void* datum;
    struct BTree* left;
    struct BTree* right;
} BTree;


#endif