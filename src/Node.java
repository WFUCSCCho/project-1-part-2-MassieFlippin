/**
 @file: BST.java
 @description: This program implements a generic binary tree node that can store a value of type T and have
 references to two child nodes.
 @author: Massie Flippin
 @date: September 19th , 2024
 ************************/

//defines a generic binary node structure
interface BinNode<T>{
    public T value();
    //sets value in teh new node
    public void setValue(T v);
}
//Node class representing a binary tree node implemting both BinNOde and Comparable
public class Node<T extends Comparable<? super T>> implements BinNode<T>, Comparable<Node<T>>{
    //Private fields to hold the nodes value and its left and right children
    private T value;
    private Node<T> left;
    private Node<T> right;
    // Implement the constructor
    Node(){left = right = null;}
    Node(T val){ left = right = null; value = val;}
    Node(T val, Node<T> l, Node<T> r){
        left = l; right = r; value = val;
    }
    // Implement the setElement method
    public T value(){return value;}

    @Override
    public void setValue(T v){
        value = v;
    }

// Implement the setLeft method
    public Node<T> left(){return left;}
    public void setLeft(Node<T>p){
        left = p;
    }

// Implement the setRight method
    public Node<T> right(){return right;}
    public void setRight(Node<T>p){right = p;}

// Implement the getLeft method
    public Node<T> getLeft(){
        return left;
    }

// Implement the getRight method
    public Node<T> getRight(){
        return right;
    }

// Implement the getElement method
    public T getValue(){
        return value;
    }

// Implement the isLeaf method
    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.value.compareTo(o.value);
    }
}