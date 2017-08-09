import javax.swing.SwingUtilities;

import code.model.Player_024_062;
import code.view.ScrabbleView_062;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver_062 { //Ali and Noah 4/9 -- ones written by noah also 4/9 (5) = noah and ali (noah driving) 4/10
	
	
	public static void main(String[] args) {
		
		if(args.length == 0){
			System.out.println("Error: No arguments passed in");
			System.exit(0);
		}
		
		Scanner scan = null;
		String dictionary = null; //noah
		String restoreFilePath = null; //noah
		String[] theArgs = new String[0]; //(5)
		
		try {
			scan = new Scanner(new File(args[0]));
			if(args.length == 1) {				  //restoring the previous state
				if(scan.nextLine() == "20 20"){
					restoreFilePath = args[0];
				}
				else {
					System.out.println("Error: Invalid Argument");
					System.exit(0);
				}
			}
			
			if(args.length == 2) {
				System.out.println("Error: Not enough arguments");
				System.exit(0);
			}
			
			
			//(5)
			if(args.length > 2 && args.length < 6) {
				if(scan.nextLine().equals("DICTIONARY")) {
					theArgs = new String[args.length];
					for (int i = 0; i < args.length; i++) {
						theArgs[i] = args[i];
					}
				}
				dictionary = args[0];
			}
			
		}
		
		catch (FileNotFoundException e) {
			System.err.println("File not found: "+args[0]);
		}
		
		finally {
			scan.close();
		}
		
		SwingUtilities.invokeLater(new ScrabbleView_062(dictionary, restoreFilePath, theArgs)); //noah 4/9
	}

}
