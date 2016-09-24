
public class BinaryTree
{
	public static void main(String args[])
	{
		Btree tree = new Btree();
		tree.insert(15);
		tree.insert(20);
		tree.insert(25);
		tree.insert(16);
		tree.insert(10);
		tree.insert(8);
		tree.insert(12);
		
	//  System.out.println(tree.size());
	//	System.out.println(tree.find(25));
	//	System.out.println(tree.largest());
		
		tree.delete(tree.root());
		System.out.println(tree.root());
	//	System.out.println(tree.size());
	//	System.out.println(tree.find(25));
		
	//	System.out.println(tree.root.left.data);
		
		tree.delete(tree.smallest());
		System.out.println(tree.smallest());
	//	System.out.println(tree.size());
	//	System.out.println(tree.find(20));
		
		tree.delete(tree.largest());
		System.out.println(tree.largest());
		System.out.println(tree.size());
	}
	public static class Node
	{
		int data;
		Node left, right, parent;
		
		public Node()
		{
			data = 0;
			left = null;
			right = null;
			parent = null;
		}
		public Node(int i)
		{
			data = i;
			left = null;
			right = null;
			parent = null;
		}
	}
	public static class Btree
	{
		Node root;
		int nElements;
		
		public Btree()
		{
			root = null;
			nElements = 0;
		}
		
		public int size()
		{
			return nElements;
		}
		public void insert(int i)
		{
			if(root == null)
			{
				root = new Node(i);
			}
			else
			{
				Node current = root;
				boolean placed = false;
				
				while(!placed)
				{
					if(i < current.data)
					{
						if(current.left == null)
						{
							current.left = new Node(i);
							current.left.parent = current;
							placed = true;
						}
						else
							current = current.left;
							
					}
					else
					{
						if(current.right == null)
						{
							current.right = new Node(i);
							current.right.parent = current;
							placed = true;
						}
						else
							current = current.right;
					}
				}
			}
			nElements++;
		}
		public boolean find(int i)
		{
			Node current = root;
			while(current != null)
			{
				if(i == current.data)
					return true;
				else
				{
					if(i < current.data)
						current = current.left;
					if(i > current.data)
						current = current.right;	
				}
					
			}
			return false;
		}
		public void delete(int i)
		{
			Node current = root;
			while(current.data != i)
			{
				if(i < current.data)
					current = current.left;
				else
					current = current.right;	
			}
			
			if(current.left == null && current.right == null)
			{
							if(current == root)
				root = null;
			else
			{
				current = current.parent;
			    if(current.left.data == i && current.left!= null)
				    current.left = null;
			    else
				    current.right = null;	
			}	
			}
			else if(current.left != null && current.right != null)
			{
				Node successor = current.right;
			
			while(successor.left != null)
				successor = successor.left;
			
			current.data = successor.data;
			if(successor.parent != current)
				successor.left = null;
			else
				delete(successor.data);	
				
			}
			else
			{
				if(current.left != null)
			{
				current.data = current.left.data;
				current.left = current.left.left;
			}
			else
			{
				current.data = current.right.data;
				current.right = current.right.right;
			}
			}
			nElements--;	
					
		}
		public int root()
		{
			return root.data;
		}
		public void inOrder(Node current)
		{
			if(current != null)
			{
				inOrder(current.left);
				System.out.print(current.data+" ");
				inOrder(current.right);
			}
		}
		public void pre(Node current)
		{
			if(current != null)
			{
				System.out.print(current.data+" ");
				pre(current.left);
				pre(current.right);
			}
		}
		public void post(Node current)
		{
			if(current != null)
			{
				post(current.left);
				post(current.right);
				System.out.print(current.data+" ");
			}
		}
		public int smallest()
		{
			Node current = root;
			while(current.left != null)
				current = current.left;
			return current.data;	
		}	
			public int largest()
			{
				Node current = root;
				while(current.right != null)
					current = current.right;
				return current.data;	
			}
			
	}
	
}