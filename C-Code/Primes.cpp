/**
* Corentin Pinato
* #14739811
*
* This program prints the primes form 2-5000.
*/

#include <iostream>

using namespace std;

bool isPrime(int num)
{
    if(num <= 1)
        return false;
    if(num <= 3)
        return true;
    for(int i = 2; i < num/2; i++)
    {
        if(num%i==0)
           return false;
    }
    return true;
}

int main()
{

    for(int i = 0; i < 5000; i++)
    {
         if(isPrime(i))
             cout<<i<<endl;
    }
}
