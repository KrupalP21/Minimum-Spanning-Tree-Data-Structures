package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */
    	if (size == 1) {
    		size = size - 1;
    		Node rearNode = rear;
    		rearNode.next = null;
    		rear = null;
    		return rearNode.tree;
    	}
    	if (size > 1) {
    		size = size - 1;
    		Node currentNode = rear.next;
    		rear.next = currentNode.next;
    		currentNode.next = null;
    		return currentNode.tree;
    	}
    	if (size < 1) {
    		throw new NoSuchElementException("No List");
    	}
    		return null;
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */
    	PartialTree partialTree = null;
    	Node tempNode = rear;
        if (tempNode == null) {
            throw new NoSuchElementException("Empty");
        }
        do {
            PartialTree tree = tempNode.tree;
            Vertex  testTree = vertex;
            boolean vertexAdjust;
            while (testTree.parent != testTree) {         
                testTree = testTree.parent;
            }
            if (testTree == tree.getRoot()) {
            vertexAdjust = true;	
            } else {
            	vertexAdjust = false;
            }
            if (vertexAdjust == true) {
                partialTree = tree;
                Node prev;                
                prev = tempNode;
                while ((prev.next == tempNode) == false) {
                    prev = prev.next;
                }
                Node next = tempNode.next;
                if (next == prev) {
                	if (tempNode == rear) {                        
                        rear = rear.next;
                    }
                    (tempNode.next).next = tempNode.next;             
                    size = size - 1;
                }
                else if (next == tempNode) {
                	size = size - 1;
                    rear = null;
                }
                else {
                    if (tempNode == rear) {
                        rear = prev;
                    }
                    prev.next = next;
                    size = size - 1;
                }
               tempNode.next = null;
                break;
            }
            tempNode = tempNode.next;
        } while (tempNode != rear);
        if (partialTree == null) {
            return null;
        }
        else {
            return partialTree;
        }
    }
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


