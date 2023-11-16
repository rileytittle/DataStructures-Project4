import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.TransferHandler;

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

		System.out.println("There were " + recordsRead + " records read to build a binary search tree\n");

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
				theTree.printInorder();
				break;
			case 2:
				theTree.printPreOrder();
				break;
			case 3:
				theTree.printPostorder();
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
				
				break;
			case 8:
				theTree.printTopCountries(5); 
				break;
			case 9:
				programNotFinished = false;
				break;
			}
		}while(programNotFinished);
		System.out.println("\nHave a good day!");

	}

}
