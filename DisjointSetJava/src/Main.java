import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {

    static int[] componentsInGraph(int[][] gb) {
        int maxx = Integer.MIN_VALUE;
        for (int i = 0; i < gb.length; i++) {
            maxx = Integer.max(maxx, Integer.max(gb[i][0], gb[i][1]));
        }
        DisjointSetsArrayList disjointSetsArrayList = new DisjointSetsArrayList(maxx);
        DisjointSetsTree disjointSetsTree = new DisjointSetsTree(maxx);
        DisjointSetsTreeHashMap disjointSetsTreeMap = new DisjointSetsTreeHashMap(maxx);

        for (int i = 0; i < gb.length; i++) {
            disjointSetsArrayList.union(gb[i][0], gb[i][1]);
            disjointSetsTree.union(gb[i][0], gb[i][1]);
            disjointSetsTreeMap.union(gb[i][0], gb[i][1]);
        }
        int[] arr = new int[6];
        arr[0] = disjointSetsArrayList.minConnectedComponent();
        arr[1] = disjointSetsArrayList.maxConnectedComponent();
        int[] minMax = disjointSetsTree.minMaxConnectedComponent();
        arr[2] = minMax[0];
        arr[3] = minMax[1];
        int[] minMax2 = disjointSetsTreeMap.minMaxConnectedComponent();
        arr[4] = minMax2[0];
        arr[5] = minMax2[1];
        return arr;
    }

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException
    {
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[][] gb = new int[n][2];
        for (int gbRowItr = 0; gbRowItr < n; gbRowItr++) {
            String[] gbRowItems = scanner.nextLine().split(" ");
            for (int gbColumnItr = 0; gbColumnItr < 2; gbColumnItr++) {
                int gbItem = Integer.parseInt(gbRowItems[gbColumnItr].trim());
                gb[gbRowItr][gbColumnItr] = gbItem;
            }
        }
        int[] result = componentsInGraph(gb);
        System.out.println("\n" + result[0] + " " + result[1]);
        System.out.println(result[2] + " " + result[3]);
        System.out.println(result[4] + " " + result[5]);
    }
}
/*
Input:
40
5 56
4 51
2 53
8 52
5 43
2 80
5 47
4 79
3 75
1 67
7 61
2 57
5 47
4 63
10 79
1 57
4 42
8 79
6 53
3 74
7 60
10 79
5 46
3 50
6 57
8 71
6 74
10 44
9 80
7 59
7 74
6 55
3 77
3 53
5 50
9 70
4 72
5 73
6 70
7 46

Output:
11 25
11 25
* */