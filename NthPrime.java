import java.util.*;

public class NthPrime
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);

		int in = sc.nextInt();
		int last = 0;
		int counter = 1;
		int num = 2;

		while(counter <= in)
		{
			boolean prime = true;

			if(num != 2)
			{
				for(int i = 2; i < num; i++)
				{
					if(num % i == 0)
						prime = false;

				}

			}
			if(prime == true)
				counter ++;
				last = num;

			num++;

		}

		System.out.println(last);
	}
}