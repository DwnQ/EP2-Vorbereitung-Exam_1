import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// A 'BalancedStick' has a specified stick weight, that can not be changed after
// initialisation. On the left and right end of the stick mobiles
// are attached (recursive structure). 'BalancedStick' implements 'Mobile'.
// You can assume that no part of a mobile has the same identity as another part.
//
public class BalancedStick implements Mobile // TODO: activate clause.
{

    //TODO: define missing parts of the class.
    private Mobile left;
    private Mobile right;
    int stickWeight;
    // Initialises 'this'; throws an 'UnbalancedException' if the resulting mobile
    // would not be balanced, i.e. if left.getWeight() != right.getWeight(). The detail message
    // of the exception contains information about the difference between left and right weight,
    // for example "Stick unbalanced (left 7 - right 16)" (see example in 'PraxisTest2.java').
    // Preconditions: stickWeight > 0, left != null, right != null, left != right,
    // no part of a mobile has the same identity as another part.
    public BalancedStick(int stickWeight, Mobile left, Mobile right) throws UnbalancedException {
        if(left.getWeight() != right.getWeight()){
            throw new UnbalancedException("(left "+ left.getWeight()+ " - " + "right "+ right.getWeight()+ ")");
        }
        this.left = left;
        this.right = right;
        this.stickWeight = stickWeight;


        // TODO: implement constructor.
    }

    // Replaces the mobile equal to 'toReplace' with a new mobile 'replaceWith' and
    // returns 'true' if such a mobile is contained as part of this mobile, i.e., attached to this
    // stick or below (recursive search). Otherwise, the call of this method has no effect and
    // 'false' is returned.
    // Throws an 'UnbalancedException' if the replacement would violate the
    // conditions that all sticks need to be balanced. The detail message
    // of the exception contains information about the difference between left and right weight.
    // Precondition: toReplace != null && replaceWith != null
    public boolean replace(Mobile toReplace, Mobile replaceWith) throws UnbalancedException {
        if(left.getWeight() != right.getWeight()) throw new UnbalancedException("");
        if(left.equals(toReplace)){
            left = replaceWith;
            return true;
        }
        if(this.left.getClass() == this.getClass()){
            return ((BalancedStick)this.left).replace(toReplace,replaceWith);
        }
        if(this.right.getClass() == this.getClass()){
            return ((BalancedStick)this.right).replace(toReplace,replaceWith);
        }
        return false;
    }

    @Override
    public int getWeight() {
        return stickWeight+ left.getWeight()+right.getWeight();
    }

    @Override
    // Two sticks are equal if
    // 1.) they have the same stick weight and
    // 2.) if the left part of 'this' equals the left part of 'o' and the right part of 'this'
    //       equals the right part of 'o'
    //     or
    //     if the right part of 'this' equals the left part of 'o' and the left part of 'this'
    //       equals the right part of 'o' (i.e., exchanging left and right part does not
    //       change the value returned by 'equals').
    //
    // For example, all three of the following mobiles are equal (provided that corresponding
    // objects of Star are equal):
    //
    //          |                      |                |
    //      +---2---+              +---2---+        +---2---+
    //      |       |              |       |        |       |
    //   +--2--+    *           +--2--+    *        *    +--2--+
    //   |     |    16          |     |    16       16   |     |
    //   *  +--1--+          +--1--+  *               +--1--+  *
    //   7  |     |          |     |  7               |     |  7
    //      *     *          *     *                  *     *
    //      3     3          3     3                  3     3
    //
    public boolean equals(Object o) {

        if(o instanceof BalancedStick){
            if(this.stickWeight == ((BalancedStick)o).getWeight() || this.right.toString().equals(((BalancedStick)o).left.toString()) || this.right.toString().equals(((BalancedStick)o).left.toString()))
            return true;
        }
        return false;
    }

    @Override
    public StarCollection getStarCollection() {
        return new StarCollection() {
            @Override
            public boolean contains(Star s) {
                ArrayList<Star> list = new ArrayList<>();
                traverse(list, BalancedStick.this);

                return list.contains(s);
            }
        };
    }

    @Override
    public StarIterator iterator() {
        ArrayList<Star> list = new ArrayList<>();
        traverse(list, this);
        return new MyStarIterator(list.iterator());
    }
    public void traverse(ArrayList<Star> list, Mobile stick) {
        if(stick == null){
            return;
        }
        if(stick instanceof BalancedStick){
            traverse(list,  ((BalancedStick) stick).left);
            traverse(list, ((BalancedStick) stick).right);
        }
        if(stick instanceof Star){
            list.add((Star) stick);
        }
    }
    class MyStarIterator implements StarIterator {


        private Iterator<Star> it;

        public MyStarIterator(Iterator<Star> iterator) {
            this.it = iterator;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Star next() {
            if(!it.hasNext()){
                throw new NoSuchElementException("no star element!");
            }
            return it.next();
        }
    }

    @Override
    public String toString() {
        return "(" + left + ")-"+ stickWeight +"-(" + right +')';
    }
}

// TODO: define additional classes if needed (either here or in a separate file).
