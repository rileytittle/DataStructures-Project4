import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.TransferHandler;
/**
 * COP 3530: Project 4 - Binary Search Trees
 * <p>
 * The project 4 class asks the user for the name of a csv file
 * to read from. It then reads the file and puts the name and 
 * happiness index from each line into a node and adds it to a 
 * binary search tree. 
 * <p>
 * After reading the file and creating a binary search tree, the
 * program gives the user a menu with multiple options to choose from. 
 * Some of the options print the tree in different ways and some of the
 * options modify the tree. The user can also choose the exit the program by typing the 
 * appropriate menu option. 
 * 
 * @author Riley Tittle
 * @version 11.16.2023
 */
public class Project4 {

	public static void main(String[] args) {
		System.out.println("COP3530 Project 4");
		System.out.println("Instructor: Xudong Liu");
		System.out.println("Student: Riley Tittle\n");
		System.out.print("Enter the file name: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();

		FileReader fileReader = null;
		Scanner fileScanner = null;
		boolean fileNotFound = false;
		do{
			try {
				fileReader = new FileReader(fileName);
				fileScanner = new Scanner(fileReader);
				fileNotFound = false;
			} catch (FileNotFoundException e) {
				fileNotFound = true;
				System.out.print("File not found. Try again: ");
				fileName = in.nextLine();
			}
		}while(fileNotFound);

		BinarySearchTree theTree = new BinarySearchTree();
		int recordsRead = 0;
		fileScanner.nextLine(); //discard the header
		while(fileScanner.hasNextLine()){
			String currentLine = fileScanner.nextLine();
			String[] lineData = currentLine.split(",");
			String countryName = lineData[0];
			double happinessIndex = Double.parseDouble(lineData[5]);
			theTree.insert(countryName, happinessIndex);
			recordsRead++;
		}

		System.out.println("\nThere were " + recordsRead + " records read to build a binary search tree\n");
		theTree.setShowInsertMessage(true);
		boolean programNotFinished = true;
		
		/*
		// test code
		Scanner in = new Scanner(System.in);
		boolean programNotFinished = true;
		BinarySearchTree theTree = new BinarySearchTree();
		theTree.insert("United States of America", 1.5434);
		theTree.insert("Wakanda", 1.5434);
		theTree.insert("Zimbabwe", 1.5434);
		theTree.insert("Afghanistan", 1.5434);
		theTree.insert("Vienna", 3.254);
		theTree.insert("Madagascar", 1.5434);
		theTree.insert("Nigeria", 3.5445);
		theTree.printInorder();
		*/
		do{
			System.out.println("1) Print tree inorder");
			System.out.println("2) Print tree preorder");
			System.out.println("3) Print tree postorder");
			System.out.println("4) Insert a country with name and happiness");
			System.out.println("5) Delete a country for a given name");
			System.out.println("6) Search and print a country and its path for a given name");
			System.out.println("7) Print bottom countries regarding happiness");
			System.out.println("8) Print top countries regarding happiness");
			System.out.println("9) Exit");
			System.out.print("Enter your choice: ");
			String menuChoice = "";
			boolean invalidChoice = false;
			do {
				try {
					menuChoice = in.nextLine();
					if(Integer.parseInt(menuChoice) > 9 || Integer.parseInt(menuChoice) < 1) {
						System.out.print("Invalid choice, enter 1-9: ");
						invalidChoice = true;
					}
					else {
						invalidChoice = false;
					}
				}
				catch(NumberFormatException e) {
					System.out.print("Invalid Choice, enter 1-9: ");
					invalidChoice = true;
				}
			}while(invalidChoice);

			switch(Integer.parseInt(menuChoice)){
			case 1:
				System.out.println();
				System.out.println("Inorder Traversal:");
				System.out.printf("%-38s%-18s\n", "Name", "Happiness");
				System.out.println("---------------------------------------------------");
				theTree.printInorder();
				System.out.println();
				break;
			case 2:
				System.out.println();
				System.out.println("Preorder Traversal:");
				System.out.printf("%-38s%-18s\n", "Name", "Happiness");
				System.out.println("---------------------------------------------------");
				theTree.printPreOrder();
				System.out.println();
				break;
			case 3:
				System.out.println();
				System.out.println("Postorder Traversal:");
				System.out.printf("%-38s%-18s\n", "Name", "Happiness");
				System.out.println("---------------------------------------------------");
				theTree.printPostorder();
				System.out.println();
				break;
			case 4:
				System.out.print("Enter country name: ");
				String nameToInsert = in.nextLine();
				System.out.print("Enter country happiness: ");
				double happiness = Double.parseDouble(in.nextLine());
				theTree.insert(nameToInsert, happiness);
				break;
			case 5:
				System.out.print("Enter country name: ");
				String nameToDelete = in.nextLine();
				theTree.delete(nameToDelete);
				break;
			case 6:
				System.out.print("Enter country name: ");
				String nameToFind = in.nextLine();
				theTree.find(nameToFind);
				break;
			case 7:
				System.out.print("Enter the number of countries: ");
				int numBottomCountries = Integer.parseInt(in.nextLine());
				System.out.println();
				theTree.printBottomCountries(numBottomCountries);
				System.out.println();
				break;
			case 8:
				System.out.print("Enter the number of countries: ");
				int numTopCountries = Integer.parseInt(in.nextLine());
				System.out.println();
				theTree.printTopCountries(numTopCountries); 
				System.out.println();
				break;
			case 9:
				programNotFinished = false;
				break;
			}
		}while(programNotFinished);
		System.out.println("\nHave a good day!");

	}

}
