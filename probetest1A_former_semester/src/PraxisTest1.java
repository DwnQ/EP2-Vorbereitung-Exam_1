// This class can be modified and is for testing your solution.
// Modifications will NOT be reviewed or assessed.
//
public class PraxisTest1 {

    public static void main(String[] args) {

        Monomial m = new Monomial(3,2);

        System.out.println("Test 1:");
        System.out.println(m);
        System.out.println(m.eval(-5));
        System.out.println(m.eval(0));
        /*
         Test 1:
         3*x^2
         75
         0
         */


        System.out.println("Test 2:");
        System.out.println(m.combine(new Monomial(-1,2)));
        System.out.println(m);
        System.out.println(m.combine(new Monomial(-1,3)));
        System.out.println(m);
        /*
        Test 2:
        true
        2*x^2
        false
        2*x^2
         */

        System.out.println("Test 3:");
        Polynomial p1 = new Polynomial(new int[] {2,0,3,1});
        Polynomial p2 = new Polynomial(new int[] {0,0,3,-1});
        System.out.println(p1);
        System.out.println(p1.eval(-5));
        System.out.println(p2);
        /*
        Test 3:
        2*x^0 + 3*x^2 + 1*x^3
        -48
        3*x^2 + -1*x^3
         */

        System.out.println("Test 4:");
        p1.add(p2);
        System.out.println(p1);

        p1 = new Polynomial(new int[] {2,0,3,1});
        p2 = new Polynomial(new int[] {0,0,3,1});

        p2.add(p1);
        System.out.println(p2);
        /*
         Test 4:
         2*x^0 + 6*x^2 + 0*x^3
         2*x^0 + 6*x^2 + 2*x^3
         */

    }
}
