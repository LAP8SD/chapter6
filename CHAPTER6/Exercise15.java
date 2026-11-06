import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Bai 6.15: Command co the undo. Show va Movie duoc goi tu thu muc ex6,7. */
public class Exercise15 {

	public static void main(String[] args) {
		ShowList program = new ShowList();
		Show m1 = new Movie("Movie 1", 2017, 120);
		Show m2 = new Movie("Movie 2", 2018, 130);

		List<Command> commands = Arrays.asList(program.createAddCommand(m1), program.createAddCommand(m2),
				program.createRemoveCommand(m1), program.createClearCommand());

		List<String> before = new ArrayList<>();
		for (Command c : commands) {
			before.add(program.toString());
			c.execute();
			System.out.println("Sau execute: " + program);
		}

		System.out.println("---- UNDO ----");
		for (int i = commands.size() - 1; i >= 0; i--) {
			commands.get(i).undo();
			boolean ok = program.toString().equals(before.get(i));
			System.out.println("Sau undo:    " + program + " -> " + (ok ? "OK" : "SAI"));
		}
	}
}
