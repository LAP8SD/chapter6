/** Bai 6.17: them redo cho CommandProcessor. */
public class Exercise17 {

	public static void main(String[] args) {
		ShowList program = new ShowList();
		CommandProcessor processor = new CommandProcessor();
		Show m1 = new Movie("Movie 1", 2017, 120);
		Show m2 = new Movie("Movie 2", 2018, 130);
		Show m3 = new Movie("Movie 3", 2019, 140);

		processor.execute(program.createAddCommand(m1));
		processor.execute(program.createAddCommand(m2));
		System.out.println(program); // Movie 1, Movie 2

		processor.undoLast();
		System.out.println(program); // Movie 1
		processor.redoLast();
		System.out.println(program); // Movie 1, Movie 2

		processor.undoLast();
		processor.execute(program.createAddCommand(m3)); // xoa stack redo
		processor.redoLast(); // khong lam gi
		System.out.println(program); // Movie 1, Movie 3
	}
}
