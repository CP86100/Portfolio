package CS211;

import java.util.Arrays;

public class QuickSort
{
	public static void main(String args[])
	{
		int array[] = {7,8,2,5,1,9,3,6};
		array = quicksort(array,0,array.length-1);
		System.out.println(Arrays.toString(array));
	}
	public static int[] quicksort(int array[], int left, int right)
	{
		if(right-left > 1 )
		{
		int middle = (left+right)/2;
		int ls = left;
		int rs = right;
		while(rs > ls)
		{
			while(array[ls] < middle)
				ls++;
			while(array[rs] > middle)
				rs--;
			
			int temp = array[ls];
			array[ls] = array[rs];
			array[rs] = temp;
		}
		array = quicksort(array,left,middle-1);
		array = quicksort(array,middle+1,right);
		}
		return array;
	}

}
