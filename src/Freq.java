import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Freq implements Command {

	@Override
	public String name() {
		return "freq";
	}

	@Override
	public boolean run(Scanner scanner) {
		System.out.println("Enter the path of the file:");
		String line = scanner.nextLine();
		try {
			Path path = Paths.get(line);
			String msg = Files.readString(path);
			ArrayList<String> mostUsed = freq(msg);
			System.out.println(mostUsed.get(0) + " " + mostUsed.get(1) +  " " + mostUsed.get(2));
		} catch (Exception e) {
			System.err.println("Unreadable file: " + e.getClass().getName() + " " + e.getMessage());					
		}
		return false;
	}
	public static ArrayList<String> freq(String text)
	{
		String[] words = text
				.replaceAll("[^a-zA-Z0-9 ]", "")
				.toLowerCase()
				.split(" ");
		Stream<Entry<String, List<String>>> stream = Arrays
				.stream(words).filter((str) -> !str.isBlank())
				.collect(Collectors.groupingBy((str) -> str))
				.entrySet()
				.stream()
				.sorted(Comparator.comparingInt((elt) -> - elt.getValue().size()))
				.limit(3);
		ArrayList<String> mostUsed = new ArrayList<String>();
		stream.forEach((str) -> mostUsed.add(str.getKey()));
		return mostUsed;
	}
}
