import java.util.ArrayList;
import java.util.function.Function;

public class IntList extends ArrayList<Integer> {
    
    public static void main(String[] args) {
	IntList list = new IntList();
	for(int i=0;i<8;i++)
	    list.add(i);

	//list.filter();
	//list.filter(7);
	// BooleanCheck f = new FilterSmall()); // using a named class
	BooleanCheck f = new BooleanCheck() { // using an anonymous class
		public boolean check(int x) {
		    return x>3;
		}

		public void haveFun() { // careful, this cannot be called outside of the anonymous class
		    System.out.println("Yea!!!"); // form outside this class
		}
	    };
	f.haveFun(); // this doesn't work. // Error: Cannot find symbol haveFun() in BooleanCheck
	// because f is a reference of type BooleanCheck, which does not have a haveFun() method defined.
	
	f = x -> x > 3;

	int divisibleBy = 3;
	f = x -> x % divisibleBy == 0;
	f = (x) -> {return x % divisibleBy == 0;};
	divisibleBy = 2; // this does not work // local variable referenced from a lambda expression must be final

	// Function<T, R> has one abstract method: R apply(T t);
	list.filter(f);
	// list.filter(new FilterSmall());
	
	
	System.out.println(list);
    }

    // filter() started without any arguments; only removed 2s from list
    // filter(int) was generalized to remove any one value from list
    // filter(BooleanCheck) further generalized to run any int test
    public void filter(Function<Integer, Boolean> filterCode) {
	for(int i=0;i<size();i++)
	    if( filterCode.apply(get(i)) ) { //get(i) == filterOutValue) {
		remove(i);
		i--; // ensure we can remove two sequential filterable values
	    }
    }    
}

// new type with one ability: convert integer inputs into boolean outputs
// since this is the one ability that our newly parameterized filter needs
interface BooleanCheck {
    public boolean check(int x);
}

// this is the old cs300 way to define a named class containing a check method
class FilterSmall implements BooleanCheck {
    public boolean check(int x) {
		return x < 4;
    }
}


