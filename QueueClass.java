import java.util.Scanner;
public class QueueClass
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		
		int in = sc.nextInt();
		Queue q = new Queue(in);
	
		for(int i = 0; i < in; i++)
		{
			q.insertPQ(sc.nextInt());
		}
		
		for(int i = 0; i < in; i++)
		{
			System.out.print(q.remove()+" ");
		}
	}
	
	static class Queue
	{
		private int max;
		private int arr[];
		private int front;
		private int rear;
		private int nItems;
		
		public Queue(int i)
		{
			max = i;
			arr = new int[max];
			front = 0;
			rear = -1;
			nItems = 0;
		}
		public boolean isEmpty()
		{
			return(nItems == 0);
		}
		public boolean isFull()
		{
			return(nItems == max);
		}
		public void insert(int i)
		{
			if(!isFull())
			{
				if(rear == max-1)
				{
					rear = -1;
				}
				rear++;
				arr[rear] = i;
				nItems++;
			}
		}
		public int remove()
		{
			int temp = arr[front];
			
			if(!isEmpty())
			{
				front++;
				if(front == max)
				{
					front = 0;
				}
				nItems--;
			}
			return temp;
		}
		public int peekFront()
		{
			return arr[front];
		}
		public int peekRear()
		{
			return arr[rear];
		}
		public void insertPQ(int i)
		{
			if(!isFull())
			{
				if(isEmpty())
				{
					arr[0] = i;
				}
				else
				{
					
				int n = nItems;
				while(n > 0 && arr[n-1] > i)
				{
					arr[n] = arr[n-1];
					n--;
				}
				arr[n] = i;
			
				}
				nItems++;
			}
		}
	}
}