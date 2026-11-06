
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class Exercise06 {

	public static void main(String[] args) {
		Concert concert = new Concert("Concert 1", "Artist X", 100);
		Movie movie1 = new Movie("Movie 1", 2017, 120);
		Movie movie2 = new Movie("Movie 2", 2018, 130);

		Show show = new CompositeShow2(concert, new CompositeShow2(movie1, movie2));

		// Client phải kiểm tra kiểu mới duyệt được
		if (show instanceof CompositeShow2 compositeShow) {
			System.out.println("Các show con của composite:");
			for (Show sub : compositeShow) {
				System.out.println(" - " + sub.description() + " (" + sub.runningTime() + " phút)");
			}
		}

		// Show lá thì không duyệt được
		Show leaf = movie1;
		System.out.println("Movie có duyệt được không? " + (leaf instanceof Iterable));

		// Kiểm tra copy() vẫn trả về CompositeShow2
		Show copy = show.copy();
		System.out.println("copy() là CompositeShow2? " + (copy instanceof CompositeShow2));

		// Iterator không cho remove
		try {
			Iterator<Show> it = ((CompositeShow2) show).iterator();
			it.next();
			it.remove();
		} catch (UnsupportedOperationException e) {
			System.out.println("remove() bị chặn: show vẫn immutable");
		}
	}
}

interface Show {
	String description();

	int runningTime();

	Show copy();
}

class Movie implements Show {
	private final String aTitle;
	private final int aYear;
	private final int aRunningTime;

	Movie(String pTitle, int pYear, int pRunningTime) {
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

	public Show copy() {
		return new Movie(aTitle, aYear, aRunningTime);
	}
}

class Concert implements Show {
	private final String aTitle;
	private final String aArtist;
	private final int aRunningTime;

	Concert(String pTitle, String pArtist, int pRunningTime) {
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

	public Show copy() {
		return new Concert(aTitle, aArtist, aRunningTime);
	}
}

class CompositeShow2 implements Show, Iterable<Show> {

	private final List<Show> aShows = new ArrayList<>();

	public CompositeShow2(Show... pShows) {
		if (pShows.length < 2 || pShows.length > 5) {
			throw new IllegalArgumentException("Arguments should be between two and five shows inclusively");
		}
		aShows.addAll(Arrays.asList(pShows));
	}

	public Iterator<Show> iterator() {
		return Collections.unmodifiableList(aShows).iterator();
	}

	public String description() {
		StringJoiner description = new StringJoiner("; ", "[", "]");
		for (Show show : aShows) {
			description.add(show.description());
		}
		return description.toString();
	}

	public int runningTime() {
		int time = 0;
		for (Show show : aShows) {
			time += show.runningTime();
		}
		return time;
	}

	public Show copy() {
		List<Show> copies = new ArrayList<>();
		for (Show show : aShows) {
			copies.add(show.copy());
		}
		return new CompositeShow2(copies.toArray(new Show[0]));
	}
}