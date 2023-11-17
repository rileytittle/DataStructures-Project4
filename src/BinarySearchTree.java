import java.util.NoSuchElementException;
/**
 * The BinarySearchTree class implements a binary search tree data structure
 * using the Node class for the individual nodes. The class has methods to insert, find, 
 * delete, and print the tree to the console in many different ways. 
 * 
 * @author Riley Tittle
 * @version 11.16.2023
 */
public class BinarySearchTree {
	private Node root;
	private Integer counter = 0;
	private int numOfNodes = 0;
	private boolean showInsertMessage = false;
	/**
	 * BinarySearchTree method is the constructor for the class.
	 * It sets the root to null, indicating an empty tree.
	 */
	public BinarySearchTree(){
		root = null;
	}
	/**
	 * setShowInsertMessage method takes a boolean value to set the showInsertMessage
	 * variable.
	 * @param value either true or false, depending on whether the user wants the insert message to show
	 */
	public void setShowInsertMessage(boolean value){
		showInsertMessage = value;
	}
	/**
	 * insertMessage prints out a message to the console telling the user
	 * the country they gave as input has been inserted. 
	 * @param name the name of the inserted country
	 * @param happiness the happiness index of the inserted country
	 */
	private void insertMessage(String name, double happiness){
		System.out.println("\n" + name + " with happiness of " + happiness + " is inserted.\n");
	}
	
	/**
	 * insert method takes the name of the country and the happiness index of the country
	 * to be inserted and inserts a new node into the binary search tree. 
	 * @param name the name of the inserted country
	 * @param happiness the happiness index of the inserted country
	 */
	public void insert(String name, double happiness){
		Node newNode = new Node(name, happiness);
		if(root == null){
			root = newNode;
			numOfNodes++;
			if(showInsertMessage){
				insertMessage(name, happiness);
			}
		}
		else{
			Node current = root;
			Node parent;
			while(true){
				parent = current;
				if(newNode.getName().compareToIgnoreCase(current.getName()) == 0){
					System.out.println("Country already exists in tree");
					return;
				}
				if(newNode.getName().compareTo(current.getName()) < 0){
					current = current.leftChild;
					if(current == null){
						parent.leftChild = newNode;
						numOfNodes++;
						if(showInsertMessage){
							insertMessage(name, happiness);
						}
						return;
					}
				}
				else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
						numOfNodes++;
						if(showInsertMessage){
							insertMessage(name, happiness);
						}
						return;
					}//end if
				}//end else
			}//end while loop
		}//end else
	}//end insert method
	
	/**
	 * find method takes the name of the country you want to find as a parameter
	 * and searches through the binary search tree to locate a node with that name.
	 * It tells the user whether it finds the node or not. 
	 * @param name the name of the country to be found
	 * @return the happiness index of the country if it is found, -1 otherwise.
	 */
	public double find(String name){
		String path = "";
		Node current = root;
		while(!current.getName().equalsIgnoreCase(name)){
			if(current.getName() != null){
				path += current.getName() + " -> ";
			}
			if(name.compareToIgnoreCase(current.getName()) < 0){
				current = current.leftChild;
			}
			else{
				current = current.rightChild;
			}
			if(current == null){
				System.out.println("\n" + name + " is not found\n");
				return -1;
			}
		}//end else
		System.out.println(name + " is found with happiness of " + current.getHappiness());
		System.out.println("Path to " + name + " is " + path + name + "\n");
		return current.getHappiness();
	}//end find method
	
	/**
	 * delete method takes the name of the country you want to delete as a parameter and 
	 * searches the binary tree for a node with that name, deleting it if found.
	 * @param name the name of the country to delete from the tree
	 */
	public void delete(String name){
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while(current.getName().compareTo(name) != 0){
			parent = current;
			if(current.getName().compareTo(name) > 0){
				isLeftChild = true;
				current = current.leftChild;
			}
			else{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null){
				System.out.println("\n" + name + " is not found.");
				return;
			}
		}
		if(current.leftChild == null && current.rightChild == null){
			if(current == root){
				root = null;
			}
			else if(isLeftChild){
				parent.leftChild = null;
			}
			else{
				parent.rightChild = null;
			}
		}
		else if(current.rightChild == null){
			if(current == root){
				root = current.leftChild;
			}
			else if(isLeftChild){
				parent.leftChild = current.leftChild;
			}
			else{
				parent.rightChild = current.leftChild;
			}
		}
		else if(current.leftChild == null){
			if(current == root){
				root = current.rightChild;
			}
			else if(isLeftChild){
				parent.leftChild = current.rightChild;
			}
			else{
				parent.rightChild = current.rightChild;
			}
		}
		else{
			Node successor = getSuccessor(current);
			if(current == root){
				root = successor;
				successor.leftChild = current.leftChild; //this line has been added by me
			}
			else if(isLeftChild){
				parent.leftChild = successor;
			}
			else{
				parent.rightChild = successor;
				successor.leftChild = current.leftChild;
			}
		}
		System.out.println("\n" + name + " is deleted from binary search tree.\n");
		numOfNodes--;
		return;
	}//end delete method
	
	/**
	 * getSuccessor takes the Node you want to delete as a parameter and then
	 * finds the successor for that node in the tree. 
	 * @param delNode the node to be deleted
	 * @return the successor node
	 */
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if(successor != delNode.rightChild){
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	/**
	 * printInorder calls the inOrder method, passing the root of the tree as a parameter.
	 */
	public void printInorder(){
		inOrder(root);
	}//end printInorder
	/**
	 * inOrder method is a recursive method that traverses and prints the tree in in-order.
	 * @param localRoot the root of the tree to be printed. 
	 */
	private void inOrder(Node localRoot){
		if(localRoot != null){
			inOrder(localRoot.leftChild);
			localRoot.print();
			inOrder(localRoot.rightChild);
		}
	}
	/**
	 * printPreorder calls the preOrder method, passing the root of the tree as a parameter.
	 */
	public void printPreOrder(){
		preOrder(root);
	}
	/**
	 * preOrder method is a recursive method that traverses and prints the tree in pre-order.
	 * @param localRoot the root of the tree to be printed. 
	 */
	private void preOrder(Node localRoot){
		if(localRoot != null){
			localRoot.print();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	/**
	 * printPostorder calls the postOrder method, passing the root of the tree as a parameter.
	 */
	public void printPostorder(){
		postOrder(root);
	}
	/**
	 * postOrder method is a recursive method that traverses and prints the tree in post-order.
	 * @param localRoot the root of the tree to be printed. 
	 */
	private void postOrder(Node localRoot){
		if(localRoot != null){
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.print();
		}
	}
	/**
	 * printBottomCountries takes an integer as a parameter, which represents how many of the
	 * bottom countries to print to the console. The method finds the bottom c countries, and prints them
	 * to the console.  
	 * @param c the number of bottom countries to print
	 */
	public void printBottomCountries(int c){
		if(c > numOfNodes){
			c = numOfNodes;
		}
		Node[] nodeArray = new Node[numOfNodes];
		Node[] bottomCountries = new Node[c];
		getAllNodes(root, nodeArray);
		counter = 0;
		Node min;
		Node previousMin = new Node("first", 0.0);
		for(int i = 0; i < c; i++){
			min = new Node("min", 10.0);
			for(int j = 0; j < nodeArray.length; j++){
				if(nodeArray[j].getHappiness() < min.getHappiness() && nodeArray[j].getHappiness() > previousMin.getHappiness()){
					min = nodeArray[j];
				}
			}
			bottomCountries[i] = min;
			previousMin = min;
		}
		for (Node node : bottomCountries) {
			node.print();
		}
	}
	/**
	 * printTopCountries takes an integer as a parameter, which represents how many of the
	 * top countries to print to the console. The method finds the top c countries, and prints them
	 * to the console.  
	 * @param c the number of top countries to print
	 */
	public void printTopCountries(int c) {
		if(c > numOfNodes){
			c = numOfNodes;
		}
		Node[] nodeArray = new Node[numOfNodes];
		Node[] topCountries = new Node[c];
		getAllNodes(root, nodeArray);
		counter = 0;
		Node max;
		Node previousMax = new Node("first", 10.0);
		for(int i = 0; i < c; i++){
			max = new Node("max", 0);
			for(int j = 0; j < nodeArray.length; j++){
				if(nodeArray[j].getHappiness() > max.getHappiness() && nodeArray[j].getHappiness() < previousMax.getHappiness()){
					max = nodeArray[j];
				}
			}
			topCountries[i] = max;
			previousMax = max;
		}
		for (Node node : topCountries) {
			node.print();
		}
	}
	/**
	 * getAllNodes traverses the tree just like the preOrder method, but instead of printing the 
	 * nodes it reaches, it adds them to an array. It adds every node from the tree onto the array.
	 * @param localRoot the root of the tree to add to the array.
	 * @param nodeArray the array which will hold all the nodes of the tree.
	 */
	private void getAllNodes(Node localRoot, Node[] nodeArray){
		if(localRoot != null){
			//localRoot.print();
			nodeArray[counter++] = localRoot;
			getAllNodes(localRoot.leftChild, nodeArray);
			getAllNodes(localRoot.rightChild, nodeArray);
		}
	}
}//end BinarySearchTree class
