import java.util.ArrayList;

public class DisjointSetsArrayList implements DisjointSets{
    ArrayList<SetNode> disjointSets;

    public DisjointSetsArrayList(int setsCount) {
        this.disjointSets = new ArrayList<>(setsCount);
        for (int i = 0; i < setsCount + 1; i++) {
            disjointSets.add(null);
        }
    }

    @Override
    public SetNode findParent(int member)
    {
        return disjointSets.get(member);
    }

    @Override
    public void union(int u, int v)
    {
        SetNode s1 = findParent(u);
        SetNode s2 = findParent(v);
        if (s1 == null && s2 == null) {
            SetNode newSet = new SetNode(u, 2);
            disjointSets.set(u, newSet);
            disjointSets.set(v, newSet);
        }
        else if (s1 == null) {
            s2.incCount();
            disjointSets.set(u, s2);
        }
        else if (s2 == null) {
            s1.incCount();
            disjointSets.set(v, s1);
        }
        else if (s1.getId() != s2.getId()) {
            int id = s2.getId();
            int tempCount = (s1.getCount() + s2.getCount());
            s1.setCount(tempCount);

            for (int i = 1; i < disjointSets.size(); i++) {
                if (disjointSets.get(i) != null && (disjointSets.get(i).getId() == id)) {
                    disjointSets.set(i, s1);
                }
            }
            //System.out.println("u id != v id"+ u + " " + v);
        }
        //display();
    }

    public int maxConnectedComponent() {
        int maxx = Integer.MIN_VALUE;
        for (int i = 1; i < disjointSets.size(); i++) {
            SetNode set = disjointSets.get(i);
            if(set == null) {
                continue;
            }
            //System.out.println(set.getCount());
            maxx = Integer.max(maxx, set.getCount());
        }

        return maxx;
    }

    public int minConnectedComponent() {
        int minn = Integer.MAX_VALUE;
        for (int i = 1; i < disjointSets.size(); i++) {
            SetNode set = disjointSets.get(i);
            if(set == null) {
                continue;
            }
            minn = Integer.min(minn, set.getCount());
        }

        return minn;
    }

    void display() {
        int nonNull = 0;
        for(int i = 0; i < disjointSets.size(); i++) {
            SetNode set = disjointSets.get(i);
            if (set == null) {
                System.out.print("[" + i + ": null], ");
                continue;
            }
            nonNull++;
            System.out.print("[" + i + ": (" + set.getId() + " " + set.getCount() + ")" + set + "], ");
        }
        System.out.println("---" + nonNull);
    }
}
