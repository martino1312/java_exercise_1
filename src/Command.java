import java.util.Scanner;

public interface Command {

	abstract String name();
	abstract boolean run(Scanner scanner);
	
}
