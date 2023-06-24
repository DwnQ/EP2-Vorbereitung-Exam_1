import java.util.ArrayList;

// Leaf node of a mobile. The actual decoration of a mobile.
// A 'Star' has a specified weight of type 'int', that can not be changed after
// initialisation. 'Star' implements 'Decoration'.
//
public class Star implements Decoration // TODO: uncomment clause.
{

    //TODO: define missing parts of the class.
    private final int weight;
    public Star(int weight) {
        this.weight = weight;
        // TODO: implement constructor.
    }

    @Override
    public int getWeight() {
        return weight;
    }

    // Returns a readable representation of 'this' with the
    // symbol '*' followed by the weight of this star.
    public String toString() {

        // TODO: implement method.
        return "*" + weight;
    }

    @Override
    public Countable getStickCountable() {
        return (Countable) this;
    }
    public boolean equals(Object o) {
        if(o instanceof Star && ((Star)o).getWeight() == this.weight){
            return true;
        }
        // TODO: implement method.
        return false;
    }
    @Override
    public StarIterator iterator() {
        ArrayList<Star> list = new ArrayList<Star>();
        list.add(this);
        var it = list.iterator();
        return new StarIterator() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Star next() {
                return it.next();
            }
        };
    }
}

// TODO: define additional classes if needed (either here or in a separate file).
