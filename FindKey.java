package CS211;

import java.math.*;
/**
 * This is a program to find the key and the message of a ElGamal encryption by brute force.
 * @author Corentin Pinato (14739811)
 * @version 1.0
 */
public class FindKey 
{
	public static void main(String args[])
	{
    	BigInteger publicKey[] = {new BigInteger("24852977"),new BigInteger("2744"),new BigInteger("8414508")};
	    BigInteger message[] = {new BigInteger("15268076"),new BigInteger("743675")};
		
		BigInteger Message = new BigInteger("1");

		BigInteger encrypted[] = encrypt(Message,publicKey,new BigInteger("1"));
	
		BigInteger key = new BigInteger("1");
		
		BigInteger decrypted = decrypt(publicKey,encrypted,key);
		while(decrypted.compareTo(Message) != 0)
		{
			key = key.add(new BigInteger("1"));
			decrypted = decrypt(publicKey,encrypted,key);
		}	
		System.out.println("The Private Key is: "+key);
		System.out.println("So the secret message is: "+decrypt(publicKey,message,key));
	}
	public static BigInteger decrypt(BigInteger publicKey[], BigInteger message[], BigInteger privateKey)
	{
		BigInteger p = publicKey[0];
		
		BigInteger gy = message[0];
		BigInteger mgxy = message[1];
		
		BigInteger p1x = p.subtract(new BigInteger("1")).subtract(privateKey);
		
		BigInteger c1 = gy.modPow(p1x, p);
		
        BigInteger c2 = mgxy.multiply(c1);
		c2 = c2.mod(p);
		
		return c2;
	}
	public static BigInteger[] encrypt(BigInteger message, BigInteger publicKey[], BigInteger randomNum)
	{
		BigInteger p = publicKey[0];
		BigInteger g = publicKey[1];
		BigInteger gxmodp = publicKey[2];
		

		BigInteger m1 = g.modPow(randomNum, p);
		BigInteger m2 = message.multiply(pow(gxmodp,randomNum)).mod(p);
		
		BigInteger result[] = {m1,m2};
		
		return result;
	}
	public static BigInteger pow(BigInteger num, BigInteger power)
	{
		BigInteger result = num;

		while( power.compareTo(new BigInteger("1"))>0)
		{
			result = result.multiply(num);
			power = power.subtract(new BigInteger("1"));
		}
		return result;
	}
}