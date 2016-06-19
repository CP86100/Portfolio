package CS211;

import java.util.Arrays;
/**
 * This is quicksort to sort numbers from lowest to highest.
 * @author Corentin Pinato
 * @version 1.0
 *
 */
public class QuickSort
{
	public static void main(String args[])
	{
		int array[] = {7,8,2,5,1,9,3,6,5,99,3,7,6,4,2,9,8,2,4,6,3,99,5,100,7,19,50,33,72,87,666,1};
		
		Qsort(array);
		
		System.out.println(Arrays.toString(array));

	}
	public static int[] Qsort(int array[])
	{
		return sort(array,0,array.length-1);
	}
	public static int[] sort(int array[], int left, int right)
	{
		int pivot = array[(left+right)/2];
		int Lscan = left, Rscan = right;
		if((right-left) > 1)
		{
			while(Rscan > Lscan)
			{
				while(array[Rscan] > pivot)
					Rscan--;
				while(array[Lscan] < pivot)
					Lscan++;
				
				if(Rscan >= Lscan)
				{
					int temp = array[Lscan];
					array[Lscan++] = array[Rscan];
					array[Rscan--] = temp;
				}
				sort(array,left,Rscan);
				sort(array,Lscan,right);
			}
		}
		return array;
	}

}
