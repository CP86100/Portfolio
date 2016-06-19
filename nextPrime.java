
public class nextPrime {
	
	public static void main(String args[])
	{
		int input = 10;
		System.out.print(NextPrime(input));
	}
	public static int NextPrime(int i)
    {
  	  int num = i;
  	  while(!isPrime(num))
  		  num++;
  	  return num;
    }
    public static boolean isPrime(int num)
    {
  	  if(num == 1)
  		  return false;
  	  else if(num != 2)
  	  {
  		  for(int i = 2; i < num; i++)
  			  if(num % i == 0)
  				  return false;
  		  return true;
  	  }
  	  else
  		  return true;
    }

}
