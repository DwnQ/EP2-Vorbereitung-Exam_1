import java.util.*;
import java.util.zip.CheckedOutputStream;

// A 'Stick' has a specified stick weight, that can not be changed after
// initialisation. Mobiles can be attached to the stick (recursive structure).
// 'Stick' implements 'Mobile'.
// You can assume that no part of a mobile has the same identity as another part.
//
public class Stick implements Mobile // TODO: uncomment clause.
{
    private final int stickWeight;
    Mobile[] attached;
    //TODO: define missing parts of the class.

    // Initialises 'this'; throws an 'StickBreaksException' if the sum of the weight of
    // all mobiles in the array 'attached' is greater than 10 times the 'stickWeight'.
    // The detail message of the exception is "Stick breaks!".
    // Precondition: attached.length > 0 and for any two mobiles m1 and m2 being part of
    // 'attached' (including sub-mobiles) it holds that m1.equals(m2) == false, this is,
    // that there are no duplicates anywhere in a mobile.
    public Stick(int stickWeight, Mobile[] attached) throws StickBreaksException {
        this.stickWeight = stickWeight;
        int sum = 0;
        for (var item:attached) {
            sum += item.getWeight();
        }
        if(sum > 10*stickWeight){
            throw new StickBreaksException("Stick breaks!");
        }
        this.attached =attached;
        // TODO: implement constructor.
    }

    // Replaces the mobile equal to 'toReplace' with a new mobile 'replaceWith' if this mobile
    // is directly attached to this stick (no recursive search). In this case 'true' is returned.
    // Otherwise, the call of this method has no effect and 'false' is returned.
    // Throws a 'StickBreaksException' if the replacement would violate the
    // condition that the sum of the weight of all attached mobiles has to be
    // less than or equal to 10 times its stick weight.
    // Precondition: toReplace != null && replaceWith != null
    public boolean replace(Mobile toReplace, Mobile replaceWith) throws StickBreaksException {
        var sum = 0;
        for (var item:attached) {
            sum+= item.getWeight();
            if(item.equals(toReplace)){
                if(replaceWith.getWeight() > sum * 10){
                    throw new StickBreaksException("ASASd");
                }

                return true;
            }
        }
        // TODO: implement method.
        return false;
    }

    @Override
    public int getWeight() {
        int sum = 0;
        for (var item:attached) {
            sum += item.getWeight();
        }
        return sum+stickWeight;
    }

    @Override
    public String toString() {
        return  stickWeight + Arrays.toString(attached);
    }

    @Override
    // Returns 'true' if 'o' is also of class 'Stick', has an equal stick weight, and has equal mobiles
    // attached, regardless of their order. This means that 'this' and 'o' have the same number of mobiles
    // attached and every mobile attached to 'this' is equal to a mobile attached to 'o' (and vice versa).
    // Otherwise, 'false' is returned.
    public boolean equals(Object o) {
        if(o instanceof Stick && ((Stick)o).getWeight() == this.getWeight() && this.attached.length ==((Stick)o).attached.length){
            return true;
        }
        // TODO: implement method.
        return false;
    }

    @Override
    public Countable getStickCountable() {
        return new Countable() {
            @Override
            public int count() {
                return Counter(Stick.this);
            }
        };
    }
    public int Counter(Stick mobile){
        for (Mobile elem: mobile.attached) {
            if(elem instanceof Star){
                return 1;
            }
            if (elem instanceof Stick){
                return Counter((Stick)elem)+1;
            }
        }
        return 1;
    }

    public void addToList(ArrayList list, Stick mobile){
        for (Mobile elem: mobile.attached) {
            if(elem instanceof Star){
                list.add(elem);
            }
            if (elem instanceof Stick){
                addToList(list, (Stick)elem);
            }
        }
    }
    @Override
    public StarIterator iterator() {
        var list = new ArrayList<Star>();
        addToList(list, this);
        return new MyStarIterator(list);
    }
    public class MyStarIterator implements StarIterator{
        private final Iterator<Star> it;
        public MyStarIterator(ArrayList<Star> list) {
            this.it = list.iterator();
        }


        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Star next() {
            try{
                return it.next();
            }catch (Exception e){
                throw new NoSuchElementException("no star element!");
            }
        }
    }
}

// TODO: define additional classes if needed (either here or in a separate file).