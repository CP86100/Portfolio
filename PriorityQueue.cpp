/**
*    Corentin Pinato
*    #14739811
*    
*    Priority Queue in C++ 
*/

#include <iostream>

using namespace std;

class Queue
{
    int first, last, size, max;
    int array[2];

    public:
   
    void set (int);

    bool isFull()
    {
        return (Queue::max == Queue::size);
    }
    bool isEmpty()
    {
        return (Queue::size == 0);
    }
    
    void insert (int num)
    {
        if(isEmpty())
        {
            Queue::first = 0;
            Queue::last = 0;
            Queue::array[0] = num;
            Queue::size++;
            cout<<"success, "<<num<<" set to [0]"<<endl;
        }
        else if(isFull())
        {
            cout<<"Unsuccessful, queue is full."<<endl;
        }
        else
        {
            int index = Queue::size;
            while(index > 0 && Queue::array[index-1] > num)
            {
                Queue::array[index] = Queue::array[index-1];
                index--;
            }
            Queue::array[index] = num;
            Queue::last++;
            Queue::size++; 
            cout<<"success, "<<num<<" set to ["<<index<<"]"<<endl;
        }
    }

    void peekFront()
    {
        cout<<Queue::array[Queue::first]<<endl;
    }
    void peekLast()
    {
        cout<<Queue::array[Queue::last]<<endl;
    }
    void peekIndex(int index)
    {
        cout<<Queue::array[index]<<endl;
    }
    void remove ()
    {
         int temp = Queue::array[Queue::first];
         
         if(!isEmpty())
         {
             Queue::first++;
             if(Queue::first == Queue::max)
             {
                 Queue::first = 0;
             }
             Queue::size--;
         }
         cout<<temp<<" successfuly removed"<<endl;
    }
 
};
void Queue::set (int size)
{
    Queue::max = size;
    Queue::size = 0;
    Queue::first = -1;
    Queue::last = -1;
    Queue::array[size];
}

int main()
{
    //These are commands to test the functionality of the priority queue.
    Queue q; q.set(5);
    q.insert(2);
    q.insert(10);
    q.insert(5);
    q.insert(1);
    q.insert(50);
    q.peekFront();
    q.peekIndex(1); q.peekIndex(2); q.peekIndex(3); q.peekIndex(4);
    q.peekLast();
    q.remove();
    q.peekFront();
}
