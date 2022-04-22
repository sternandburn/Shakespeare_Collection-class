/** * This class contains the code for the <name> assignment.  
 * 
 * Shakespeare  
 * @version 1.0, 11/08/19  
 * @author Noah Stern 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class Shake {
	public static void main(String[] args) throws IOException {
		Set<String> mySet = new HashSet<>();
		LinkedList<String> list = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("shakespeare.txt")));
		FileReader file = new FileReader("shakespeare.txt ");
		;

		//finds each unique word in the file and increments a count
		int uniques = 0;
		while (br.ready()) {
			String line = br.readLine();
			String[] words = line.split("\\W");
			for (String word : words) {
				String cleanword = (word.toLowerCase().trim());
				if (cleanword.length() > 0) {
					// add word to set
					mySet.add(cleanword);
					list.add(cleanword);
					uniques++;
				}
			}
		}
		
		System.out.println("Number of uniqie words: " + uniques);

		//finds the last 5 words alphabetically
		System.out.println("The last 5 words alphabetically are: ");
		Collections.sort(list);
		for(int i = list.size() - 5; i < list.size(); i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println();
		
		//finds the 5 most occuring words in the file
		HashMap<String, Integer> wordcount = new HashMap<String, Integer>();

		try {

			BufferedReader in = new BufferedReader(new FileReader("shakespeare.txt"));

			String str;

			while ((str = in.readLine()) != null) {
				str = str.toLowerCase(); // convert to lower case

				int idx1 = -1;

				for (int i = 0; i < str.length(); i++) {

					if ((!Character.isLetter(str.charAt(i))) || (i + 1 == str.length())) {

						if (i - idx1 > 1) {

							if (Character.isLetter(str.charAt(i)))
								i++;

							String word = str.substring(idx1 + 1, i);

							if (wordcount.containsKey(word)) {

								wordcount.put(word, wordcount.get(word) + 1);
							} else {

								wordcount.put(word, 1);
							}
						}

						idx1 = i;
					}
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		List<Map.Entry<String, Integer>> entries = new LinkedList<>(wordcount.entrySet());
		List<Map.Entry<String, Integer>> tops = new LinkedList<>();
		Comparator<Map.Entry<String, Integer>> valueComp = Comparator
				.comparing((Map.Entry<String, Integer> t) -> t.getValue());
		int topcount = 0;
		while (topcount < 5 && !entries.isEmpty()) {
			Map.Entry<String, Integer> max = Collections.max(entries, valueComp);
			tops.add(max);
			entries.remove(max);
			topcount++;
		}
		System.out.println("The top 5 occurring words are: ");
		System.out.println(tops);

	}
}
