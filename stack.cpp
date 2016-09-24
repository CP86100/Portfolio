
#include <iostream>
#include <string>

class Stack
{

	int max;
	int top;
	int arr[2];
public:

        void setSize(int);

	void push(int input)
	{
		if (!Stack::isFull())
		{
			Stack::top++;
			arr[Stack::top] = input;
			std::cout << input << " pushed successfully" << std::endl;
		}
		else
		{
			std::cout << "push unsuccessful, stack is full"<<std::endl;
		}
	}
	bool isFull()
	{
		return (top == max-1);
	}
	bool isEmpty()
	{
		return (top == -1);
	}
	void peek()
	{
		if (!Stack::isEmpty())
			std::cout<<"[peek] = "<<arr[Stack::top]<<std::endl;
		else
		{
			std::cout << "peek unsuccesfful, stack is empty";
		}
	}
	void pop()
	{
		if (!Stack::isEmpty())
		{
			int output = arr[Stack::top];
			Stack::top--;
			std::cout<<"[pop] = "<<output<<std::endl;
		}
		else
		{
			std::cout << "pop unsuccessful, stack is empty";
		}
	}
};
void Stack::setSize(int size)
{
    top = -1;
    max = size;
    arr[size];
}

int main()
{
	int size;

	Stack st;

	std::cout << "Enter the size of the stack" << std::endl;
	std::cin >> size;
	st.setSize(size);

	bool quit = false;
	std::string cmd;
	int num;

	while (!quit)
	{
		std::cout << "Enter a command [pop] [push] [peek] [quit]" << std::endl;
		std::cin >> cmd;

		if (cmd.compare("quit") == 0)
		{
			quit = true;
			std::cout << "End of program"<<std::endl;
		}
		else if (cmd.compare("push") == 0)
		{
			std::cout << "Enter the number to push" << std::endl;
			std::cin >> num;
			st.push(num);
		}
		else if (cmd.compare("pop") == 0)
			st.pop();
		else if (cmd.compare("peek") == 0)
			st.peek();

	}
    return 0;
}
