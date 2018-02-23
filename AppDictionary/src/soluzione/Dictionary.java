package soluzione;

import java.io.BufferedReader;
import soluzione.Albero;
import soluzione.Levenshtein;
import java.io.FileReader;

import java.io.IOException;

public class Dictionary {
	
	/**
	 *   Class Dictionary
	 *   
	 *   The Dictionary application reads a name of file through  prompt of command,
	 *        the name of the file is passed as  the first argument  of String[] args 
	 *   the file read contains  commands  that allow the application to do the following operations
	 *   load, insert ,delete ,visualisation, research, calculate the editing  distance between two words of the dictionary
	 *     
	 *   the following methods are written to a file in output  
	 *     research, visualisation,  editing distance and the operations that were done
	 * @param args name of file
	 * @throws IOException   if file  is not found throws IOException
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader reader;
		Albero tree = new Albero();
		String temp, parola, parola1;
		String[] operation;
		String distance;

		if (args.length < 0) {
			System.err.println("Please enter name of file");
		}
     try {
		if (0 < args.length) {
			reader = new BufferedReader(new FileReader(args[0]));
			/**
			 * leggo il file cmd.txt riga per riga
			 */
			while ((temp = reader.readLine()) != null) {
				/**
				 * la prima posizione dell'array operation contiene il commando la seconda
				 * contiene il primo parametro e la terza posizione se c'è, contiene il secondo
				 * parametro.
				 */
				operation = temp.split(" ");

				switch (operation[0]) {
				/**
				 * upload words from dictionary to treeSet
				 */

				case "c":
					/**
					 * Loads words from a file and stores them into a TreeSet
					 */
					tree.carica(operation[1]);
					break;

				case "i":
					/**
					 * inserts words into dictionary
					 */
					tree.inserisci(operation[1]);
					break;

				case "v":
					/**
					 * Stamps all the words in the dictionary on to video
					 */
					System.out.println(tree.visualizza());
					break;

				case "e":
					/**
					 * Deletes a word from the dictionary
					 */
					tree.elimina(operation[1]);
					break;

				case "r":
					/**
					 * Given a Schema  , searches all the words that satisfy that schema 
					 */

					System.out.println(tree.ricerca(operation[1]));
					break;

				case "d":
   /**
    * uses levinshtein distance to calculate the editing distance  between two strings
    * and shows which operations were done in the editing process
    * 
    */                if(  operation[0].length()>0 )
    {
					parola = operation[1];
					parola1 = operation[2];
					System.out.println("d " + parola + " " + parola1 + ": " + Levenshtein.distance(parola, parola1));
					Levenshtein p= new Levenshtein();
					String allop;
					System.out.println(allop=p.toString());
					distance = "d " + parola + " " + parola1 + ": " + Levenshtein.distance(parola, parola1);
					 if(!tree.contains(parola))
						// tree.inserisci(parola);
					 if(!tree.contains(parola1))
						 //tree.inserisci(parola1);
					tree.StampDistance(distance,allop);

					break;
    }
				case "f":
					System.out.println("exiting");

					System.exit(0);

					break;

				default:
					System.out.println("operation not found");

				}
			}
			reader.close();
		}
     }catch(Exception e) {
    	 e.printStackTrace();
    	 // System.err.println("Insert a file containing commands with a txt extension  ");
      }
	}
}
