import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }
    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        for (int i = 0; i < data.size(); i++) {
            this.add((T) data.toArray()[i]);
        }
    }

    /**
     * Performs a right rotation to maintain a balanced tree
     * @param node the node being rotated
     * @return the parent node
     */
    private AVLNode<T> rightRotate(AVLNode<T> node) {
        AVLNode<T> n1 = node.getLeft();
        AVLNode<T> n2 = n1.getRight();
        n1.setRight(node);
        node.setLeft(n2);
        if (node.getLeft() != null) {
            if (node.getRight() != null) {
                node.setHeight(Math.max(node.getLeft().getHeight(),
                        node.getRight().getHeight()) + 1);
                node.setBalanceFactor(node.getLeft().getHeight()
                        - node.getRight().getHeight());
            } else {
                node.setHeight(n1.getLeft().getHeight() + 1);
                node.setBalanceFactor(node.getLeft().getHeight() + 1);
            }
        } else {
            if (node.getRight() != null) {
                node.setHeight(node.getRight().getHeight() + 1);
                node.setBalanceFactor(-1 * node.getRight().getHeight() - 1);
            } else {
                node.setHeight(0);
                node.setBalanceFactor(0);
            }
        }
        if (n1.getLeft() != null) {
            if (n1.getRight() != null) {
                n1.setHeight(Math.max(n1.getLeft().getHeight(),
                        n1.getRight().getHeight()) + 1);
                n1.setBalanceFactor(n1.getLeft().getHeight()
                        - n1.getRight().getHeight());
            } else {
                n1.setHeight(n1.getLeft().getHeight() + 1);
                n1.setBalanceFactor(n1.getLeft().getHeight() + 1);
            }
        } else {
            if (n1.getRight() != null) {
                n1.setHeight(n1.getRight().getHeight() + 1);
                n1.setBalanceFactor(-1 * n1.getRight().getHeight() - 1);
            } else {
                n1.setHeight(0);
                n1.setBalanceFactor(0);
            }
        }
        return n1;
    }

    /**
     * Performs a left rotate to maintain a balanced tree.
     * @param node the node being rotated
     * @return the parent node
     */
    private AVLNode<T> leftRotate(AVLNode<T> node) {
        AVLNode<T> n1 = node.getRight();
        AVLNode<T> n2 = n1.getLeft();
        n1.setLeft(node);
        node.setRight(n2);
        if (node.getLeft() != null) {
            if (node.getRight() != null) {
                node.setHeight(Math.max(node.getLeft().getHeight(),
                        node.getRight().getHeight()) + 1);
                node.setBalanceFactor(node.getLeft().getHeight()
                        - node.getRight().getHeight());
            } else {
                node.setHeight(node.getLeft().getHeight() + 1);
                node.setBalanceFactor(node.getLeft().getHeight() + 1);
            }
        } else {
            if (node.getRight() != null) {
                node.setHeight(node.getRight().getHeight() + 1);
                node.setBalanceFactor(-1 * node.getRight().getHeight() - 1);
            } else {
                node.setHeight(0);
                node.setBalanceFactor(0);
            }
        }
        if (n1.getLeft() != null) {
            if (n1.getRight() != null) {
                n1.setHeight(Math.max(n1.getLeft().getHeight(),
                        n1.getRight().getHeight()) + 1);
                n1.setBalanceFactor(n1.getLeft().getHeight()
                        - n1.getRight().getHeight());
            } else {
                n1.setHeight(n1.getLeft().getHeight() + 1);
                n1.setBalanceFactor(n1.getLeft().getHeight() + 1);
            }
        } else {
            if (n1.getRight() != null) {
                n1.setHeight(n1.getRight().getHeight() + 1);
                n1.setBalanceFactor(-1 * n1.getRight().getHeight() - 1);
            } else {
                n1.setHeight(0);
                n1.setBalanceFactor(0);
            }
        }
        return n1;
    }

    /**
     * A helper function to assist in the recursion of the add method
     * @param root the current node
     * @param data the data within the node
     * @return the updated node
     */
    private AVLNode<T> addRecursion(AVLNode<T> root, T data) {
        if (root == null) {
            root = new AVLNode<>(data);
            root.setBalanceFactor(0);
            root.setHeight(0);
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(addRecursion(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(addRecursion(root.getRight(), data));
        } else {
            size--;
            return root;
        }
        if (root.getLeft() != null) {
            if (root.getRight() != null) {
                root.setBalanceFactor(root.getLeft().getHeight()
                        - root.getRight().getHeight());
                root.setHeight(Math.max(root.getLeft().getHeight(),
                        root.getRight().getHeight()) + 1);
            } else {
                root.setBalanceFactor(root.getLeft().getHeight() + 1);
                root.setHeight(root.getLeft().getHeight() + 1);
            }
        } else {
            if (root.getRight() != null) {
                root.setBalanceFactor(-1 * root.getRight().getHeight() - 1);
                root.setHeight(root.getRight().getHeight() + 1);
            }
        }
        if (root.getBalanceFactor() < -1) {
            if (root.getRight().getBalanceFactor() == 1) {
                root.setRight(rightRotate(root.getRight()));
                return leftRotate(root);
            } else {
                return leftRotate(root);
            }
        } else if (root.getBalanceFactor() > 1) {
            if (root.getLeft().getBalanceFactor() == -1) {
                root.setLeft(leftRotate(root.getLeft()));
                return rightRotate(root);
            } else {
                return rightRotate(root);
            }
        }
        return root;
    }
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        root = addRecursion(root, data);
        size++;
    }

    /**
     * A helper function to assist in the recursion of the remove function
     * @param root the current node
     * @param data the data within the node
     * @param dummy a placeholder to store the removed data
     * @return the updated node
     */
    private AVLNode<T> removeRecursion(AVLNode<T> root, T data,
                                       AVLNode<T> dummy) {
        if (root == null) {
            throw new NoSuchElementException("Data not found within tree");
        }
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(removeRecursion(root.getLeft(), data, dummy));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(removeRecursion(root.getRight(), data, dummy));
        } else {
            if ((root.getLeft() == null) && (root.getRight() == null)) {
                dummy.setLeft(root);
                return null;
            } else {
                dummy.setLeft(root);
                if ((root.getLeft() != null) && (root.getRight() != null)) {
                    //two children
                    dummy.setLeft(new AVLNode<>(root.getData()));
                    AVLNode<T> dummy2 = new AVLNode<>(null);
                    root.setRight(findSuccessor(root.getRight(), dummy2));
                    root.setData(dummy2.getLeft().getData());
                } else if (root.getLeft() != null) {
                    dummy.setLeft(root);
                    return root.getLeft();
                } else if (root.getRight() != null) {
                    dummy.setLeft(root);
                    return root.getRight();
                }
            }
        }
        if (root.getLeft() != null) {
            if (root.getRight() != null) {
                root.setBalanceFactor(root.getLeft().getHeight()
                        - root.getRight().getHeight());
                root.setHeight(Math.max(root.getLeft().getHeight(),
                        root.getRight().getHeight()) + 1);
            } else {
                root.setBalanceFactor(root.getLeft().getHeight() + 1);
                root.setHeight(root.getLeft().getHeight() + 1);
            }
        } else {
            if (root.getRight() != null) {
                root.setBalanceFactor(-1 * root.getRight().getHeight() - 1);
                root.setHeight(root.getRight().getHeight() + 1);
            } else {
                root.setBalanceFactor(0);
                root.setHeight(0);
            }
        }
        if (root.getBalanceFactor() < -1) {
            if (root.getRight().getBalanceFactor() == 1) {
                root.setRight(rightRotate(root.getRight()));
                return leftRotate(root);
            } else {
                return leftRotate(root);
            }
        } else if (root.getBalanceFactor() > 1) {
            if (root.getLeft().getBalanceFactor() == -1) {
                root.setLeft(leftRotate(root.getLeft()));
                return rightRotate(root);
            } else {
                return rightRotate(root);
            }
        }
        return root;
    }
    /**
     * Finds the Successor of the node being removed.
     * @param root the node being removed from the tree
     * @param dummy2 placeholder for the data being removed
     * @return the successor of the node
     */
    private AVLNode<T> findSuccessor(AVLNode<T> root, AVLNode<T> dummy2) {
        if (root.getLeft() != null) {
            root.setLeft(findSuccessor(root.getLeft(), dummy2));
        } else {
            dummy2.setLeft(root);
            return root.getRight();
        }
        return root;
    }
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        if (root == null) {
            throw new NoSuchElementException("Data is not found in tree.");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        dummy.setLeft(root);
        if (size == 1) {
            root = null;
        } else {
            root = removeRecursion(root, data, dummy);
        }
        size--;
        return dummy.getLeft().getData();
    }
    /**
     * Helper function to assist in the recursion of the get method
     * @param root the current node
     * @param data the data within the node
     * @return the node being traversed
     */
    private AVLNode<T> getRecursion(AVLNode<T> root, T data) {
        if (root == null) {
            throw new NoSuchElementException("Element is not found "
                    + "within the tree.");
        }
        if (data.compareTo(root.getData()) == 0) {
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            return getRecursion(root.getLeft(), data);
        } else if (data.compareTo(root.getData()) > 0) {
            return getRecursion(root.getRight(), data);
        }
        return root;
    }
    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        return getRecursion(root, data).getData();
    }
    /**
     * Helper method to assist in recursion of the contains method by providing
     * a node input and output
     * @param root a node of the AVL
     * @param data the data being looked for
     * @return whether the data is found within the tree
     */
    private boolean containsRecursion(AVLNode<T> root, T data) {
        if (data.compareTo(root.getData()) == 0) {
            return true;
        } else if ((data.compareTo(root.getData()) < 0) && root.getLeft()
                != null && containsRecursion(root.getLeft(), data)) {
            return true;
        }
        return (data.compareTo(root.getData())) > 0 && root.getRight() != null
                && containsRecursion(root.getRight(), data);
    }
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        return containsRecursion(root, data);
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
    /**
     * Finds the predecessor of the node being removed.
     * @param root the node being removed from the tree
     * @return the predecessor of the node
     */
    private T findPred(AVLNode<T> root) {
        if (root.getRight() != null) {
            return findPred(root.getRight());
        } else {
            return root.getData();
        }
    }

    /**
     * A helper function to assist in the recursion of getSecondLargest
     * @param root the curtent node
     * @return the data being retrieved
     */
    private T getSecRecursion(AVLNode<T> root) {
        if (root.getRight() != null) {
            if (root.getRight().getRight() == null) {
                if (root.getRight().getLeft() != null) {
                    return findPred(root.getRight().getLeft());
                }
                return root.getData();
            }
            return getSecRecursion(root.getRight());
        }
        return root.getLeft().getData();
    }
    @Override
    public T getSecondLargest() {
        if (size <= 1) {
            throw new NoSuchElementException("Not enough elements in tree.");
        }
        return getSecRecursion(root);
    }

    /**
     * A helper function to help with the recursion of the equals method by
     * comparing each piece of data between the two nodes
     * @param root the node from the current tree
     * @param root2 the node from the tree being compared to
     * @return whether the trees are equal
     */
    private boolean equalsCompare(AVLNode<T> root, AVLNode<T> root2) {
        if (root == null && root2 == null) {
            return true;
        } else if (root == null || root2 == null) {
            return false;
        }
        if (!root.getData().equals(root2.getData())) {
            return false;
        }
        return (equalsCompare(root.getLeft(), root2.getLeft())
                && equalsCompare(root.getRight(), root.getRight()));
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AVL)) {
            return false;
        }
        AVL<T> c = (AVL<T>) obj;
        if (!equalsCompare(c.root, this.root)) {
            return false;
        }
        return c.size == this.size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
