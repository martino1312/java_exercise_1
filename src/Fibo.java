import java.util.Scanner;

public class Fibo implements Command {

	@Override
	public String name() {
		return "fibo";
	}

	@Override
	public boolean run(Scanner scanner) {
		System.out.println("Enter the index of the sequence:");
		try {
			int x = scanner.nextInt();
			System.out.println(fibo(x));
		}
		catch (Exception e) {
			System.err.println("You must enter a number.");
		}
		scanner.nextLine();
		return false;
	}
	
	private long fibo(long n)
	{
		long x = 0;
		long y = 1;
		boolean xt = true;
		
		for (long i = 2; i < n; ++i) {
			if (xt)
				x += y;
			else
				y += x;
			xt = !xt;
		}
		
		return x + y;
	}
}
