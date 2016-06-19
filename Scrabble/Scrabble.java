package CS211;

import java.io.*;
import java.util.*;
/**
 * This program uses a dictionary to find the longest word possible with given letters.
 * @author Corentin Pinato
 * @ version 1.0
 *
 */
public class Scrabble
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your letters.");
		String letters = sc.nextLine();
		System.out.println("Must have letter");
		String letter = sc.nextLine();
		sc.close();
		letters += letter;
		String dictionary[] = dictionary(), words[] = getWords(dictionary,letters,letter);
		System.out.println(Arrays.toString(words));
		words = sort(words);
		System.out.println(Arrays.toString(words));
	}
	public static String[] sort(String[] words)
	{
		for(int i = 0; i < words.length; i++)
			for(int n = 0; n < words.length-1; n++)
			{
				if(words[n].length() < words[n+1].length())
				{
					String temp = words[n];
					words[n] = words[n+1];
					words[n+1] = temp;
				}
			}
		return Arrays.copyOfRange(words, 0, 10);
	}
	public static String[] getWords(String dictionary[], String letters, String letter)
	{
		int count = 0;
		for(int i = 0; i < dictionary.length; i++)
			if(hasLetters(dictionary[i], letters))
				count++;
		String answer[] = new String[count];
		int n = 0;
		for(int i = 0; i < dictionary.length; i++)
			if(hasLetters(dictionary[i], letters))
				answer[n++] = dictionary[i];
		count = 0;
		n = 0;
		for(int i = 0; i < answer.length; i++)
			if(has(answer[i],letter))
				count++;
		String output[] = new String[count];
		for(int i = 0; i < answer.length; i++)
			if(has(answer[i],letter))
				output[n++] = answer[i];
		return output;
	}
	public static boolean has(String word, String letter)
	{
		boolean has = false;
		for(int i = 0; i < word.length(); i++)
			if(word.charAt(i) == letter.charAt(0))
				has = true;
		
		return has;
	}
	public static boolean hasLetters(String word, String Letters)
	{
		String letters = Letters;
		boolean hasLetters = true;
		int n = 0;
		while(hasLetters && n < word.length())
		{
			int i = 0;
			boolean found = false;
			while(i < letters.length() && !found)
			{
				if(word.charAt(n) == letters.charAt(i))
				{
					letters = letters.substring(0,i)+letters.substring(i+1,letters.length());
					found = true;
				}
				i++;
			}
			if(!found)
				hasLetters = false;
			n++;
		}
		return hasLetters;
	}
	public static String[] dictionary()
	{
		File file = new File("C:\\Users\\Corentin\\Desktop\\dictionary.txt");
		String dictionary[] = new String[216555];
		try{Scanner sc = new Scanner(file);
			for(int i = 0; i < dictionary.length; i++)
				dictionary[i] = sc.nextLine();
			sc.close();	
		}catch(FileNotFoundException ex)
		{System.out.println("Error" +ex);}
		return dictionary;
	}

}
