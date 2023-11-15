
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
		System.out.println("Path to " + name + " is " + path + name);
		return current.getHappiness();
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
	
}//end BinarySearchTree class
