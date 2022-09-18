import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        System.out.println("----- BST -----");
        BST bst = new BST();

        Integer[] arr = {50, 20, 70, 15, 70, 5, 17, 65, 75, 7, 6};
        Integer[] arr3 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 0, 20, 34, 56};

        for (int i = 0; i < arr.length; i++) {
            bst.insert(arr[i], arr[i]);
        }

        bst.inOrderTraversal();
        System.out.println("height : " + bst.heightRec(bst.getRoot()));

        BST bst2 = new BST();
        for (int i = 0; i < arr3.length; i++) {
            bst2.insert(arr3[i], arr3[i]);
        }
        bst2.inOrderTraversal();
        System.out.println("height : " + bst2.heightRec(bst2.getRoot()));

        ////Height test.
        //System.out.println("----- Height Test -----");
        //BalancedBinarySearchTree bst2 = new BalancedBinarySearchTree();
        //Integer[] arr2 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 0};

        //for (int i = 0; i < arr2.length; i++) {
        //    bst2.insert(arr2[i], arr2[i]);
        //}

        //BstNode root = bst2.getRoot();
        //if(bst2.height(root) == 10) {
        //    System.out.println("height test passed");
        //}

        System.out.println("----- AVL -----");
        AVLTree tree = new AVLTree();
        for (int i = 0; i < arr3.length; i++) {
            tree.insert(arr3[i], arr3[i]);
        }

        BstNode root = tree.getRoot();

        System.out.println("height : " + tree.height(root));

        tree.inOrderTraversal();

        //Red Black Tree.
        System.out.println("----- Red Black Tree -----");
        RedBlackTree rbTree = new RedBlackTree();
        for (int i = 0; i < arr3.length; i++) {
            rbTree.insert(arr3[i], arr3[i]);
        }
        root = rbTree.getRoot();

        System.out.println("height : " + rbTree.height(root));

        rbTree.inOrderTraversal();

        //random array insert.
        final int skewPoint = 5000;
        final int arrForInsertSize = 15000;
        final int insertRandomBound = 2 * arrForInsertSize;
        ArrayList<Integer> arrForInsert = new ArrayList<Integer>();
        for (int i = 0; i < arrForInsertSize; i++) {
            Integer randomNum = skewedRandom(insertRandomBound, skewPoint);
            arrForInsert.add(randomNum);
        }

        for (int x : arrForInsert) {
            System.out.print(x + ", ");
        }
        System.out.println("");

        //graphs x axis number of insertion y axis height.
        System.out.println("graphs x axis number of insertion y axis height");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("insert_exp.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BST bstExpInsert = new BST();
        AVLTree avlExpInsert = new AVLTree();
        RedBlackTree redBlackExpInsert = new RedBlackTree();

        for (int i = 0; i < arrForInsertSize; i++) {
            //System.out.println(arrForInsertSize + " " + i);
            try {
                bstExpInsert.insert(arrForInsert.get(i), arrForInsert.get(i));
                avlExpInsert.insert(arrForInsert.get(i), arrForInsert.get(i));
                redBlackExpInsert.insert(arrForInsert.get(i), arrForInsert.get(i));
            }catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            try {
                writer.write(i + " " + bstExpInsert.height(bstExpInsert.getRoot()) +
                        " " + avlExpInsert.height(avlExpInsert.getRoot()) +
                        " " + redBlackExpInsert.height(redBlackExpInsert.getRoot())+ "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //graphs x axis increasing set of searches y axis time taken by a set of searches.
        System.out.println("graphs x axis increasing set of searches y axis time taken by a set of searches.");
        try {
            writer = new BufferedWriter(new FileWriter("search_exp.txt", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final int graphPoints = 50000;
        final int searchBound = 2 * insertRandomBound;
        Random rand = new Random();
        ArrayList<Integer> searchKeys = new ArrayList<Integer>();
        for (int i = 1; i < graphPoints; i++) {
            searchKeys.clear();
            for (int j = 0; j < i; j++) {
                searchKeys.add(rand.nextInt(searchBound));
            }
            Date startBstSearch = new Date();
            for (Integer x : searchKeys) {
                bstExpInsert.search(x);
            }
            Date endBstSearch = new Date();
            long bstTimeTaken = endBstSearch.getTime() - startBstSearch.getTime();

            Date startAvlSearch = new Date();
            for (Integer x : searchKeys) {
                avlExpInsert.search(x);
            }
            Date endAvlSearch = new Date();
            long avlTimeTaken = endAvlSearch.getTime() - startAvlSearch.getTime();

            Date startRedBlackSearch = new Date();
            for (Integer x : searchKeys) {
                redBlackExpInsert.search(x);
            }
            Date endRedBlackSearch = new Date();
            long redBlackTimeTaken = endRedBlackSearch.getTime() - startRedBlackSearch.getTime();

            try {
                writer.write(i + " " + bstTimeTaken + " " + avlTimeTaken + " " + redBlackTimeTaken + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //graphs x axis number of insertion and deletion y axis height.


        //graphs x axis number of insertion y axis cumulative number of rotations or recoloring.

        //graphs x axis number of deletion y axis cumulative number of rotations or recoloring.

        //graphs x axis number of insertion and deletion y axis cumulative number of rotations or recoloring.

    }

    static int skewedRandom(int bound, int skewPoint) {
        Random rand = new Random();
        int a = rand.nextInt(bound);
        int b;
        for (int i = 0; i < 7; i++) {
            b = rand.nextInt(bound);
            if (Math.abs(skewPoint - a) > Math.abs(skewPoint - b)) {
                a = b;
            }
        }
        b = rand.nextInt(bound);
        if (b % 2 == 0) {
          a = b;
        }
        return a;

    }
}
