
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
					}
				}
			}
		}
	}
}
