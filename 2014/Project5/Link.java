

public class Link implements Comparable<Link> { // (could be other items)

    private int iData; // data item

    public Link(int it) // constructor
    {
        iData = it;
    }

    public int getKey() {
        return iData;
    }

    @Override
    public int compareTo(Link t) {
        if (this.iData == t.iData) {
            return 0;
        }
        return this.iData > t.iData ? 1 : -1;
    }

    @Override
    public String toString() {
        return String.valueOf(this.iData);
    }
}
