import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

interface Comparable {
    Object getKey();
    boolean lessThan(Comparable A);
    boolean isEqual(Comparable A);
}

class BstNode implements Comparable{
    private BstNode parent; //will be used for balanced BSTs, AVL andd Red Black Trees.
    protected BstNode leftChild;
    protected BstNode rightChild;

    private Object key;
    private Object data;

    BstNode()
    {
        this.parent = null;
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BstNode getParent() {
        return parent;
    }

    public void setParent(BstNode parent) {
        this.parent = parent;
    }


    @Override
    public Object getKey() {
        return key;
    }

    public boolean lessThan(Comparable A) {
        return ((Integer)this.key).intValue() < ((Integer)A.getKey()).intValue();
    }

    public boolean isEqual(Comparable A) {
        return ((Integer)this.key).intValue() == ((Integer)A.getKey()).intValue();
    }

    BstNode(Object key, BstNode leftChild, BstNode rightChild, Object data) {
        this();
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public BstNode(Object key) {
        this();
        this.key = key;
    }

    public BstNode(Object key, Object data) {
        this();
        this.key = key;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    void setData(Object data) {
        this.data = data;
    }

    void setKey(Object key) {
        this.key = key;
    }

    protected void setLeftChild(BstNode node) {
        leftChild = node;
        if (node != null) {
            node.setParent(this);
        }
    }

    protected void setRightChild(BstNode node) {
        rightChild = node;
        if (node != null) {
            node.setParent(this);
        }
    }

    BstNode getLeftChild()
    {
        return leftChild;
    }

    BstNode getRightChild()
    {
        return rightChild;
    }

    void processNode() {
        System.out.println((Integer)key + " " + (Integer)data);
    }

    boolean isLeafNode() {
        return ((this.getRightChild() == null) && (this.getLeftChild() == null));
    }
}

public class BST {
    protected boolean updateAllowed;
    protected BstNode root;

    BST(){
        updateAllowed = true;
        root = null;
    }

    public boolean isUpdateAllowed() {
        return updateAllowed;
    }

    public void setUpdateAllowed(boolean updateAllowed) {
        this.updateAllowed = updateAllowed;
    }

    public BstNode getRoot() {
        return root;
    }

    BstNode insertUtil(BstNode newNode) {
        if(null == root) {
            root = newNode;
            return root;
        }

        BstNode currNodeParent = null;
        BstNode currNode = root;

        while (currNode != null && !newNode.isEqual(currNode)) {
            currNodeParent = currNode;
            if (newNode.lessThan(currNode)) {
                currNode = currNode.getLeftChild();
            }
            else if (currNode.lessThan(newNode)) {
                currNode = currNode.getRightChild();
            }
            else {
                if (isUpdateAllowed()) {
                    currNode.setData(newNode.getData());
                }
                return currNode;
            }
        }

        if (null == currNode) {
            if (newNode.lessThan(currNodeParent)) {
                currNodeParent.setLeftChild(newNode);
            } else {
                currNodeParent.setRightChild(newNode);
            }
        } else {
            if (updateAllowed) {
                currNode.setData(newNode.getData()); //key exists in tree: update data operation.
                return null;
            }
        }
        return newNode;
    }

    public void insert(Object key, Object data) {
        BstNode newNode = new BstNode(key, data);
        insertUtil(newNode);
    }

    BstNode inOrderSuccUtil(BstNode x) {
        BstNode currNode = x;
        BstNode currNodeParent = null;
        BstNode currNodeGrandParent = null;

        //go right
        currNode = currNode.getRightChild();
        if (currNode == null) {
            return null;
        }

        while (currNode != null) {
            currNodeGrandParent = currNodeParent;
            currNodeParent = currNode;
            currNode = currNode.getLeftChild();
        }

        if (currNodeParent.getRightChild() != null && currNodeGrandParent != null) {
            currNodeGrandParent.setLeftChild(currNodeParent.getRightChild());
        }

        if (currNodeGrandParent != null) {
            currNodeGrandParent.setLeftChild(null);
        }

        return currNodeParent;
    }

    BstNode search(Object key) {

        if (null == root) {
            return null;
        }

        BstNode currNode = root;
        BstNode newNode = new BstNode(key);

        while (currNode != null && !newNode.isEqual(currNode)) {
            if (newNode.lessThan(currNode)) {
                currNode = currNode.getLeftChild();
            }
            else if (currNode.lessThan(newNode)) {
                currNode = currNode.getRightChild();
            }
        }

        return currNode;
    }

    protected BstNode deleteUtil(Object key) {
        BstNode node = search(key);
        BstNode parentNode = node.getParent();
        BstNode inorderSucc = inOrderSuccUtil(node);
        if (inorderSucc == node.getRightChild()) {
            inorderSucc.setLeftChild(node.getLeftChild());
        } else {
            inorderSucc.setLeftChild(node.getLeftChild());
            inorderSucc.setRightChild(node.getRightChild());
        }
        node.setRightChild(null);
        node.setLeftChild(null);
        return parentNode;
    }

    void delete(Object key) {
        deleteUtil(key);
    }

    void inOrderTraversalRecUtil(BstNode x) {
        if (x == null) {
            return;
        }

        inOrderTraversalRecUtil(x.getLeftChild());
        x.processNode();
        inOrderTraversalRecUtil(x.getRightChild());
    }

    void inOrderTraversalRec() {
        if (root == null) {
            return;
        }

        inOrderTraversalRecUtil(root);
    }


    void inOrderTraversal() {
        inOrderTraversal(root);
    }

    void inOrderTraversal(BstNode x) {
        if (x == null) {
            return;
        }

        Set<BstNode> visited = new HashSet<BstNode>();
        Stack<BstNode> stk = new Stack<>();

        stk.push(x);

        while (!stk.empty()) {
            BstNode currNode = stk.peek();
            BstNode child;

            //Go to left child first.
            child = currNode.getLeftChild();
            if ( child == null || visited.contains(child)) {
                currNode.processNode();
                visited.add(currNode);
                stk.pop();

                if (currNode.getRightChild() != null) {
                    stk.push(currNode.getRightChild());
                }
            }
            else {
                stk.push(child);
            }
        }
    }

    int heightRec(BstNode x) {
        if (x == null) {
            return 0;
        }

        return Math.max(heightRec(x.getLeftChild()), heightRec(x.getRightChild())) + 1;
    }

    int height(BstNode x) {
        return  heightRec(x);
    }
}