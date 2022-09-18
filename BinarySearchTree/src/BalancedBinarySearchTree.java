import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public  abstract class BalancedBinarySearchTree extends BST{

    BalancedBinarySearchTree() {
        super();
    }

    void leftRotate(BstNode x) {
        BstNode y = (BstNode) x.getRightChild();
        if (y == null) {
            System.out.println("\nnode : " + x.getKey() + " does not have right child invalid Left Rotate.");
            return;
        }
        //System.out.println("Left Rotate " + x.getKey());
        x.setRightChild(y.getLeftChild());

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRightChild()) {
            x.getParent().setRightChild(y);
        } else {
            x.getParent().setLeftChild(y);
        }

        y.setLeftChild(x);
    }

    void rightRotate(BstNode x) {
        BstNode y = (BstNode) x.getLeftChild();
        if (y == null) {
            System.out.println("\nnode : " +x.getKey() + " does not have left child invalid Right Rotate.");
            return;
        }
        //System.out.println("Right Rotate " + x.getKey());
        x.setLeftChild(y.getRightChild());

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRightChild()) {
            x.getParent().setRightChild(y);
        } else {
            x.getParent().setLeftChild(y);
        }

        y.setRightChild(x);
    }

    public abstract void insert(Object key, Object data);
    public abstract void delete(Object key);
}
