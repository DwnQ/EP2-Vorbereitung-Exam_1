import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// A set of rational numbers implemented as a binary search tree. There are no
// duplicate entries in this set (no two elements e1 and e2 for which e1.compareTo(e2) == 0). The
// elements are sorted according to their value (the ordering is given by the method 'compareTo'
// of class 'Rational').
//
// TODO: define further classes and methods for the implementation of the binary search tree,
//   if needed. Do NOT use the Java-Collection framework in your implementation.
//
public class TreeSetRational
{

    // TODO: define missing parts of the class.

    // Initialises 'this' as an empty set.
    private TreeSetRational left, right;
    private Rational root;
    public TreeSetRational() {

    }

    // Adds the specified Rational object to the set.
    // Returns 'true' if the set did not already contain the specified element
    // and 'false' otherwise.
    // Precondition: r != null.
    public boolean add(Rational r) {
        if(root == null){
            root = r;
            return true;
        }
        //CASE 1: given r is bigger than value go left
        if(root.compareTo(r)==-1){
            if(left != null){
                return left.add(r);
            }
            left = new TreeSetRational();
            left.root = r;
            return true;
        }
        //CASE 2: given r is smaller than value go right
        if(root.compareTo(r)==1){
            if(right != null){
                return right.add(r);
            }
            right =new TreeSetRational();
            right.root = r;
            return true;

        }
        return false;
    }

    // Returns a new 'TreeSetRational' object that is the union of this set and the 'other' set.
    // 'this' and 'other' are not changed by this method.
    // Precondition: other != null.
    public TreeSetRational union(TreeSetRational other) {
        var newTree = new TreeSetRational();
        union(newTree, other);
        union(newTree, this);
        // TODO: implement method.
        return newTree;
    }
    public void union(TreeSetRational first,TreeSetRational second) {
        if(second!= null){
            union(first,second.left);
            first.add(second.root);
            union(first,second.right);
        }
    }

    // Returns the number of rational numbers in the set that are within the range defined by
    // the lowest and highest rational number (inclusive). The method exploits the structure of
    // the binary search tree and traverses only relevant parts of the tree.
    // Precondition: lowest != null && highest != null && lowest.compareTo(highest) <= 0.
    public int countWithinRange(Rational lowest, Rational highest) {
        /*V1
        List<Rational> list = new ArrayList<Rational>();
        traverseTree(this, list);

        int count = 0;
        for (Rational number : list) {
            if (number.compareTo(lowest) >= 0 && number.compareTo(highest) <= 0) {
                count++;
            }
        }
        return count;
         */
        /*V2
        AtomicInteger out = new AtomicInteger(0);
        countTraverseWithinRange(this, out, lowest, highest);
        return out.get();
         */
        int count = 0;
        count = countTraverseWithinRange(this, count, lowest, highest);
        return count;
    }
    public void traverseTree(TreeSetRational node,List<Rational> list){
        if(node!= null){
            traverseTree(node.left,list);
            list.add(node.root);
            traverseTree(node.right,list);
        }
    }
    public AtomicInteger countTraverseWithinRange(TreeSetRational node, AtomicInteger counter,Rational lowest, Rational highest){
        if(node!= null){
            countTraverseWithinRange(node.left,counter, lowest,highest);
            if(node.root.compareTo(lowest)>-1 && node.root.compareTo(highest)<1){
                counter.set(counter.intValue()+1);
            }
            countTraverseWithinRange(node.right,counter,lowest,highest);
        }
        return counter;
    }
    public Integer countTraverseWithinRange(TreeSetRational node, int count,Rational lowest, Rational highest){
        if (node != null) {
            count = countTraverseWithinRange(node.left, count, lowest, highest);
            if (node.root.compareTo(lowest) >= 0 && node.root.compareTo(highest) <= 0) {
                count++;
            }
            count = countTraverseWithinRange(node.right, count, lowest, highest);
        }
        return count;
    }

    // Removes the lowest rational number from this set. Returns the rational number that was
    // removed from this set or 'null' if this set is empty.
    // (The corresponding node is removed by replacing it with the subtree of the node that
    // contains entries greater than the minimum.)
    public Rational removeMinimum() {
        return removeLeft(this);
    }
    public Rational removeLeft(TreeSetRational node) {
        Rational temp = node.root;

        if(temp!= null){
            if(node.left!= null){
                if(node.root.compareTo(node.left.root)<1){
                    temp = node.root;
                    System.out.println(temp);
                    if(node.right != null){
                        node.root = node.right.root;
                        node.right = node.right.right != null ? node.right.right : null;
                        if(node.right!= null){
                            node.left = node.right.left != null ? node.right.left : null;
                        }
                    }
                    return temp;

                }else removeLeft(node.left);
            }
            temp = node.root; //Removed Element
            if(node.right != null){
                node.root = node.right.root;
                node.right = node.right.right != null ? node.right.right : null;
                if(node.right!= null){
                    node.left = node.right.left != null ? node.right.left : null;
                }
            }
            return temp;
        }
        return null;
    }

    // Returns a string representation of 'this' with all rational objects
    // ordered ascending. The format of the string uses
    // brackets and is as in the following example with a set of four elements:
    // "[-3/4, -2/3, -1/3, 1/2]"
    // Returns "[]" if this set is empty.
    public String toString() {
        var temp = new StringBuilder();

        appendToString(this, temp);
        temp.setLength(temp.length()-2);

        if(temp.toString().contentEquals("null") ){
            return "[]";
        }
        return "[" + temp + "]";
    }
    public void appendToString(TreeSetRational node,StringBuilder string) {
        if(node!= null){
            appendToString(node.right,string);

            string.append(node.root).append(", ");
            appendToString(node.left,string);

        }

    }
}

// TODO: define further classes, if needed (either here or in a separate file).
