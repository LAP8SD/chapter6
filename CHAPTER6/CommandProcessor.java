import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bai 16: execute + undoLast. Bai 17: them redoLast.
 */
public class CommandProcessor {
	private final Deque<Command> aExecuted = new ArrayDeque<>();
	private final Deque<Command> aUndone = new ArrayDeque<>();

	public void execute(Command pCommand) {
		pCommand.execute();
		aExecuted.push(pCommand);
		aUndone.clear(); // lenh moi lam lich su re nhanh -> het redo
	}

	public void undoLast() {
		if (!aExecuted.isEmpty()) {
			Command command = aExecuted.pop();
			command.undo();
			aUndone.push(command);
		}
	}

	public void redoLast() {
		if (!aUndone.isEmpty()) {
			Command command = aUndone.pop();
			command.execute();
			aExecuted.push(command);
		}
	}
}
