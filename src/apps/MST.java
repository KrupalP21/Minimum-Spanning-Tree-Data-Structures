package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		/* COMPLETE THIS METHOD */
		PartialTreeList treeList = new PartialTreeList();
		for (int i = 0; i < graph.vertices.length; i = i + 1) {
			Vertex current = graph.vertices[i];
			MinHeap <PartialTree.Arc> minHeap = new MinHeap <PartialTree.Arc>();
			PartialTree tree = new PartialTree(graph.vertices[i]);
			while (current.neighbors != null) {
				PartialTree.Arc treeArc = new PartialTree.Arc(graph.vertices[i], current.neighbors.vertex, current.neighbors.weight);
				minHeap.insert(treeArc);
				current.neighbors = current.neighbors.next;
			}
			tree.getArcs().merge(minHeap);
			treeList.append(tree);
		}
		return treeList;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		/* COMPLETE THIS METHOD */
		ArrayList <PartialTree.Arc> edgeArray = new ArrayList <PartialTree.Arc>();
		while (ptlist.size() > 1) {
			PartialTree tree = ptlist.remove();
			MinHeap <PartialTree.Arc> minHeap = tree.getArcs();
			PartialTree.Arc currentMin = minHeap.deleteMin();
			while (currentMin != null) {
			Vertex one = currentMin.v1;
			Vertex two = currentMin.v2;
			PartialTree testTree = ptlist.removeTreeContaining(one);
			if (testTree == null) {
				testTree = ptlist.removeTreeContaining(two);
			}
			if (testTree != null) {
				tree.merge(testTree);
				edgeArray.add(currentMin);
				ptlist.append(tree);
				break;
			} else {

			}
				currentMin = minHeap.deleteMin();	
		}
		}
		return edgeArray;	
}
}