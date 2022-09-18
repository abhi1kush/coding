public interface DisjointSets{
    SetNode findParent(int member);
    void union(int u, int v);
}
