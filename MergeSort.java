public class MergeSort
{
	public static void main(String args[])
	{
		int in[] = {9,0,7,8,1,6,2,3,5,};
		in = mergeSort(in);
		
	        System.out.print(Arrays.toString(in));
	}
	public static int[] mergeSort(int[] input)
	{
		return sort(input, 0, input.length-1);
	}
	public static int[] sort(int in[], int upper, int lower)
	{
		int mid = (upper + lower)/2;
		int ans[] = {in[upper]};
		
		if(upper == lower)
			return ans;
		else
			return merge(sort(in, upper, mid), sort(in, mid+1, lower));	
	}
	public static int[] merge(int a[], int b[])
	{
		int na = 0, nb = 0, i = 0;
		int ans[] = new int[a.length+b.length];
		
		while(na < a.length && nb < b.length)
		{
			if(a[na] < b[nb])
				ans[i++] = a[na++];
			else
				ans[i++] = b[nb++];	
		}
		while(na < a.length)
			ans[i++] = a[na++];
		while(nb < b.length)
			ans[i++] = b[nb++];
		return ans;		
	}
	
}

                 //OUTPUT//
/* 
   0 1 2 3 4 5 6 6 7 8 9 10 11 12 13 14 15 
   Process completed.
*/