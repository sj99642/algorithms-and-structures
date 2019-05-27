#ifndef BSTree_H
#define BSTree_H

#include <stdbool.h>

typedef struct BSTree {
    int key;
    void* datum;
    BSTree* left;
    BSTree* right;
} BSTree;

BSTree* BST_empty();
BSTree* BST_build(int key, void* datum, BSTree* left, BSTree* right);
bool BST_search(BSTree* tree, int key);
int BST_count(BSTree*);

// Traversals
void BST_printInOrder(BSTree* tree);
void BST_printPostOrder(BSTree* tree);
void BST_printPreOrder(BSTree* tree);

// Accessing elements
BSTree* BST_find(BSTree* tree, int key);
BSTree* BST_findMin(BSTree* tree);
BSTree* BST_findMax(BSTree* tree);
BSTree* BST_predecessor(BSTree* node, BSTree* tree);

// Data modification
BSTree* BST_insert(BSTree* addition, BSTree* tree);
BSTree* BST_delete(BSTree* key, BSTree* tree);


#endif