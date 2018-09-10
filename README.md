# Minimum-Spanning-Tree-Data-Structures

Using graphs, trees, and algorithmic thinking to perform a round robin based algorithm from an undirected graph to make a minimum spanning tree in Java. Project at Rutgers University-New Brunswick for Sesh Venugopal in CS112 Data Structures.

## Objective

Algorithm Implementation
```
1. Create an empty list L of partial trees
2. Separately for each vertex v in the graph:
      a. Create a partial tree T containing only v.
      b. Mark v as belonging to T (this will be implemented in a particular way in the code).
      c. Create a priority queue (heap) P and associate it with T.
      d. Insert all of the arcs (edges) connected to v into P. The lower the weight on an arc, the higher its priority.
      e. Add the partial tree T to the list L.
3. Remove the first partial tree PTX from L. Let PQX be PTX's priority queue.
4. Remove the highest‐priority arc from PQX. Say this arc is α. Let v1 and v2 be the two vertices connected by α, where v1 belongs to PTX.
5. If v2 also belongs to PTX, go back to Step 4 and pick the next highest priority arc, otherwise continue to the next step.
6. Report α ‐ this is a component of the minimum spanning tree.
7. Find the partial tree PTY to which v2 belongs. Remove PTY from the partial tree list L. Let PQY be PTY's priority queue.
8. Combine PTX and PTY. This includes merging the priority queues PQX and PQY into a single priority queue. Append the resulting tree to the end of L.
9. If there is more than one tree in L, go to Step 3.
```
Made using Eclipse(Java) 

### License

All use of this code must comply with the Rutgers University Code of Student Conduct.
