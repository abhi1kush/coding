import java.util.ArrayList;

class TrieNode {
    TrieNode[] childrenArr;
    boolean isEndOfWord;

    public  final int ALPHABET_SIZE = 26;
    TrieNode() {
        this.isEndOfWord = false;
        childrenArr = new TrieNode[ALPHABET_SIZE];
    }

    public TrieNode(boolean isEndOfWord) {
        this.childrenArr = new TrieNode[ALPHABET_SIZE];
        this.isEndOfWord = isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public boolean isLeafNode() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (null != this.childrenArr[i]) {
                return false;
            }
        }
        return true;
    }
}

public class Trie extends TrieNode{
    TrieNode root;
    String alphabets = "abcdefghijklmnopqrstuvwxyz";

    private int charToIndex(char ch) {
        return  alphabets.indexOf(ch);
    }

    public void createTrie() {
        root = new TrieNode();
    }

    Trie() {
        createTrie();
    }

    public TrieNode createNode(boolean isEndOfWord) {
        TrieNode node = new TrieNode(isEndOfWord);
        return node;
    }

    public boolean isEmpty() {
        if (null == root) {
            return true;
        }
        if (root.isLeafNode()) {
            return true;
        }
        return false;
    }

    public void insert(String str) {
        if (null == root) {
            createTrie();
        }
        TrieNode currNode = root;
        TrieNode newNode;
        for (int i = 0; i < str.length(); i++) {
            if (null == currNode.childrenArr[charToIndex(str.charAt(i))]) {
                currNode.childrenArr[charToIndex(str.charAt(i))] = createNode(false);
            }
            currNode = currNode.childrenArr[charToIndex(str.charAt(i))];
        }
        currNode.setEndOfWord(true);
    }

    public boolean contains(String str) {
        if (null == root) {
            return false;
        }

        TrieNode currNode = root;
        for (int i =0; i < str.length(); i++) {
            if (null == currNode.childrenArr[charToIndex(str.charAt(i))]) {
                return false;
            }
            currNode = currNode.childrenArr[charToIndex(str.charAt(i))];
        }
        return currNode.isEndOfWord();
    }

    public void autoCompleteHelper(ArrayList<String> list, TrieNode currNode, String str) {
        if (null == currNode) {
            return;
        }

        if (currNode.isEndOfWord()) {
            list.add(str);
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (null != currNode.childrenArr[i]) {
                autoCompleteHelper(list, currNode.childrenArr[i], str + Character.toString(alphabets.charAt(i)));
            }
        }
    }

    public ArrayList<String> autoComplete(String str) {
        ArrayList<String> list = new ArrayList<>();
        TrieNode currNode = root;

        if (null == root) {
            return list;
        }

        //reach the node representing last char of str.
        for (int i = 0; i < str.length(); i++) {
            if (null == currNode.childrenArr[charToIndex(str.charAt(i))]) {
                return list;
            }
            currNode = currNode.childrenArr[charToIndex(str.charAt(i))];
        }

        if (null == currNode || currNode.isLeafNode()) {
            return list;
        }

        //traverse all paths using recursion.
        autoCompleteHelper(list, currNode, str);

        return list;
    }

    private void deleteHelper(TrieNode currNode, String str, int index) {
        if (index >= str.length() || null == currNode) {
            return;
        }

        deleteHelper(currNode.childrenArr[charToIndex(str.charAt(index))], str, index + 1);

        if (str.length() - 1 == index) {
            currNode.childrenArr[charToIndex(str.charAt(index))].setEndOfWord(false);
        }

        if (currNode.childrenArr[charToIndex(str.charAt(index))].isLeafNode()) {
            currNode.childrenArr[charToIndex(str.charAt(index))] = null;
        }
    }

    public void delete(String str) {
        if (null == root) {
            return;
        }

        if (!this.contains(str)) {
            return;
        }

        TrieNode currNode = root;
        deleteHelper(currNode, str, 0);
    }

}
