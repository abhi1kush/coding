public class SetNode {
    int id;
    int rank;
    int count;
    SetNode parent;

    public SetNode(int id) {
        this.id = id;
        rank = 0;
        parent = this;
    }

    public int getId() {
        return id;
    }

    public SetNode getParent() {
        return parent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParent(SetNode parent) {
        this.parent = parent;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void incRank() {
        rank++;
    }

    public int getRank() {
        return rank;
    }

    public SetNode(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        count++;
    }

}
