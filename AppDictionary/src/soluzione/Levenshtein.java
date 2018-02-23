package soluzione;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Levenshtein {

	private static int distance;
	private static String alloperation;

	public Levenshtein() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static int distance(String a, String b) {

		a = a.toLowerCase();
		b = b.toLowerCase();
		int cost;

		// i == 0
		int[][] d = new int[a.length() + 1][b.length() + 1];
		// set 1st line and 1st column to i
		for (int i = 0; i <= a.length(); i++)
			d[i][0] = i;
		for (int j = 0; j <= b.length(); j++)
			d[0][j] = j;

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				// str1[ i1 - 1 ] = str2[ i2 - 1 ]
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					cost = 0;
					d[i][j] = d[i - 1][j - 1];
				} else {
					cost = 1;
					d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, // inserimento
							d[i][j - 1] + 1), // cancellazione
							d[i - 1][j - 1] + cost); // sostituzione

					if (i > 1 && j > 1 && a.charAt(i - 1) == b.charAt(j - 2) && a.charAt(i - 2) == b.charAt(j - 1)) {
						d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost); // transposition

					}

				}

			}

		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		StringBuilder sb = new StringBuilder();
		StringBuilder s1 = new StringBuilder(" " + a);
		StringBuilder s2 = new StringBuilder(" " + b);
		StringBuilder temp= new StringBuilder();

		

		int i = d.length - 1;
		int j = d[0].length - 1;
		while (i > 1 && j > 1) {
			int a0 = d[i][j];
			int a1 = d[i - 1][j - 1];
			int a2 = d[i][j - 1];
			int a3 = d[i - 1][j];

			if (a0 == a1 && a1 == a2 && a2 == a3) {
				sb.append("sca" + "(" + (i - 1) + ")" + " ");
				i = i - 2;
				j = j - 2;
			} else if ((a1 < Math.min(a2, a3) || (a1 == a2 && a2 == a3)) && (a1 == a0 || a1 == a0 - 1)) {
				if (a1 == a0 - 1) {
					
					char c = s2.charAt(i); // which character of the second string was substition in the first string
					sb.append("sos" + "(" + (i)+ "," + c + ")" + " ");
					i--;
					j--;
				} else {
					// sb.append("notOp ");
					i--;
					j--;
				}

			} else if (a2 <= a3 && (a2 == a0 || a2 == a0 - 1)) {

				char c = s2.charAt(i +1);
				sb.append("ins" + "(" + (i+1) + "," + c + ")" + " ");
				j--;
			} else {
				char c = s1.charAt(i-2);
				sb.append("del" + "(" + (i-1) + "," + c + ")" + " ");
				i--;
			}

		}
		/*
		 * stamps the sb instance in reverse
		 */
		ArrayList<String> mylist = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(sb.toString());
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			mylist.add(tmp);
		}
		StringBuilder ap = new StringBuilder();
		for (int k = mylist.size() - 1; k >= 0; k--) {
			ap.append(mylist.get(k));

		}

		alloperation = ap.toString();
		distance = d[a.length()][b.length()];
		return distance;

	}

	/**
	 * returns a String instance that contains all the elementary operations that
	 * were done to modify the string Elementary operations are
	 * ,Transportion,insert,delete substitution
	 */
	@Override
	public String toString() {
		return "op: " + alloperation;
	}

}
