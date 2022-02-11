import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Predict implements Command {

	@Override
	public String name() {
		return "predict";
	}

	@Override
	public boolean run(Scanner scanner) {
		System.out.println("Enter the path of the file:");
		String line = scanner.nextLine();
		try {
			Path path = Paths.get(line);
			String msg = Files.readString(path);
			System.out.println("Enter the word to start with:");
			line = scanner.nextLine();
			String[] words_ = msg
					.replaceAll("[^a-zA-Z0-9 ]", "")
					.toLowerCase()
					.split(" ");
			ArrayList<String> words = new ArrayList<String>();
			for (int i = 0; i < words_.length; ++i)
				words.add(words_[i]);
			line = line.toLowerCase();
			if (!words.contains(line))
				System.err.println("The word you entered is not in the text");
			else {
				System.out.print(line + " ");
				for (int i = 0; i < 19; ++i) {
					line = predict(msg, line);
					System.out.print(line);
					if (i < 18)
						System.out.print(" ");
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.err.println("Unreadable file: " + e.getClass().getName() + " " + e.getMessage());					
		}
		return false;
	}
	
	private String predict(String text, String word)
	{
		ArrayList<String> possibilities = new ArrayList<String>();
		String[] words = text
				.replaceAll("[^a-zA-Z0-9 ]", "")
				.toLowerCase()
				.split(" ");
		for (int i = 0; i < words.length - 1; ++i) {
			if (words[i].equals(word))
				possibilities.add(words[i + 1]);
		}
		String s = "";
		for (String str : possibilities)
			s += str + " ";
		return Freq.freq(s).get(0);
	}
}