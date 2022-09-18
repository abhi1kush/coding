import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;

public class DisjointSetsTree implements DisjointSets{
    ArrayList<SetNode> disjountSets;

    DisjointSetsTree(int setCount)
    {
        disjountSets = new ArrayList<>(setCount);
        for (int i = 0; i < setCount + 1; i++) {
            disjountSets.add(new SetNode(i));
        }
    }

    @Override
    public SetNode findParent(int ele)
    {
        SetNode currNode = disjountSets.get(ele);
        if (currNode.getParent() == currNode)
        {
            return currNode;
        }
        
        SetNode parentNode = findParent(currNode.getParent().getId());
        //Path compression.
        currNode.setParent(parentNode);
        return parentNode;
    }
    
    @Override
    public void union(int ele1, int ele2) {
        SetNode s1 = findParent(ele1);
        SetNode s2 = findParent(ele2);
        
        int rank1 = s1.getRank();
        int rank2 = s2.getRank();

        if (rank1 == rank2) {
            s1.incRank();
            s2.setParent(s1);
        }
        else if (rank1 < rank2) {
            s1.setParent(s2);
        }
        else if (rank1 > rank2) {
            s2.setParent(s1);
        }
    }


    public int[] minMaxConnectedComponent() {
        int[] arr = new int[disjountSets.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        for (int i = 0; i < disjountSets.size(); i++) {
            arr[findParent(i).getId()]++;
        }

        int maxx = Integer.MIN_VALUE;
        int minn = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            //System.out.print(arr[i] + ", ");
            maxx = Integer.max(maxx, arr[i]);
            if (arr[i] != 0 && arr[i] != 1) {
                minn = Integer.min(minn, arr[i]);
            }
        }
        if (minn == Integer.MAX_VALUE) {
            minn = 1;
        }

        int[] ret = new int[2];
        ret[0] = minn;
        ret[1] = maxx;
        return ret;
    }
}
