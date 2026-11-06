/** Bai 6.16: CommandProcessor (execute + undoLast). Client chi noi chuyen voi processor. */
public class Exercise16 {

	public static void main(String[] args) {
		ShowList program = new ShowList();
		CommandProcessor processor = new CommandProcessor();
		Show m1 = new Movie("Movie 1", 2017, 120);
		Show m2 = new Movie("Movie 2", 2018, 130);

		processor.execute(program.createAddCommand(m1));
		processor.execute(program.createAddCommand(m2));
		processor.execute(program.createRemoveCommand(m1));
		processor.execute(program.createClearCommand());
		System.out.println(program); // []

		processor.undoLast();
		System.out.println(program); // chi con Movie 2
		processor.undoLast();
		System.out.println(program); // Movie 1, Movie 2
		processor.undoLast();
		processor.undoLast();
		processor.undoLast(); // stack rong: khong loi
		System.out.println(program); // []
	}
}
