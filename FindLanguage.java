package CS211;

import java.util.*;
import java.io.*;
import java.text.*;


public class FindLanguage 
{
	public static void main(String args[])
	{
		String input = getInput();
		
		input = input.replace("ù", "");
		
		int ascii[] = asciiArray(input);
		char cha[] = inOrder(ascii);
	//	System.out.println(Arrays.toString(cha));
		int freq[] = frequency(ascii,cha);
	//	System.out.println(Arrays.toString(freq));
		
		/////////////////////////////////////////////////////////////////////
		// Get the total amount of characters in the whole text.
		
		int total = 0;
		for(int i = 0; i < ascii.length; i++)
		{
			total += ascii[i];
		}
		//total -= freq[1];
	//	System.out.println(total);
		
		/////////////////////////////////////////////////////////////////////
		// Get Character Frequency of text in percentage.
		
		double per[] = new double[freq.length];
		for(int i = 0; i < per.length; i++)
		{
			per[i] = ((double)freq[i]/(double)total)*100;
		}
		
	//	System.out.println(Arrays.toString(per));
	//	System.out.println(cha.length);
		
		double languageFreq[][] = LangFreq();
		
		// List of Different Languages.
		String foundLanguage[] = {"English","French","Spanish","German","Italian","Danish","Finnish","Icelandic","Swedish"};
	
		//////////////////////////////////////////////////////////////////////////////////////
		// Check difference between the character frequency of input and character frequency of languages.
		double languages[] = new double[9];

		double dif = 0.3;      //<=============================DIF===============================DIF==========================================================DIF
		
		for(int r = 0; r < languageFreq.length; r++)
		{
			double percentage = 0;
			for(int c = 0; c < languageFreq[r].length; c++)
			{
				if(Math.abs(languageFreq[r][c]-per[c]) <= dif)
				{
					percentage++;
				}
			}
			languages[r] = (percentage/(double)languageFreq[r].length)*100;
		//	languages[r] = 100.0-languages[r];
		}

		//System.out.println(Arrays.toString(languages));
		
		/////////////////////////////////////////////////////////////////////
		// Get the language with the highest compatibility.
		double largest = languages[0];
		int index = 0;
		for(int i = 0; i < languages.length; i++)
		{
			if(languages[i] > largest)
			{
				largest = languages[i];
				index = i;
			}
		}
		
		////////////////////////////////////////////////////////////////////
		// Printing out Results.
		
		DecimalFormat formatter = new DecimalFormat("#0.000");   
		
		System.out.println();
		System.out.println("The language with the highest score is: "+foundLanguage[index]+" with "+formatter.format(languages[index])+"% compatibility");
		System.out.println();
		for(int i = 0; i < languages.length; i++)
			System.out.println(foundLanguage[i]+": "+formatter.format(languages[i])+"%");
		System.out.println();
		

	}
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
	public static String getInput()
	{
		File file = new File("X:\\workspace\\CodeFiles\\mystery.txt");
		String output = new String();
		try
		{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			
			String str = new String();
			while((str = bf.readLine())!= null)
			{
				output += str;
			}
			bf.close();
			
		}catch(IOException ex)
		{
			System.out.println(ex);
		}
		return output;
	}
	public static double[][] LangFreq()
	{
		
		
		//English
		double freqEn[] = {12.70,9.05,8.16,7.50,6.96,6.75,6.32,6.09,5.98,4.25,4.02,2.78,2.76,2.4,2.36,2.22,2.01,1.97,1.93,1.5,0.97,0.77,0.153,0.15,0.095,0.074};
		//French
		double freqFr[] = {14.71,7.95,7.63,7.53,7.24,7.1,6.69,6.31,5.79,5.45,3.66,3.26,2.96,2.52,1.83,1.50,1.36,1.06,0.9,0.866,0.737,0.613,0.486,0.427,0.326,0.271,0.218,0.128,0.085,0.074,0.058,0.051,0.049,0.045,0.023,0.018,0.008,0.005};
		//Spanish
		double freqSp[] = {12.181,11.525,8.683,7.977,6.871,6.712,6.247,5.010,4.967,4.632,4.019,3.157,2.927,2.510,2.215,1.786,1.138,1.008,0.877,0.827,0.725,0.703,0.692,0.502,0.493,0.467,0.433,0.311,0.215,0.168,0.017,0.012,0.011};
		//German
		double freqDe[] = {16.396,9.776,7.27,7.003,6.550,6.516,6.154,5.076,4.577,4.166,3.437,3.009,2.732,2.594,2.534,1.921,1.886,1.656,1.417,1.134,0.995,0.846,0.670,0.578,0.443,0.307,0.268,0.039,0.034,0.018};
		//Italian
		double freqIt[] = {11.792,11.745,10.143,9.832,6.883,6.510,6.367,5.623,4.981,4.501,3.736,3.056,3.011,2.512,2.097,1.644,1.181,1.153,0.927,0.636,0.635,0.505,0.263,0.166,0.033,0.030,0.020,0.011,0.009,0.003,0.002};
		//Danish
		double freqDn[] = {15.453,8.956,7.24,6.862,6.025,6.00,5.858,5.229,4.636,4.077,3.395,3.237,2.406,2.332,2.00,1.979,1.756,1.621,1.190,0.939,0.872,0.730,0.698,0.565,0.069,0.034,0.028,0.007};
		//Finnish
		double freqFn[] = {12.217,10.817,8.826,8.750,8.968,8.750,7.968,7.862,5.761,5.614,5.008,4.973,3.577,3.202,2.872,2.250,2.042,1.851,1.842,1.745,1.043,0.444,0.392,0.281,0.194,0.094,0.051,0.031,0.13,0.003};
		//Icelandic
		double freqIc[] = {10.110,8.581,7.711,7.578,6.418,5.630,4.953,4.562,4.532,4.393,4.241,4.041,3.314,3.013,2.437,2.166,1.871,1.799,1.575,1.570,1.455,1.144,1.043,0.994,0.900,0.867,0.789,0.777,0.647,0.613,0.228,0.046,};
		//Swedish
	    double freqSw[] = {10.149,9.383,8.542,8.431,7.691,6.590,5.817,5.275,4.702,4.482,3.471,3.140,2.862,2.415,2.090,2.027,1.919,1.839,1.797,1.535,1.486,1.338,1.305,0.708,0.614,0.159,0.142,0.070,0.020};
	    
	    double languageFreq[][] = new double[9][1];
		languageFreq[0] = freqEn;
		languageFreq[1] = freqFr;
		languageFreq[2] = freqSp;
		languageFreq[3] = freqDe;
		languageFreq[4] = freqIt;
		languageFreq[5] = freqDn;
		languageFreq[6] = freqFn;
		languageFreq[7] = freqIc;
		languageFreq[8] = freqSw;
		
		return languageFreq;
	}
	
	

}