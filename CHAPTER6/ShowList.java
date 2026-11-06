import java.util.ArrayList;
import java.util.List;

/**
 * Receiver: giu danh sach Show (Show, Movie lay tu thu muc ex6,7) va tao Command
 * bang anonymous class thong qua factory method. Dung cho bai 14-17; o bai 18 duoc
 * Program boc lai.
 */
public class ShowList {
	private final List<Show> aShows = new ArrayList<>();

	public Command createAddCommand(final Show pShow) {
		return new Command() {
			@Override
			public void execute() {
				aShows.add(pShow);
			}

			@Override
			public void undo() {
				aShows.remove(aShows.size() - 1);
			}
		};
	}

	public Command createRemoveCommand(final Show pShow) {
		return new Command() {
			private int aIndex;

			@Override
			public void execute() {
				aIndex = aShows.indexOf(pShow);
				aShows.remove(aIndex);
			}

			@Override
			public void undo() {
				aShows.add(aIndex, pShow);
			}
		};
	}

	public Command createClearCommand() {
		return new Command() {
			private List<Show> aBackup;

			@Override
			public void execute() {
				aBackup = new ArrayList<>(aShows);
				aShows.clear();
			}

			@Override
			public void undo() {
				aShows.addAll(aBackup);
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < aShows.size(); i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(aShows.get(i).description());
		}
		return sb.append("]").toString();
	}
}
