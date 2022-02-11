import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {
	
	public static void main(String[] args) {
		
		System.out.println("Je s'appelle Groot.");
		
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		List<Command> commands = new ArrayList<Command>();
		commands.add(new Fibo());
		commands.add(new Freq());
		commands.add(new Predict());
		commands.add(new Quit());
		boolean unknown = true;
		
		while (true)
		{
			for (Command cmd : commands)
			{
				if (cmd.name().equals(line))
				{
					unknown = false;
					if (cmd.run(scanner))
					{
						scanner.close();
						System.exit(0);
					}
				}
			}
			if (unknown)
				System.out.println("Unknown command");
			line = scanner.nextLine();
			unknown = true;
		}
	}
}