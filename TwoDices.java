package stats;

import java.util.*;

public class TwoDices
{
	public static void main(String args[])
	{
		int array[] = new int[12];
		for(int i = 0; i < 1000000; i++)
		{
			int die1 = roll();
			int die2 = roll();
			
			int sum = die1+die2;
			array[sum-1]++;
		}
		for(int i = 1; i < array.length; i++)
			System.out.println("The odds to get "+(i+1)+" is "+(double)array[i]/1000000.0);
		System.out.println("--------------------------------------------------------------------------");
		int array2[] = new int[7];
		for(int i = 0; i < 1000000; i++)
		{
			int die = roll();
			array2[die]++;
		}
		for(int i = 1; i < array2.length; i++)
			System.out.println("The odds to get "+(i)+" is "+(double)array[i]/1000000.0);
	}
	public static int roll()
	{
		Random rd = new Random();
		int max = 6;
		int min = 1;
		return rd.nextInt(max-min+1)+min;
	}

}
