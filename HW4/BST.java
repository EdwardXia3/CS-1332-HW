import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;
/**
 * Your implementation of a binary search tree.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        for (T var:data) {
            add(var);
        }
    }

    @Override
    public void add(T data) {
        root = addRecursion(root, data);
    }

    /**
     * Helper function for add that assists for recursion by providing an
     * node input and output.
     *
     * @param root a node of a BST
     * @param data the data being inserted
     * @return the node Pointer
     */
    private BSTNode<T> addRecursion(BSTNode<T> root, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        } else if (root == null) {
            root = new BSTNode<>(data);
            size++;
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(addRecursion(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(addRecursion(root.getRight(), data));
        }
        return root;
    }
    /**
     * Helper function for the get function that assists for recursion by
     * providing an node input and output
     * @param root a node of the BST
     * @param data the data being remove
     * @param dummy placeholder to store data
     * @return the node pointer
     */
    private BSTNode<T> removeRecursion(BSTNode<T> root, T data,
                                       BSTNode<T> dummy) {
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
                    dummy.setLeft(new BSTNode<>(root.getData()));
                    BSTNode<T> dummy2 = new BSTNode<>(null);
                    root.setLeft(findPred(root.getLeft(), dummy2));
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
        return root;
    }

    /**
     * Finds the predecessor of the node being removed.
     * @param root the node being removed from the tree
     * @param dummy2 placeholder for the data being removed
     * @return the predecessor of the node
     */
    private BSTNode<T> findPred(BSTNode<T> root, BSTNode<T> dummy2) {
        if (root.getRight() != null) {
            root.setRight(findPred(root.getRight(), dummy2));
        } else {
            dummy2.setLeft(root);
            return root.getLeft();
        }
        return root;
    }
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        dummy.setLeft(root);
        if (size == 1) {
            root = null;
        } else {
            root = removeRecursion(root, data, dummy);
        }
        size--;
        return dummy.getLeft().getData();
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        return getRecursion(root, data).getData();
    }

    /**
     * Helper function for the get function that assists for recursion by
     * providing an node input and output
     * @param root a node of the BST
     * @param data the data being found
     * @return the node pointer
     */
    private BSTNode<T> getRecursion(BSTNode<T> root, T data) {
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
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null");
        }
        return containsRecursion(root, data);
    }

    /**
     * Helper method to assist in recursion of the contains method by providing
     * a node input and output
     * @param root a node of the BST
     * @param data the data being looked for
     * @return whether the data is found within the tree
     */
    private boolean containsRecursion(BSTNode<T> root, T data) {
        if (data.compareTo(root.getData()) == 0) {
            return true;
        } else if ((data.compareTo(root.getData()) < 0) && root.getLeft()
                != null && containsRecursion(root.getLeft(), data)) {
            return true;
        }
        return (data.compareTo(root.getData())) > 0 && root.getRight() != null
                && containsRecursion(root.getRight(), data);
    }
    /**
     * Helper method to assist in recursion of the contains method by providing
     * a node input and output
     * @param list the list containing data  from the tree
     * @param root a node of the BST
     * @param orderType 1:preorder, 2:postorder, 3:inorder
     * @return whether the data is found within the tree
     */
    private List<T> orderRecursion(List<T> list, BSTNode<T> root,
                                   int orderType) {
        //PreOrder
        if (root == null) {
            return list;
        }
        if (orderType == 1) {
            list.add(root.getData());
            list = orderRecursion(list, root.getLeft(), 1);
            list = orderRecursion(list, root.getRight(), 1);
        }
        if (orderType == 2) {
            list = orderRecursion(list, root.getLeft(), 2);
            list = orderRecursion(list, root.getRight(), 2);
            list.add(root.getData());
        }
        if (orderType == 3) {
            list = orderRecursion(list, root.getLeft(), 3);
            list.add(root.getData());
            list = orderRecursion(list, root.getRight(), 3);
        }
        return list;
    }

    @Override
    public List<T> preorder() {
        LinkedList<T> list = new LinkedList<T>();
        return orderRecursion(list, root, 1);
    }

    @Override
    public List<T> postorder() {
        LinkedList<T> list = new LinkedList<>();
        return orderRecursion(list, root, 2);
    }

    @Override
    public List<T> inorder() {
        LinkedList<T> list = new LinkedList<>();
        return orderRecursion(list, root, 3);
    }

    @Override
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        LinkedList<T> out = new LinkedList<>();
        if (root == null) {
            return out;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.remove();
            out.add(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return out;
    }

    /**
     * Determines the common ancestor of both data points in the tree
     * @param root a node of the BST
     * @param data1 a piece of data from the tree
     * @param data2 a piece of data from the tree
     * @return the common ancestor
     */
    private BSTNode<T> commonAncestor(BSTNode<T> root, T data1, T data2) {
        if (root == null) {
            throw new NoSuchElementException("Neither Data is not in the tree");
        }
        if ((data1.compareTo(root.getData()) < 0)
                && (data2.compareTo(root.getData()) < 0)) {
            return commonAncestor(root.getLeft(), data1, data2);
        } else if ((data1.compareTo(root.getData()) > 0)
                && (data2.compareTo(root.getData()) > 0)) {
            return commonAncestor(root.getRight(), data1, data2);
        }
        return root;
    }
    @Override
    public int distanceBetween(T data1, T data2) {
        if ((data1 == null) || (data2 == null)) {
            throw new IllegalArgumentException("Data can not be null");
        }
        if (data1.compareTo(data2) == 0) {
            return 0;
        }
        int dist = 0;
        BSTNode<T> node = commonAncestor(root, data1, data2);
        BSTNode<T> tempNode = node;
        while (tempNode.getData() != data1) {
            if (tempNode.getData().compareTo(data1) > 0) {
                tempNode = tempNode.getLeft();
                dist++;
            } else if (tempNode.getData().compareTo(data1) < 0) {
                tempNode = tempNode.getRight();
                dist++;
            }
            if (tempNode == null) {
                throw new NoSuchElementException("Data1 is not in the tree");
            }
        }
        tempNode = node;
        while (tempNode.getData() != data2) {
            if (tempNode.getData().compareTo(data2) > 0) {
                tempNode = tempNode.getLeft();
                dist++;
            } else if (tempNode.getData().compareTo(data2) < 0) {
                tempNode = tempNode.getRight();
                dist++;
            }
            if (tempNode == null) {
                throw new NoSuchElementException("Data2 is not in the tree");
            }
        }
        return dist;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    /**
     * Helper method to assist in recursion of the height method by providing
     * a node input and output
     * @param root a node of the BST
     * @return the height of the tree
     */
    private int heightRecursive(BSTNode<T> root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(heightRecursive(root.getLeft()),
                    heightRecursive(root.getRight()));
        }
    }
    @Override
    public int height() {
        return heightRecursive(root);
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
