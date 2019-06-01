#ifndef Graph_H
#define Graph_H

typedef struct {
    int label;
    GraphEdge* outEdges;
} GraphNode;

typedef struct {
    int weight;
    GraphNode* to;
} GraphEdge;

#endif