/**
 * @file: BST.java
 * @description: This program implements the Binary Search Tree. The binary search tree helps store
 * data in a way that allows for efficient searching, insertion, and deletion of delements. It has a stored
 * structure that enables quick access to data.
 * @author: Massie Flippin
 * @date: September 19th , 2024
 ************************/
import java.util.Iterator;
import java.util.Stack;

class BST<T extends Comparable<T>> implements Iterable<T>{
    private Node<T> root; // Root of the BST
    private int nodecount; //Number of nodes in the BST
    // Implement the constructor
    BST(){root = null; nodecount= 0;}
// Implement the clear method
    public void clear(){root = null; nodecount = 0;}

// Implement the size method
    public int size(){return nodecount;}

// Implement the insert method
    public void insert(T e){
        root = inserthelp(root, e);
        nodecount++;
        }
//Inserthelp method
    private Node<T> inserthelp(Node<T> rt, T key){
        if(rt == null){
            return new Node<T>(key);
        }
        if(rt.value().compareTo(key)>0){
            rt.setLeft(inserthelp(rt.left(), key));
        }
        else{
            rt.setRight(inserthelp(rt.right(), key));
        }
        return rt;
    }
    // Implement the remove method
    //Remove a record into the tree.
    //Records can be anything, but they must be Comparable

    public T remove(T key) {
        T temp = findhelp(root, key); // First find it
        if(temp != null){
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }
    //removehelp method
    //This method is a recursive function that helps remove a node from a BST.
    //Searches for the node containing the key to be removed and then handling the removal
    private Node<T> removehelp(Node<T> rt, T key){
        if(rt == null){
            return null;
        }//tree assignment
        if(rt.value().compareTo(key) > 0){
            rt.setLeft(removehelp(rt.left(), key));
        } else if (rt.value().compareTo(key)<0) {
            rt.setRight(removehelp(rt.right(), key));
        }
        else { //found it, remove it
            if (rt.left() == null)
                return rt.right();
            else if (rt.right() == null)
                return rt.left();
            else { //two children
                Node<T> temp = getMax(rt.left());
                rt.setValue(temp.value());
                rt.setLeft(deleteMax(rt.left()));
            }
        }
        return rt;
    }
    //get max method finds the max value
    private Node<T> getMax(Node<T> rt) {
        if(rt == null){
            return null;
        }
        while(rt.right() != null){
            rt = rt.right();
        }
        return rt;
    }
    // deleteMax method, deletes the max node
    private Node<T> deleteMax(Node<T> rt) {
        if(rt == null){
            return null;
        }
        if(rt.right() == null){
            return rt.left();
        }
        rt.setRight(deleteMax(rt.right()));
        return rt;
    }

// Implement the search method
    public T find(T key){
        return findhelp(root, key);
    }

// Implement the iterator method
    public Iterator<T> iterator(){
      return new BSTIterator(root);
    }

// Implement the BSTIterator class
   private class BSTIterator implements Iterator<T>{
        private final Stack<Node<T>> stack;

        //Constructor initializes the stack with the leftmost path
    public BSTIterator(Node<T> root){
        stack = new Stack<>();
        pushLeftNodes(root);
    } // pushes all left node of a given subtree onto a stack.
    private void pushLeftNodes(Node<T> node){
        while(node != null){
            stack.push(node);
            node = node.left();
        }
    }

    // indicates that a method declaration is intended to override a method
    // declaration in a super type.
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> node = stack.pop();
        if(node.right() != null){
            pushLeftNodes(node.right());
        }
        return node.value();
    }
}
//Finding whether an Element exists in the BST
    private T findhelp(Node<T> rt, T key){
        if(rt == null) return null;
        if(rt.value().compareTo(key)>0){
            return findhelp(rt.left(), key);
        } else if (rt.value().compareTo(key) ==0){
            return rt.value();
      }
        else return findhelp(rt.right(), key);
    }
}