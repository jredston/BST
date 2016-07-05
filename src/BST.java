

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author juliaredston
 *
 */

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST. YOU DO NOT
     * NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data
     *            the data to add to the tree
     * @throws IllegalArgumentException
     *             if data or any element in data is null
     */
    public BST(Collection<T> data) {
        for (T a : data) {

            add(a);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {

            root = new BSTNode<T>(data);
        } else {

            add(root, data);

        }
    }
    /**
    @param node node we're currently at
    @param data data being added
     */
    private void add(BSTNode<T> node, T data) {
        int i = node.getData().compareTo(data);

        if (i == 0) {

            return;
        }
        if (i < 0) {
            // data is greater than node data
            if (node.getRight() == null) {

                node.setRight(new BSTNode<T>(data));
            } else {
                add(node.getRight(), data);
            }

        } else {
            // data less than node data
            if (node.getLeft() == null) {

                node.setLeft(new BSTNode<T>(data));
            } else {
                add(node.getLeft(), data);
            }
        }

    }

    @Override
    public T remove(T data) {
        if (root == null) {

            throw new java.util.NoSuchElementException();
        }
        if (data == null) {

            throw new IllegalArgumentException();
        }
        if (root.getData().equals(data)) {

            T returnData = root.getData();
            root = null;
            return returnData;
        }
        return remove(root, data);

    }
    /**
    @param node node we're currently at
    @param data data being removed
    @return T data that was removed
    */
    private T remove(BSTNode<T> node, T data) {
        int i = node.getData().compareTo(data);

        if (i < 0) {
            // data is greater than node data
            System.out.println("data greater than node");
            if (node.getRight() == null) {

                throw new java.util.NoSuchElementException();
            } else {
                if (node.getRight().getData().equals(data)) {
                    System.out.println("found our node " + data
                            + " is equal to " + node.getRight().getData());
                    // found our node
                    BSTNode<T> myNode = node.getRight();
                    if (myNode.getLeft() != null && myNode.getRight() != null) {
                        System.out.println("has two children");
                        // has two children
                        BSTNode<T> previous = myNode.getRight();
                        BSTNode<T> current = myNode.getRight();
                        if (current.getLeft() == null) {
                            node.setRight(current);
                            current.setLeft(myNode.getLeft());
                        } else {
                            while (current.getLeft() != null) {
                                previous = current;
                                current = current.getLeft();
                            }
                            BSTNode<T> currentRight = current;
                            while (currentRight.getRight() != null) {
                                currentRight = currentRight.getRight();
                            }

                            current.setLeft(myNode.getLeft());
                            currentRight.setRight(myNode.getRight());
                            previous.setLeft(null);
                            node.setRight(current);
                        }
                        return myNode.getData();

                    } else if (myNode.getLeft() == null
                            && myNode.getRight() == null) {
                        System.out.println("node is leaf");
                        // node is a leaf simply remove it
                        node.setRight(null);
                        return myNode.getData();
                    } else if (myNode.getLeft() != null) {
                        // only a left branch simply replace
                        System.out.println("only left branch");
                        node.setRight(myNode.getLeft());
                        return myNode.getData();
                    } else {
                        // only right branch simply replace
                        System.out.println("only right branch");
                        node.setRight(myNode.getRight());
                        return myNode.getData();
                    }
                } else {
                    return remove(node.getRight(), data);
                }
            }

        } else {
            // data less than node data
            if (node.getLeft() == null) {

                throw new java.util.NoSuchElementException();
            } else {
                if (node.getLeft().getData().equals(data)) {
                    System.out.println("found our node " + data
                            + " is equal to " + node.getLeft().getData());
                    // found our node
                    BSTNode<T> myNode = node.getLeft();
                    if (myNode.getLeft() != null && myNode.getRight() != null) {
                        // has two children
                        System.out.println("two children");
                        BSTNode<T> previous = myNode.getRight();
                        BSTNode<T> current = myNode.getRight();
                        if (current.getLeft() == null) {
                            node.setLeft(current);
                            current.setLeft(myNode.getLeft());
                        } else {
                            while (current.getLeft() != null) {
                                previous = current;
                                current = current.getLeft();
                            }
                            current.setLeft(myNode.getLeft());
                            current.setRight(myNode.getRight());
                            previous.setLeft(null);
                            node.setLeft(current);
                        }

                        return myNode.getData();
                    } else if (myNode.getLeft() == null
                            && myNode.getRight() == null) {
                        // node is a leaf simply remove it
                        System.out.println("node is leaf");

                        node.setLeft(null);
                        return myNode.getData();
                    } else if (myNode.getLeft() != null) {
                        // only a left branch simply replace
                        node.setLeft(myNode.getLeft());
                        return myNode.getData();
                    } else {
                        // only right branch simply replace
                        node.setLeft(myNode.getRight());
                        return myNode.getData();
                    }
                } else {
                    return remove(node.getLeft(), data);
                }
            }
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            throw new java.util.NoSuchElementException();
        }
        BSTNode<T> dataNode = find(root, data);
        if (dataNode == null) {
            // data was not found
            throw new java.util.NoSuchElementException();
        }
        return dataNode.getData();

    }
    /**
    @param node node we're currently at
    @param data data being found
    @return BSTNode of nodes
    */
    private BSTNode<T> find(BSTNode<T> node, T data) {
        int i = node.getData().compareTo(data);

        if (i == 0) {
            return node;
        }
        if (i < 0) {
            // data is greater than node data
            if (node.getRight() == null) {
                return null;
            } else {
                return find(node.getRight(), data);
            }

        } else {
            // data less than node data
            if (node.getLeft() == null) {
                return null;
            } else {
                return find(node.getLeft(), data);
            }
        }

    }

    @Override
    public boolean contains(T data) {
        if (root == null) {
            throw new java.util.NoSuchElementException();
        }
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (find(root, data) == null) {
            return false;
        }
        return true;

    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return (size(root));
    }
    /**
    @param node node we're currently at
    @return int of size
    */
    private int size(BSTNode<T> node) {
        if (node == null) {
            return (0);
        } else {
            return (size(node.getLeft()) + size(node.getRight()) + 1);
        }
    }

    @Override
    public List<T> preorder() {
        List<T> nodes = new ArrayList<T>();
        return preorder(nodes, root);
    }
    /**
    @param nodes list of nodes so far
    @param node currently at
    @return List of data
    */
    private List<T> preorder(List<T> nodes, BSTNode<T> node) {
        if (node != null) {
            nodes.add(node.getData());
            preorder(nodes, node.getLeft());
            preorder(nodes, node.getRight());
        }
        return nodes;
    }

    @Override
    public List<T> postorder() {
        List<T> nodes = new ArrayList<T>();
        return postorder(nodes, root);
    }
    /**
    @param nodes list of nodes so far
    @param node currently at
    @return List of data
    */
    private List<T> postorder(List<T> nodes, BSTNode<T> node) {
        if (node != null) {
            postorder(nodes, node.getLeft());
            postorder(nodes, node.getRight());
            nodes.add(node.getData());

        }
        return nodes;
    }

    @Override
    public List<T> inorder() {
        List<T> nodes = new ArrayList<T>();
        return inorder(nodes, root);
    }
    /**
    @param nodes list of nodes so far
    @param node currently at
    @return List of data
    */
    private List<T> inorder(List<T> nodes, BSTNode<T> node) {
        if (node != null) {
            inorder(nodes, node.getLeft());
            nodes.add(node.getData());
            inorder(nodes, node.getRight());

        }
        return nodes;
    }

    @Override
    public List<T> levelorder() {
        List<T> listData = new LinkedList<T>();
        List<BSTNode<T>> listNodes = new LinkedList<BSTNode<T>>();
        listNodes.add(root);
        while (!listNodes.isEmpty()) {

            BSTNode<T> current = listNodes.remove(0);

            listData.add(current.getData());

            if (current.getLeft() != null) {

                listNodes.add(current.getLeft());
            }
            if (current.getRight() != null) {

                listNodes.add(current.getRight());

            }
        }
        return listData;

    }

    @Override
    public void clear() {
        root = null;

    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 0;
        }
        return height(root);
    }
    /**
    @param node node we're currently at
    @return int of height
    */
    private int height(BSTNode<T> node) {
        if (node == null || (node.getLeft()
                == null && node.getRight() == null)) {
            return 0;
        }
        int h1 = height(node.getLeft());
        int h2 = height(node.getRight());

        if (h1 > h2) {
            return (h1 + 1);
        } else {
            return (h2 + 1);
        }
    }

    /**
     * Compares two BSTs and checks to see if the trees are the same. If the
     * trees have the same data in a different arrangement, this method should
     * return false. This will only return true if the tree is in the exact same
     * arrangement as the other tree.
     *
     * You may assume that you won't get a BST with a different generic type.
     * For example, if this BST holds Strings, then you will not get as an input
     * a BST that holds Integers.
     * 
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     *
     * Should have a running time of O(n).
     * 
     * @param other
     *            the Object we are comparing this BST to
     * @return true if other is equal to this BST, false otherwise.
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other == null || !(other instanceof BST)) {

            return false;
        }
        BST<T> otherBST = (BST<T>) other;
        return equals(root, otherBST.root);
    }
    /**
    @param node our node
    @param other node comparing to
    @return boolean whether its equals or not
    */
    private boolean equals(BSTNode<T> node, BSTNode<T> other) {
        if (node == null && other == null) {

            return true;
        }
        if (node == null || other == null) {

            return false;
        }
        if (node.getData().equals(other.getData())) {

            return (equals(node.getRight(), other.getRight()) && equals(
                    node.getLeft(), other.getLeft()));
        }
        return false;

    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }

    
}
