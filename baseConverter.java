package CS210;

import java.util.*;
import java.math.*;
/**
 * This program is to convert a number from one base to another.
 * @author Corentin Pinato
 * @version 1.0
 */
public class baseConverter
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
	    int base1 = sc.nextInt(), base2 = sc.nextInt(), num = sc.nextInt();

	    int in = toDec(base1, num);
	    System.out.println(toBase(base2, in));

	}
	public static int toDec(int base, int num)
	{
		String in = Integer.toString(num);
		int answer = 0, digit = 0;

		for(int i = 0; i < in.length(); i++)
		{
			digit = (int)in.charAt(i)-48;
			answer += digit*Math.pow(base, in.length()-i-1);
		}
		return answer;
	}
	public static String toBase(int base, int num)
	{
		int power = 0, value = 0;
		String answer = new String();

		boolean found = false;
		int time = 0, pwr = 32;
		while(!found)
		{
			power = (int)Math.pow(base, pwr);
			if(power <= num)
			{
				time = pwr+1;
				found = true;
			}
			pwr--;
		}

		int pow = time-1;
		for(int i = 0; i < time; i++)
		{
			power = (int)Math.pow(base, pow);
			value = (num-(num%power))/power;
			answer = answer + Integer.toString(value);
			num = num%power;
			pow--;
		}
		return answer;
	}
}
