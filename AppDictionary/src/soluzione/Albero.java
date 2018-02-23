package soluzione;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Albero {
	/**
	 * Class Albero
	 * 
	 * @description The class contains methods for reading words from a file,
	 *              visualisation of words,insert and delete here is a brief
	 *              description of the methods
	 * 
	 *              the class uses a treeSet as a data structure to store the words
	 *              read from a file
	 */

	/* Filename: name of output file */
	private final String filename = "output-1.txt";
	/*
	 * see BufferedWriter
	 */
	private BufferedWriter writer;
	/**
	 * @see TreeSet
	 */
	TreeSet<String> tree;
	/**
	 * parola: words read from file. riga: line read from file
	 */
	private String parola, riga;
	/**
	 * @see StringBuilder
	 */
	StringBuilder sb;

	/**
	 * Creates a data structure of type TreeSet
	 * 
	 * @throws IOException
	 */
	public Albero() throws IOException {
		tree = new TreeSet<String>();
		writer = new BufferedWriter(new FileWriter(filename, true));

	}

	/**
	 * The method takes in input the name of a file reads the file line by line and
	 * splits the line into Strings using a stringTokenizer adds the words from the
	 * file into a TreeSet only strings that contain characters are added
	 * 
	 * @param file
	 *            :name of file
	 * @throws IOException
	 */
	public void carica(String file) throws IOException {
		BufferedReader bd = new BufferedReader(new FileReader(file));
		while ((riga = bd.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(riga);
			while (st.hasMoreTokens()) {
				parola = st.nextToken();
				if (parola.matches(".*[a-zA-Z]") && !(!parola.equals(parola.toLowerCase())))// controls to see if a word
																							// is lowcase
					tree.add(parola);
			}
		}
		bd.close();

	}

	/**
	 * methods writes distance to a file
	 * 
	 * @param distance
	 *            : distance obtained from levinshtens distance algorithm
	 * @throws IOException
	 */
	public void StampDistance(String distance, String operation) throws IOException {
		writer.write(distance);
		writer.flush();
		writer.newLine();
		writer.write(operation);
		writer.flush();
		writer.newLine();

	}

	/**
	 * The method stamps words to video
	 * 
	 * @return : returns all the words in a treeSet if empty returns a null pointer
	 * @throws IOException
	 */
	public String visualizza() throws IOException {

		sb = new StringBuilder();
		Iterator<String> it = tree.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append(" ");

		}
		writer.write("v: " + sb);
		writer.flush();
		writer.newLine();
		return "v: " + sb;
	}

	/**
	 * The method returns a TreeSet
	 * 
	 * @return
	 */
	public TreeSet<String> getTree() {
		return tree;
	}

	/**
	 * the method adds a value of type String to the TreeSet the word must contain
	 * only lowcase charaters otherwise method returns false and doesnt insert the
	 * word
	 * 
	 * @param parola
	 *            all characters in the word must be lowcase
	 * @return if true the word is added to the Treeset otherwise no operation is
	 *         done
	 */
	public boolean inserisci(String parola) {
		if (!(!parola.equals(parola.toLowerCase()))) {
			tree.add(parola);
			return true;
		}
		return false;
	}

	/**
	 * the method deletes a value of type String from the TreeSet
	 * 
	 * @param parola
	 */
	public void elimina(String parola) {
		tree.remove(parola);

	}

	public boolean contains(String parola) {
		if (tree.contains(parola)) {
			return true;
		}

		return false;
	}

	/**
	 * a schema can only contain uppercase and lowcase characters
	 * 
	 * The method searches for all the words that are in the dictionary that satisfy
	 * the schema and returns them else returns a null reference if none are found
	 * 
	 * @param schema:
	 *            a string containg atleast on uppercase character
	 * 
	 * @return: all the strings that satisfy the schema in the TreeSet
	 * @throws IOException
	 */
	public String ricerca(String schema) throws IOException {

		sb = new StringBuilder();
		String tmp;
		Iterator<String> ite = tree.iterator();
		while (ite.hasNext()) {
			tmp = ite.next();
			if (tmp.length() == schema.length())
				if (minControl(schema, tmp))
					if (instanceControl(schema, tmp)) {
						sb.append(tmp);
						sb.append(" ");
					}

		}
		writer.write("r " + schema + ": " + sb);
		writer.flush();
		writer.newLine();
		return "r " + schema + ": " + sb;
	}

	/**
	 * The method instanceControl : controls that each Upper case letter of the
	 * Schema is assigned to a unique lowcase character of the word
	 * 
	 * @param schema
	 *            a string containing upper case and lower case letters
	 * @param parola
	 *            all characters in the word must be lowcase
	 * @return :
	 */
	public static boolean instanceControl(String schema, String parola) {
		boolean ok = true;
		HashMap<Character, Character> map = new HashMap<Character, Character>();

		for (int i = 0; i < parola.length(); i++) {
			Character key = new Character(schema.charAt(i));
			Character value = new Character(parola.charAt(i));

			if (Character.isUpperCase(schema.charAt(i))) {
				if (map.containsKey(key)) {
					if (map.get(key).equals(value)) {
						continue;
					} else {
						ok = false;
						break;
					}

				} else {
					map.put(key, value);
				}

			} else {
				continue;
			}

		}
		return ok;
	}

	/**
	 * the method controls to see if the low case letters in the schema are equal to
	 * the letters in the parola in the same position
	 * 
	 * @param schema
	 *            a word containing at least one upper case letter
	 * 
	 * @param parola:
	 *            all characters in the word must be lowcase
	 * 
	 * @return : true if the lower case letter in the schema are equal to the
	 *         letters in the parola in the same positions
	 */
	public static boolean minControl(String schema, String parola) {
		boolean b = true;

		for (int i = 0; i < schema.length(); i++) {
			if (Character.isLowerCase(schema.charAt(i))) {
				if (schema.charAt(i) == parola.charAt(i)) {
					continue;
				} else {
					b = false;
					break;
				}

			} else {
				continue;
			}

		}
		return b;
	}

}
