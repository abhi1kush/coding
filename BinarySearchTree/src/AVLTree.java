public class AVLTree extends BalancedBinarySearchTree{

    AVLTree() {
        super();
    }

    protected int balanceFactor(BstNode x) {
        if (x == null) {
            return 0;
        }

        return heightRec(x.getLeftChild()) - heightRec(x.getRightChild());
    }

    public void balanceTree(BstNode x) {

        if (x == null) {
            //System.out.println("Passed null for balancing.");
            return;
        }

        //Balance the tree.
        BstNode currNode = x;
        BstNode currNodeChild = null;
        BstNode currNodeGrandChild = null;

        while (currNode != null) {
            currNodeGrandChild = currNodeChild;
            currNodeChild = currNode;
            currNode = currNode.getParent();

            if (currNode == null) {
                break;
            }

            int balance = Math.abs(balanceFactor(currNode));

            if ( balance > 1) {
                if (currNodeChild != null && currNode.getLeftChild() == currNodeChild) {
                    if (currNodeGrandChild != null && currNodeChild.getRightChild() == currNodeGrandChild) { //[Left Right] -> Left Rotate currNodeChild [Left Left]
                        //System.out.println("Left-Right");
                        leftRotate(currNodeChild);
                    }
                    // [Left Left] -> Right Rotate currNode.
                    //System.out.println("Left-Left");
                    rightRotate(currNode);
                    currNode = currNodeGrandChild; //current top node after rotations.
                    currNodeChild = null;
                }
                else if(currNodeChild != null && currNode.getRightChild() == currNodeChild) {
                    if (currNodeGrandChild != null && currNodeChild.getLeftChild() == currNodeGrandChild) { //[Right Left] -> Right Rotate [Right Right]
                        //System.out.println("Right-Left");
                        rightRotate(currNodeChild);
                    }
                    //[Right Right] -> Left Rotate currNode.
                    //System.out.println("Right-Right");
                    leftRotate(currNode);
                    currNode = currNodeGrandChild; //current top node after rotations.
                    currNodeChild = null;
                }
            }
        }

    }

    //insert
    @Override
    public void insert(Object key, Object data) {
        BstNode newNode = new BstNode(key, data);
        BstNode insertedNode = (BstNode) insertUtil(newNode);
        balanceTree(insertedNode);
    }

    //delete
    @Override
    public void delete(Object key) {
        BstNode parentNode = deleteUtil(key);
        balanceTree(parentNode);
    }
}
