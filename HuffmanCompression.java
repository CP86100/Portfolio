package CS211;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
/**
 * This is a program to compress inputed text using the Huffman Code method.
 * @author Corentin Pinato (14739811)
 * @version 2.0
 * @date 24 Feb 2016
 */

public class HuffmanCompression 
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the sentence.");
		
		String input = new String(), s = sc.nextLine();
		while(!s.equals("$run"))
		{
			input+=s;
			s = sc.nextLine();
		}
		System.out.println("Enter name for your output file");
		String filename = sc.next();
		sc.close();
		
		String output[] = huffman(input);
		System.out.println(output[0]);

		int compressed = output[0].replace(" ", "").length();
		int original = input.length()*7;
		double ratio = (double)compressed/(double)original*100;	
		
		System.out.println("Input: "+original+" bits | Output: "+compressed+" bits | "+Math.round(ratio)+"% compression");
		
		toFile(output[0], output[1], filename);
	}
	public static void toFile(String huffman, String table, String filename)
	{
		String path = "C:\\Users\\Corentin\\Desktop\\";
		File file = new File(path+filename);
		try{
		PrintWriter printout = new PrintWriter(file);
		printout.println(huffman);
		printout.println();
		printout.println(table);
		printout.close();
		} catch (IOException ex){
		System.out.println("Error: "+ex);
		}
	}
	public static String[] huffman(String input)
	{	
		int Ascii[] = asciiArray(input);
		
		char order[] = inOrder(Ascii);
		int freq[] = frequency(Ascii, order);

		return compress(freq,order,input);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//                                  Character Frequency
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public static int[] asciiArray(String str)
	{
		int array[] = new int[256];
		for(int i = 0; i < str.length(); i++)
			array[(int)str.charAt(i)]++;
//		System.out.println(Arrays.toString(array));
		return array;
	}
	public static char[] inOrder(int Ascii[])
	{
		int ascii[] = Arrays.copyOf(Ascii,Ascii.length);
		String output = new String();
		int pos = 0, freq = 0;
		
		do{
			freq = 0;
			pos = 0;
			for(int i = 0; i < ascii.length; i++)
			{
				if(ascii[i] > freq)
				{
					freq = ascii[i];
					pos = i;
				}
			}
			if(pos != 0)
				output += (char)pos;
			ascii[pos] = 0;
			
		}while(freq != 0);
//		System.out.println(output);
		return output.toCharArray();
	}
	public static int[] frequency(int ascii[], char order[])
	{
		int output[] = new int[order.length];
		for(int i = 0; i < order.length; i++)
			output[i] = ascii[(int)order[i]];
//		System.out.println(Arrays.toString(output));
		return output;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//                              Node and PriorityQueue Classes
	//////////////////////////////////////////////////////////////////////////////////////////////////
	static class Node
	{
		char data;
		int freq;
		Node left;
		Node right;
		
		public Node()
		{
			freq = 0;
			left = null;
			right = null;
		}
		public Node(int i, char a)
		{ 
			data = a;
			freq = i;
			left = null;
			right = null;
		}
		public Node (Node a, Node b)
		{
			freq = a.freq + b.freq;
			left = a;
			right = b;
		}
	}
	//-------------------------------------------------------------------------------------------------
	
	static class PriorityQueue
	{
		int max;
		Node array[];
		int front;
		int rear;
		int size;
		
		public PriorityQueue(int i)
		{
			max = i;
			array = new Node[max];
			front = 0;
			rear = -1;
			size = 0;
		}
		
		public void add(Node i)
		{
			int n = size;
			if(n == 0)
				array[0] = i;
			else
			{
				while(n > 0 && array[n-1].freq < i.freq)
				{
					array[n] = array[n-1];
					n--;
				}
				array[n] = i;
			}
			size++;
			rear++;
		}
		public Node pop()
		{
			Node ans = array[rear];
			rear--;
			size--;
			return ans;
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                          Huffman  Code
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String traverse(Node tree)
	{
		Node current = tree;
		
		String ans = new String();
		if(current != null)
		{
			ans += traverse(current.left);
			ans += (current.data);
			ans += traverse(current.right);
		}
		return ans;
	}
	public static String getCode(Node tree, String str)
	{
		Node current = tree;
		String ans = new String();
		
		if(current.left == null && current.right == null)
			ans += " "+str;
		if(current.left != null)
			ans += getCode(current.left, str+"0");
		if(current.right != null)
			ans += getCode(current.right, str+"1");
		return ans;
	}
	public static String[][] makeTable(String trav, String code)
	{
		String ans[][] = new String[2][(trav.length()/2)+1];
		int n = 0;
		for(int i = 0; i < trav.length(); i+=2)
		{
			ans[0][n++] = new String()+trav.charAt(i);
		}
		n = 0;
		String s = new String("");
		for(int i = 0; i < code.length(); i++)
		{

			if(code.charAt(i) != ' ' && i != code.length())
				s += code.charAt(i);
			
			if(!s.equals("") && (code.charAt(i) == ' ' || i == code.length()-1))
			{
				ans[1][n++] = s;
				s = new String("");
			}
		}
//		System.out.println(Arrays.deepToString(ans));	
		return ans;
	}
	public static String encode(String encode, String[][] table)
	{
		String ans = new String();
		for(int i = 0; i < encode.length(); i++)
		{
			for(int n = 0; n < table[0].length; n++)
			{
				if(encode.substring(i,i+1).equals(table[0][n]))
					ans += table[1][n]+" ";
			}
		}
		return ans;
	}
	public static String[] compress(int freq[], char order[], String input)
	{
		String compressed[] = new String[2];
		PriorityQueue pq = new PriorityQueue(order.length);
		
		for(int i = 0; i < order.length; i++)
		{
			Node node = new Node(freq[i], order[i]);
			pq.add(node);
		}
		while(pq.size != 1)
		{
			Node node = new Node(pq.pop(),pq.pop());
			pq.add(node);
		}
		 String trav = traverse(pq.array[0]);
		 String code = getCode(pq.array[0],"");
		 String table[][] = makeTable(trav,code);
		 
		 compressed[0] = encode(input,table);
		 compressed[1] = Arrays.deepToString(table);
		 return compressed;
		
	}

}
