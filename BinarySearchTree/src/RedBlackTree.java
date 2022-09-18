class RedBlackNode extends BstNode implements Comparable {
    //true: Red, false : Black
    public final boolean RED = true;
    private boolean color;  //Only for Red Black Tree.

    RedBlackNode() {
        this.setParent(null);
        this.color = RED;
    }

    public RedBlackNode(Object key, Object data) {
        super(key, data);
        color = RED;
    }

    public boolean isRed() {
        return color == true;
    }

    public void colorRed() {
        this.color = true;
    }

    public boolean isBlack() {
        return color == false;
    }

    public void colorBlack() {
        this.color = false;
    }
}

public class RedBlackTree extends BalancedBinarySearchTree{
    RedBlackTree() {
        super();
    }

    public RedBlackNode getUncle(RedBlackNode x) {
        RedBlackNode parent = (RedBlackNode) x.getParent();
        if (parent == null) {
            return null;
        }

        RedBlackNode grandParent = (RedBlackNode) parent.getParent();
        if (grandParent == null) {
            return null;
        }

        if (parent == grandParent.getLeftChild()) {
            return (RedBlackNode) grandParent.getRightChild();
        }

        return (RedBlackNode) grandParent.getLeftChild();
    }

    public RedBlackNode getGrandParent(RedBlackNode x) {
        RedBlackNode parent = (RedBlackNode) x.getParent();
        if (parent == null) {
            return null;
        }

        return (RedBlackNode) parent.getParent();
    }

    //insert
    @Override
    public void insert(Object key, Object data) {
        //System.out.println("Inserting in RedBlackTree: " + (Integer)key);
        RedBlackNode newNode = new RedBlackNode(key, data);
        RedBlackNode insertedNode = (RedBlackNode) insertUtil(newNode);
        if (root == insertedNode) {
            insertedNode.colorBlack();       //case 1.
            return;
        }
        balanceRedBlackTree(insertedNode);
    }

    @Override
    public void delete(Object key) {
        RedBlackNode parentNode = (RedBlackNode) deleteUtil(key);
        //TODO
        balanceRedBlackTree(parentNode);
    }

    public void balanceRedBlackTree(RedBlackNode x) {
        if (x == null) {
            return;
        }

        RedBlackNode currNode = x;
        while (currNode.getParent() != null && ((RedBlackNode)currNode.getParent()).isRed()) {
            RedBlackNode grandParent = getGrandParent(currNode);
            if (grandParent == null) {
                ((RedBlackNode)root).colorBlack();
                return;
            }

            if (currNode.getParent() == grandParent.getLeftChild()) {
                RedBlackNode uncle = (RedBlackNode) grandParent.getRightChild();

                if (uncle != null && uncle.isRed()) {//case 2: currNode's uncle is red. -> recolor.
                    ((RedBlackNode) currNode.getParent()).colorBlack();
                    uncle.colorBlack();
                    grandParent.colorRed();
                    currNode = grandParent;
                }
                else {
                    //[Left Right] case 3: currNode's uncle is Black(Triangle) => rotate currNode's parent(line)[Left Left].
                    if (currNode == currNode.getParent().getRightChild()) {
                        currNode = (RedBlackNode) currNode.getParent();
                        leftRotate(currNode);
                    }
                    //[Left Left] case 4: currNode's uncle is black (line). => recolor and rotate currNode's grandparent.
                    ((RedBlackNode) currNode.getParent()).colorBlack();
                    grandParent = getGrandParent(currNode);
                    if (grandParent != null) {
                        grandParent.colorRed();
                    }
                    rightRotate(grandParent);
                }
            }
            else {
                RedBlackNode uncle = (RedBlackNode) grandParent.getLeftChild();

                if (uncle != null && uncle.isRed()) {//case 2: currNode's uncle is red. -> recolor.
                    ((RedBlackNode) currNode.getParent()).colorBlack();
                    uncle.colorBlack();
                    grandParent.colorRed();
                    currNode = grandParent;
                }
                else {
                    //[Right Left] case 3: currNode's uncle is Black(Triangle) => rotate currNode's parent(line)[Right Right].
                    if (currNode == currNode.getParent().getLeftChild()) {
                        currNode = (RedBlackNode) currNode.getParent();
                        rightRotate(currNode);
                    }
                    //[Right Right] case 4: currNode's uncle is black (line). => recolor and rotate currNode's grandparent.
                    ((RedBlackNode) currNode.getParent()).colorBlack();
                    grandParent = getGrandParent(currNode);
                    if (grandParent != null) {
                        grandParent.colorRed();
                    }
                    leftRotate(grandParent);
                }

            }

        }
        ((RedBlackNode)root).colorBlack(); //case 1.
    }
}
