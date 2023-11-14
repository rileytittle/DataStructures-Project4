/**
 * Node class represents a node in a binary search tree. It has
 * variables to hold data and variables to hold the reference of 
 * its two children nodes. 
 * @author Riley Tittle
 * @version 11.14.2023
 */
public class Node {
	private String countryName;
	private double happinessIndex;
	public Node leftChild;
	public Node rightChild;
	
	/**
	 * Node is the constructor of the Node class. It takes a string
	 * named countryName and a double named happinessIndex to set
	 * the name and happiness index of the country the node
	 * represents on the tree. 
	 * @param countryName the name of the country being assigned to the Node
	 * @param happinessIndex the happiness index of the country being assigned to the Node
	 */
	public Node(String countryName, double happinessIndex){
		this.countryName = countryName;
		this.happinessIndex = happinessIndex;
		leftChild = null;
		rightChild = null;
	}//end Node constructor
	
	/**
	 * getName returns the name of the Country that the node represents.
	 * @return the name of the country that the node represents.
	 */
	public String getName(){
		return countryName;
	}//end getName
	
	/**
	 * getHappiness returns the happiness index of the country the node represents.
	 * @return the happiness index of the country the node represents. 
	 */
	public double getHappiness(){
		return happinessIndex;
	}//end getHappiness
	
	/**
	 * print prints out the name and happiness index of the country
	 * represented by the node to the console. 
	 */
	public void print(){
		//System.out.println(this.name + "\t" + this.capital + "\t" + this.Gdp + "\t" + this.area + "\t" + this.happyIndex);
		//String test = "Cole";
		//String tset = "Tittle";
		//System.out.printf("%s%s", test, tset);
		System.out.printf("%-38s%-18.3f", countryName, happinessIndex);
		System.out.println();
	}//end print method
}//end Node class
