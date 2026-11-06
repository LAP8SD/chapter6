/** Bai 6.18: Program dong goi CommandProcessor. */
public class Exercise18 {

	public static void main(String[] args) {
		Program program = new Program();
		Show m1 = new Movie("Movie 1", 2017, 120);
		Show m2 = new Movie("Movie 2", 2018, 130);

		program.add(m1);
		program.add(m2);
		program.remove(m1);
		System.out.println(program); // Movie 2
		program.clear();
		System.out.println(program); // []

		program.undoLast();
		System.out.println(program); // Movie 2
		program.undoLast();
		System.out.println(program); // Movie 1, Movie 2
		program.redoLast();
		System.out.println(program); // Movie 2
	}
}
