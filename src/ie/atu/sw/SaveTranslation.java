package ie.atu.sw;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveTranslation { // O(n)
	
	public static void SaveToFile(String content, char type) throws FileNotFoundException {
		PrintWriter outputFile = null; // O(1). creates empty PrintWriter object
		
		if(type == 'm') { // If m is passed in, generate encoded morse txt file
			outputFile = new PrintWriter("EnglishToMorse.txt"); // O(1)
		}
		else if (type == 'e') { // if e is passed in, generate decoded english txt file
			outputFile = new PrintWriter("MorseToEnglish.txt"); // O(1)
		}
		// Print contents to file and close
		outputFile.println(content); // O(N)
		outputFile.close(); // O(1)
	}
}
