
public class Node {
	private String countryName;
	private double happinessIndex;
	public Node leftChild;
	public Node rightChild;
	
	public Node(String countryName, double happinessIndex){
		this.countryName = countryName;
		this.happinessIndex = happinessIndex;
		leftChild = null;
		rightChild = null;
	}
	
	public String getName(){
		return countryName;
	}
	
	public double getHappiness(){
		return happinessIndex;
	}
	
	public void print(){
		//System.out.println(this.name + "\t" + this.capital + "\t" + this.Gdp + "\t" + this.area + "\t" + this.happyIndex);
		//String test = "Cole";
		//String tset = "Tittle";
		//System.out.printf("%s%s", test, tset);
		System.out.printf("%-38s%-18.3f", countryName, happinessIndex);
		System.out.println();
	}//end print method
}
