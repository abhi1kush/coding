import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Trie trie = new Trie();

        trie.insert("carpentar");
        System.out.println("carpentar " + trie.contains("carpentar"));
        System.out.println("car " + trie.contains("car"));
        trie.insert("car");
        System.out.println("car " + trie.contains("car"));
        System.out.println("carpentar " + trie.contains("carpentar"));

        trie.insert("sun");
        trie.insert("sunlight");
        trie.insert("sunshine");
        trie.insert("sunshiv");
        trie.insert("sunson");

        trie.delete("sun");
        trie.delete("sunson");
        System.out.println("sun " + trie.contains("sun"));

        ArrayList<String> list = trie.autoComplete("su");
        System.out.print("[");
        for (String str : list) {
            System.out.print(str + ", ");
        }
        System.out.println("]");

        Trie trie1 = new Trie();
        trie1.insert("cat");
        System.out.println(trie1.contains("cat"));
        trie1.delete("cat");
        System.out.println("isEmpty " + trie1.isEmpty());
    }
}
