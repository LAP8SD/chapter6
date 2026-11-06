/**
 * Bai 18: Program dong goi CommandProcessor. Chi uy quyen (delegate): danh sach do
 * ShowList quan ly, lich su undo/redo do CommandProcessor quan ly, nen Program khong
 * tro thanh God class.
 */
public class Program {
	private final ShowList aShows = new ShowList();
	private final CommandProcessor aProcessor = new CommandProcessor();

	public void add(Show pShow) {
		aProcessor.execute(aShows.createAddCommand(pShow));
	}

	public void remove(Show pShow) {
		aProcessor.execute(aShows.createRemoveCommand(pShow));
	}

	public void clear() {
		aProcessor.execute(aShows.createClearCommand());
	}

	public void undoLast() {
		aProcessor.undoLast();
	}

	public void redoLast() {
		aProcessor.redoLast();
	}

	@Override
	public String toString() {
		return aShows.toString();
	}
}
