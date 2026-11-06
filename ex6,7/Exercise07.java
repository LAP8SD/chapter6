
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class Exercise07 {

	public static void main(String[] args) {
		Show2 concert = new Concert2("Concert 1", "Artist X", 100);
		Show2 movie1 = new Movie2("Movie 1", 2017, 120);
		Show2 movie2 = new Movie2("Movie 2", 2018, 130);

		Show2 composite = new CompositeShow3(concert, new CompositeShow3(movie1, movie2));

		// Không cần instanceof hay ép kiểu
		System.out.println("Các show con của composite:");
		for (Show2 sub : composite) {
			System.out.println(" - " + sub.description() + " (" + sub.runningTime() + " phút)");
		}

		// Show lá cũng duyệt được, nhưng chạy 0 lần
		int count = 0;
		for (Show2 ignored : movie1) {
			count++;
		}
		System.out.println("Số show con của Movie: " + count);

		// copy() vẫn đúng
		Show2 copy = composite.copy();
		int copyCount = 0;
		for (Show2 ignored : copy) {
			copyCount++;
		}
		System.out.println("Số show con của bản copy: " + copyCount);
	}
}

/**
 * Represents a performance that can take place in a theater. All
 * implementations of this interface should be immutable.
 */
interface Show2 extends Iterable<Show2> {
	String description();

	int runningTime();

	Show2 copy();

	/** Mặc định: show lá không có show con. */
	@Override
	default Iterator<Show2> iterator() {
		return Collections.emptyIterator();
	}
}

class Movie2 implements Show2 {
	private final String aTitle;
	private final int aYear;
	private final int aRunningTime;

	Movie2(String pTitle, int pYear, int pRunningTime) {
		aTitle = pTitle;
		aYear = pYear;
		aRunningTime = pRunningTime;
	}

	public String description() {
		return aTitle + " (" + aYear + ")";
	}

	public int runningTime() {
		return aRunningTime;
	}

	public Show2 copy() {
		return new Movie2(aTitle, aYear, aRunningTime);
	}
}

class Concert2 implements Show2 {
	private final String aTitle;
	private final String aArtist;
	private final int aRunningTime;

	Concert2(String pTitle, String pArtist, int pRunningTime) {
		aTitle = pTitle;
		aArtist = pArtist;
		aRunningTime = pRunningTime;
	}

	public String description() {
		return aTitle + " by " + aArtist;
	}

	public int runningTime() {
		return aRunningTime;
	}

	public Show2 copy() {
		return new Concert2(aTitle, aArtist, aRunningTime);
	}
}

class CompositeShow3 implements Show2 {

	private final List<Show2> aShows = new ArrayList<>();

	public CompositeShow3(Show2... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	@Override
	public Iterator<Show2> iterator() {
		return Collections.unmodifiableList(aShows).iterator();
	}

	public String description() {
		StringJoiner description = new StringJoiner("; ", "[", "]");
		for (Show2 show : aShows) {
			description.add(show.description());
		}
		return description.toString();
	}

	public int runningTime() {
		int time = 0;
		for (Show2 show : aShows) {
			time += show.runningTime();
		}
		return time;
	}

	public Show2 copy() {
		List<Show2> copies = new ArrayList<>();
		for (Show2 show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow3(copies.toArray(new Show2[0]));
	}
}