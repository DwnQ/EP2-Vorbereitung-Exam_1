// A caravan of camels implemented as a singly linked list with entries of 'Camel'.
// There are no 'null' entries in the list.
//
// TODO: define further classes and methods for the implementation of the singly linked list,
//  if needed. Do NOT use the Java-Collection framework in your implementation.
//
public class Caravan {

    //TODO: declare variables.

    // Initializes this caravan as an empty list.
    private Camel node;
    private Caravan nextNode;
    public Caravan() {

    }

    // Adds 'camel' as the last camel to the end of this caravan.
    // Precondition: camel != null.
    public void addLast(Camel camel) {
        var temp = this;
        if(temp.node == null){
            temp.node = camel;
            return;
        }
        while (temp.nextNode != null){
            temp = temp.nextNode;
        }
        var out = new Caravan();
        out.addLast(camel);
        temp.nextNode = out;
    }

    // Inserts a new camel into this caravan. Seen from the head of the caravan, the camel is
    // inserted just before the first camel in the caravan that has the same strength as the
    // specified 'searchStrength'. If no such camel is found, the new camel is added as the head
    // of the caravan.
    // Precondition: camel != null.
    public void insertBefore(int searchStrength, Camel camel) {
        for (var temp = this; temp.nextNode != null ; temp = temp.nextNode) {
            if(temp.node.getStrength() == searchStrength){
                var oldCaravan = new Caravan();
                oldCaravan.node = temp.node;

                oldCaravan.nextNode = temp.nextNode;
                temp.node = camel;
                temp.nextNode = oldCaravan;
                return;
            }
        }
    }

    // Removes 'number' camels from the front of the caravan (the first 'number'
    // camels seen from the head of the caravan) and returns them as a new caravan in which they
    // have the same order as they had in 'this' (see examples in 'PraxisTest1.java'). If this
    // caravan is empty (this.size() == 0) or number == 0 then the result is a new empty caravan.
    // Precondition: number >= 0 && number <= this.size().
    public Caravan detachFront(int number) {
        var out = new Caravan();
        if(this.size() == 0 || number == 0){
            return out;
        }
        var temp = this;
        for (int i = 0; i < number; i++, temp = temp.nextNode) {
            out.addLast(temp.node);
        }
        this.nextNode = temp.nextNode;
        this.node = temp.node;
        return out;
    }

    // Returns the number of camels in the caravan.
    public int size() {
        /*
        var temp = this;
        int counter = 1;

        if(temp.node == null){
            return 0;
        }
        while (temp.nextNode != null){
            temp = temp.nextNode;
            counter++;
        }
        return counter;
         */
        int counter = 1;
        if(this.node == null){
            return 0;
        }
        for (var node = this; node.nextNode != null;node =node.nextNode) {
            ++counter;
        }

        return counter;
    }

    // Returns a string representation of this caravan with all its camels in brackets
    // in corresponding order with head of the caravan on the left,
    // followed by the pace of the caravan, which corresponds to the pace of
    // the slowest camel in the caravan.
    // Example: [(10-2=8), (5-2=3), (7-3=4), (10-3=7)] pace = 3
    // Returns "[]" if the caravan is empty.

    @Override
    public String toString() {
        var out = new StringBuilder();
        int pace = Integer.MAX_VALUE;
        if(this.node == null){
            return "[]";
        }
        out.append("[");

        var temp = this;
        for (;temp.nextNode != null;temp =temp.nextNode) {
            pace = Math.min(pace, temp.node.getMaxPace());
            out.append(temp.node);
            out.append(", ");
        }
        pace = Math.min(pace, temp.node.getMaxPace());
        out.append(temp.node);
        out.append("] pace = ").append(pace);

        return out.toString();
    }
}

// TODO: define further classes, if needed (either here or in a separate file).
