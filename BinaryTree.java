
public class BinaryTree
{
	public static void main(String arsg[])
	{
		Tree tree = new Tree();

		tree.insert(18);
		tree.insert(7);
		tree.insert(5);
		tree.insert(12);
		tree.insert(6);
		tree.insert(23);
		tree.insert(50);
		tree.insert(1);
		tree.insert(2);
		tree.insert(100);
		tree.insert(20);

	    System.out.println(tree.size());

		tree.delete(18);
		tree.delete(7);
    	tree.delete(tree.smallest());
		tree.delete(tree.largest());
		tree.delete(tree.largest());
		System.out.println(tree.find(12)+"+"+tree.find(2));

		System.out.println("smallest: "+tree.smallest()+" largest: "+tree.largest()+" root: "+tree.root());
		System.out.println(tree.size());
		inOrder(tree);
		preOrder(tree);
		postOrder(tree);



	}
	public static void postOrder(Tree tree)
	{
		post(tree.root);
	}
	public static void post(Node node)
	{
		if(node != null)
		{
			post(node.left);
			post(node.right);
			System.out.print(node.data+" ");
		}
	}
	public static void inOrder(Tree tree)
	{
		order(tree.root);
	}
	public static void order(Node node)
	{
		if(node != null)
		{
			order(node.left);
			System.out.print(node.data + " ");
			order(node.right);
		}
	}
	public static void preOrder(Tree tree)
	{
		pre(tree.root);
	}
	public static void pre(Node node)
	{
		if(node != null)
		{
			System.out.print(node.data+" ");
			pre(node.left);
			pre(node.right);
		}
	}
	static class Node
	{
		int data;
		Node right;
		Node left;
		Node parent;

		public Node()
		{
			data = 0;
			right = null;
			left = null;
			parent = null;
		}

		public Node(int i)
		{
			data = i;
			right = null;
			left = null;
			parent = null;
		}
	}
	static class Tree
	{
		Node root;
		int nElements;

		public Tree()
		{
			root = null;
			nElements = 0;
		}
		public void insert(int i)
		{
			Node newNode = new Node(i);
			boolean placed = false;
			nElements++;

			if(root == null)
			{
				root = newNode;
				placed = true;
			}
			else
			{
				Node current = root;
				while(!placed)
				{

				if(i < current.data)
				{

					if(current.left == null)
					{
						current.left = newNode;
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
						current.right = newNode;
						current.right.parent = current;
						placed = true;
					}
					else
						current = current.right;
				}
				}
			}
			if(placed)
				System.out.println("added");
		}
		public boolean find(int i)
		{
			Node current = root;

			while(current.data != i)
			{
				if(i < current.data)
				{
					current = current.left;
				}
				else
					current = current.right;
				if(current == null)
					return false;
			}
			return true;
		}
		public int smallest()
		{
			Node current = root;
			while(current.left != null)
			{
				current = current.left;
			}
			return current.data;
		}
		public int largest()
		{
			Node current = root;
			while(current.right != null)
			{
				current = current.right;
			}
			return current.data;
		}
		public int root()
		{
			return root.data;
		}
		public void delete(int i)
		{
			Node current = root;

			while(current.data != i)
			{
				if(i < current.data)
				{
					current = current.left;
				}
				else
					current = current.right;
			}

			if(current.left == null && current.right == null)
			{
				current = current.parent;
				if(current.left != null && current.left.data == i)
					current.left = null;
				else
					current.right = null;
			}
			else if(current.left != null && current.right != null)
			{
				Node successor = current.right;

				while(successor.left != null)
				{
					successor = successor.left;
				}
				current.data = successor.data;
				successor = successor.parent;
				if(successor.left.data == current.data)
					successor.left = null;
				else
				{
					current.right=successor.right;
					current.right = null;
				}

			}
			else
			{
				if(current.left != null)
				{
					current.data = current.left.data;
					current.left = current.left.left;
					if(current.left.parent != null)
						current.left.parent = current;
				}
				else
				{
					current.data = current.right.data;
					current.right = current.right.right;
					if(current.right != null)
						current.right.parent = current;
				}
			}
			nElements--;
			System.out.println("deleted and updated");
		}
		public int size()
		{
			return nElements;
		}



	}
}