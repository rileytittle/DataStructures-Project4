import java.util.NoSuchElementException;

public class BinarySearchTree {
	private Node root;
	private Integer counter = 0;
	private int numOfNodes = 0;
	private boolean showInsertMessage = false;
	public BinarySearchTree(){
		root = null;
	}
	
	public void setShowInsertMessage(boolean value){
		showInsertMessage = value;
	}
	private void insertMessage(String name, double happiness){
		System.out.println("\n" + name + "with happiness of " + happiness + " is inserted.");
	}
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
				System.out.println("\n" + name + " is not found");
				return -1;
			}
		}//end else
		System.out.println(name + " is found with happiness of " + current.getHappiness() + "\n");
		System.out.println("Path to " + name + " is " + path + name);
		return current.getHappiness();
	}
	//I think this works at the moment. Ran with some test code and it
	//performed fine
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
	
	public void printInorder(){
		inOrder(root);
	}//end printInorder
	private void inOrder(Node localRoot){
		if(localRoot != null){
			inOrder(localRoot.leftChild);
			localRoot.print();
			inOrder(localRoot.rightChild);
		}
	}
	public void printPreOrder(){
		preOrder(root);
	}
	private void preOrder(Node localRoot){
		if(localRoot != null){
			localRoot.print();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	public void printPostorder(){
		postOrder(root);
	}
	private void postOrder(Node localRoot){
		if(localRoot != null){
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.print();
		}
	}
	
	public void printBottomCountries(int c){
		Node[] nodeArray = new Node[numOfNodes];
		Node[] bottomCountries = new Node[c];
		getAllNodes(root, nodeArray);
		counter = 0;
		Node min;
		Node previousMin = new Node("first", 0.0);
		for(int i = 0; i < c; i++){
			min = nodeArray[0];
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
	
	public void printTopCountries(int c) {
		Node[] nodeArray = new Node[numOfNodes];
		Node[] topCountries = new Node[c];
		getAllNodes(root, nodeArray);
		counter = 0;
		Node max;
		Node previousMax = new Node("first", 10.0);
		for(int i = 0; i < c; i++){
			max = nodeArray[0];
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
	private void getAllNodes(Node localRoot, Node[] nodeArray){
		if(localRoot != null){
			//localRoot.print();
			nodeArray[counter++] = localRoot;
			getAllNodes(localRoot.leftChild, nodeArray);
			getAllNodes(localRoot.rightChild, nodeArray);
		}
	}
}//end BinarySearchTree class
