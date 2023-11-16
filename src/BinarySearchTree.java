import java.util.NoSuchElementException;

public class BinarySearchTree {
	private Node root;
	public BinarySearchTree(){
		root = null;
	}
	
	public void insert(String name, double happiness){
		Node newNode = new Node(name, happiness);
		if(root == null){
			root = newNode;
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
						return;
					}
				}
				else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
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
				return -1;
			}
		}//end else
		System.out.println(name + " is found with happiness of " + current.getHappiness());
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
				successor.leftChild = current.leftChild;
			}
			else if(isLeftChild){
				parent.leftChild = successor;
			}
			else{
				parent.rightChild = successor;
				successor.leftChild = current.leftChild;
			}
		}
		return;
	}//end delete method
	
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode; //usa
		Node successor = delNode;//usa
		Node current = delNode.rightChild; //wakanda
		while(current != null){
			successorParent = successor; //1) usa 2)wakanda
			successor = current;//1) wakanda 2) vienna
			current = current.leftChild;//1)vienna 2) null
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
		
	}
}//end BinarySearchTree class
