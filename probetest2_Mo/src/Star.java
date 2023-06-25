import java.util.ArrayList;

// Leaf node of a mobile. The actual decoration of a mobile.
// A 'Star' has a specified weight, that can not be changed after
// initialisation. 'Star' implements 'Decoration'.
//
public class Star  implements Decoration //TODO: activate clause.
{
    //TODO: define missing parts of the class.
    private int weight;
    public Star(int weight) {
        this.weight = weight;
        // TODO: implement constructor.
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    // Returns a readable representation of 'this' with the
    // symbol '*' followed by the weight of this star.
    public String toString() {

        // TODO: implement method.
        return "*"+weight;
    }

    @Override
    public StarCollection getStarCollection() {
        return new StarCollection() {
            @Override
            public boolean contains(Star s) {
                return this.equals(s);
            }
        };
    }

    @Override
    public StarIterator iterator() {
        ArrayList<Star> list = new ArrayList<>();
        list.add(this);
        var it = list.iterator();
        return new StarIterator() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Star next() {
                return  it.next();
            }
        };
    }
}
