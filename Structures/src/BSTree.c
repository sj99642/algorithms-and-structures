#include <stdbool.h>

#include "BSTree.h"

#ifndef NULL
#define NULL (int*) 0
#endif

/**
 * Creates in memory a new blank binary search tree
 */
BSTree* BST_empty() {
    return (BSTree*) malloc(sizeof(BSTree));
}

/**
 * Makes a new binary search tree and initialises it as directed
 */
BSTree* BST_build(int key, void* datum, BSTree* left, BSTree* right) {
    BSTree* bst = BST_empty();
    bst->key = key;
    bst->datum = datum;
    bst->left = left;
    bst->right = right;
    return bst;
}

/**
 * Searches through the BST and returns true if an item has the given key
 */
bool BST_search(BSTree* bst, int key) {
    if (bst == NULL) {
        return false;
    } else if (bst->key == key) {
        return true;
    } else if (key < bst->key) {
        return BST_search(bst->left, key);
    } else {
        return BST_search(bst->right, key);
    }
}

/**
 * Searches through the BST and returns the subtree (recursvely) with the given key,
 * or NULL otherwise.
 */
BSTree* BST_find(BSTree* bst, int key) {
    if (bst == NULL) {
        return NULL;
    } else if (bst->key == key) {
        return bst;
    } else if (key < bst->key) {
        return BST_search(bst->left, key);
    } else {
        return BST_search(bst->right, key);
    }
}

/**
 * Counts the number of items in this tree, going all the way down.
 */
int BST_count(BSTree* bst) {
    // If this is already NULL, it doesn't count to the total
    if (bst == NULL) {
        return 0;
    }

    // One for this, plus the totals for each sub-tree
    return 1 + BST_count(bst->left) + BST_count(bst->right);
}

// TRAVERSALS


void BST_printInOrder(BSTree* tree) {
    if (tree != NULL) {
        BST_printInOrder(tree->left);
        printf("%d\n", tree->key);
        BST_printInOrder(tree->right);
    }
}

void BST_printPostOrder(BSTree* tree) {
    if (tree != NULL) {
        BST_printInOrder(tree->left);
        BST_printInOrder(tree->right);
        printf("%d\n", tree->key);
    }
}

void BST_printPreOrder(BSTree* tree) {
    if (tree != NULL) {
        printf("%d\n", tree->key);
        BST_printInOrder(tree->left);
        BST_printInOrder(tree->right);
    }
}

// Accessing elements

/**
 * Returns the node with the smallest key
 */
BSTree* BST_findMin(BSTree* tree) {
    if (tree == NULL) {
        return NULL;
    }

    // Progress leftwards until there is no further left to go
    if (tree->left == NULL) {
        return tree;
    } else {
        return BST_findMin(tree);
    }
}

/**
 * Returns the node with the largest key
 */
BSTree* BST_findMax(BSTree* tree) {
    if (tree == NULL) {
        return NULL;
    }

    // Progress rightwards until there is no further left to go
    if (tree->right == NULL) {
        return tree;
    } else {
        return BST_findMax(tree);
    }
}

/**
 * Returns the parent within `tree` of `node`
 */
BSTree* BST_parent(BSTree* node, BSTree* tree) {
    // If they are already the same, there is no parent
    if (node == tree) {
        return NULL;
    }

    // If the node is directly left or right of this tree, this tree is the parent
    if (tree->left == node || tree->right == node) {
        return tree;
    }

    // If not, work out whether the node is in the left or right sub-tree, and ask the question there
    if (node->key < tree->key) {
        return BST_parent(node, tree->left);
    } else {
        return BST_parent(node, tree->right);
    }
}

/**
 * Returns the predecessor of `node` in the larger tree `tree`. The predecessor is the
 * node with the next key down.
 */
BSTree* BST_predecessor(BSTree* node, BSTree* tree) {
    // If this is the minimum, there is no predecessor
    if (node == BST_findMin(tree)) {
        return NULL;
    }

    // If this is true, the predecessor is the largest left child
    if (node->left != NULL) {
        return BST_findMax(node->left);
    }

    // If there is no left node, the predecessor is the first ancestor which
    // has a key smaller than this
    BSTree* p = node;
    while (p->key >= node->key) {
        p = BST_parent(p, tree);
    }

    // Finally found a parent we are not right of
    return p;
}

/**
 * Returns the successor of `node` in the larger tree `tree`. The successor is the
 * node with the next key up.
 */
BSTree* BST_successor(BSTree* node, BSTree* tree) {
    // If this is the maximum, there is no successor
    if (node == BST_findMax(tree)) {
        return NULL;
    }

    // If this is true, the successor is the smallest right child
    if (node->right != NULL) {
        return BST_findMin(node->left);
    }

    // If there is no right node, the successor is the first ancestor which
    // has a key bigger than this
    BSTree* p = node;
    while (p->key <= node->key) {
        p = BST_parent(p, tree);
    }

    // Finally found a parent we are not right of
    return p;
}


// DATA MODIFICATION

/**
 * Inserts this node into the correct position in the tree
 */
BSTree* BST_insert(BSTree* addition, BSTree* tree) {
    if (tree == NULL) {
        return addition;
    }

    if (addition->key < tree->key) {
        return insert(addition, tree->left);
    } else if (addition->key > tree->key) {
        return insert(addition, tree->right);
    } else {
        // They are equal, so overwrite this one
        return addition;
    }
}

/**
 * Deletes the node represented by the root of the given tree. 
 * Returns that tree.
 */
BSTree* BST_delete(BSTree* toRemove, BSTree* tree) {
    if (toRemove == NULL) {
        // Nothing to actually remove
        return tree;
    } else if (toRemove->left == NULL && toRemove->right == NULL) {
        // No children, so safe to just get rid of
        return _BST_replaceByIn(toRemove, NULL, tree);
    } else if (toRemove->left == NULL) {
        // Remove, pulling its right child up to its own place
        return _BST_replaceByIn(toRemove, toRemove->right, tree);
    } else if (toRemove->right == NULL) {
        // Remove, pulling its left child up to its own place
        return _BST_replaceByIn(toRemove, toRemove->left, tree);
    } else {
        // Parent of two children - has its own procedure.
        return _BST_deleteParentOfTwo(toRemove, tree);
    }
}

/**
 * Used by BST_delete to replace a node with another
 */
BSTree* _BST_replaceByIn(BSTree* toRemove, BSTree* replacement, BSTree* tree) {
    BSTree* parent = BST_parent(toRemove, tree);
    if (toRemove == tree) {
        tree = replacement;
    } else if (toRemove == parent->left) {
        // This node is to its parent's left
        parent->left = replacement;
    } else {
        // This node is to its parent's right
        parent->right = replacement;
    }

    return tree;
}

/**
 * Used by BST_delete to deal with deleting a node that has two children
 */
BSTree* _BST_deleteParentOfTwo(BSTree* node, BSTree* tree) {
    BSTree* replacement = BST_predecessor(node, tree);

    // Overwrite this by its predecessor, then delete the predecessor
    node->key = replacement->key;
    node->datum = replacement->datum;

    // This deletion will be easy. If we are in this function, then the
    // node is a parent of two, so it has a left child. Its predecessor
    // is the largest value in that left child's tree, so it will have no
    // children. 
    BST_delete(replacement, tree);
}