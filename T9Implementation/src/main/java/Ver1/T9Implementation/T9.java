package Ver1.T9Implementation;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author sandeeprv
 * Class to implement T9 algorithm
 */
public class T9 {

	/**
	 * T9 dictionary is the hash table having the digit seq mapped to words the
	 * digit sequence is the key and the word tree is the value
	 */
	public static HashMap<String, Queue<Word>> T9dictionary = new LinkedHashMap<String, Queue<Word>>();

	/**
	 * T9dicitionary is constructed.
	 */
	public T9() {
		makeDictionary();
		System.out.println(T9dictionary);
	}

	public static void main(String[] args) {
		makeDictionary();
		String response = respondQuery(args);
		System.out.println(response);
	}

	/**
	 * @param queries
	 * @return response for the query
	 *  this method is only used when this code is run.
	 */
	public static String respondQuery(String[] queries) {
		StringBuilder response = new StringBuilder();

		for (String eachQuery : queries) {
			response.append(getResponse(eachQuery) + " ");
		}
		return response.toString();
	}

	/**
	 * @param query
	 * @return
	 *             converts the digit sequence input by the user into words and
	 *             returns it
	 */
	public static String getResponse(String query) {
		Queue<Word> WordQueue = null;
		try {
			WordQueue = getWordQueue(query);
		} catch (SequenceNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return (WordQueue.peek().toString().substring(0, query.length()));
	}

	/**
	 * @param query
	 * @return
	 * @throws SequenceNotFoundException
	 * returns the wordQueue corresponding to the given Sequence
	 */
	private static Queue<Word> getWordQueue(String query) throws SequenceNotFoundException {
		if (!T9dictionary.containsKey(query)) {
			Set<String> Keys = T9dictionary.keySet();
			for (String eachKey : Keys) {
				if (eachKey.startsWith(query)) {
					System.out.println("in loop: "+eachKey);
					return (T9dictionary.get(eachKey));
				}
			}
			throw (new SequenceNotFoundException(query) );			
		}
		return T9dictionary.get(query);
	}

	/**
	 * @param eachQuery
	 * @param mayBeThis
	 * @throws SequenceNotFoundException 
	 * @returns Word In case the Word at the head of the tree is not the
	 *          expected one, on pressing * (T9GUI) this method gets Invoked and
	 *          based on the number of times * is pressed the tree returns a
	 *          different Word everytime
	 */
	public static String notThisWord(String eachQuery, int mayBeThis) throws SequenceNotFoundException {
		Queue<Word> WordQueue = getWordQueue(eachQuery);
		List<Word> temporaryList = new ArrayList<Word>();
	
		int numberOfWords = WordQueue.size();
		int notThisWord = 1;
		System.out.println(numberOfWords + " "+mayBeThis+ " "+ (mayBeThis%numberOfWords));
		// till the tree is not empty and a different word is not encountered
		
		while ((!WordQueue.isEmpty())
				&& (notThisWord != (mayBeThis%numberOfWords)) && (mayBeThis%numberOfWords!=0)) {
			temporaryList.add(WordQueue.remove());
			System.out.println("dict " + WordQueue);

			notThisWord = (notThisWord + 1) % numberOfWords;
		}
	
		String mayBeThisWord = WordQueue.peek().toString();
		WordQueue.addAll(temporaryList);
		return mayBeThisWord.substring(0, eachQuery.length());

	}

	/**
	 * reads a dicitionary word by word adding the sequence to the T9 dictionary
	 */
	public static void makeDictionary() {

		try {
			FileInputStream fstream = new FileInputStream(
					"../T9Implementation/Dictionary.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String sequence;
			while ((strLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(strLine);
				while(st.hasMoreTokens()) {
					String word = st.nextToken();
					sequence = generateSeq(word.toLowerCase());
					insert(sequence, word.toLowerCase());
				}			
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

		/**
	 * @param word
	 * @returns an equivalent digit sequence to the word
	 */
	public static String generateSeq(String word) {
		StringBuilder sequence = new StringBuilder();
		for (char c : word.toCharArray()) 
			sequence.append(getDigit(c));
		return sequence.toString();
	}

	/**
	 * @param sequence
	 * @param word
	 *            inserts the sequence into the T9dictionary if it is not there
	 *            already, updates the wordQueue with the new word or increments
	 *            the wodfrequencey if it already exists
	 */
	public static void insert(String sequence, String word) {
		if (T9dictionary.containsKey(sequence)) {
			Queue<Word> wordQueue = T9dictionary.get(sequence);
			if (wordQueue.contains(new Word(word))) {
				Word toUpdate = removeWord(wordQueue, new Word(word));
				toUpdate.setFrequency(toUpdate.getFrequency() + 1);
				wordQueue.add(toUpdate);
			} else {
				Word ne = new Word(word);
				wordQueue.add(ne);
			}
		} else {
			Queue<Word> wordQueue = new PriorityQueue<Word>(1,
					new Comparator<Word>() {

						public int compare(Word o1, Word o) {
							if (o1.words == o.words) {
								return 0;
							} else if (o1.frequency < o.frequency) {
								return 1;
							} else {
								return -1;
							}
						}

					});
			wordQueue.add(new Word(word));
			T9dictionary.put(sequence, (Queue<Word>) wordQueue);
		}
	}

	/**
	 * @param wordQueue
	 * @param word
	 * @return Word removes the Word from the PriorityQueue and returns the
	 *         Word.
	 */
	private static Word removeWord(Queue<Word> wordQueue, Word word) {
		for (Word eachWordIn : wordQueue) {
			if (eachWordIn.equals(word)) {
				wordQueue.remove(eachWordIn);
				return eachWordIn;
			}
		}
		// since the method is called only if the Word is present return null
		// statement never gets executed
		return null;
	}

	/**
	 * @param alphabet
	 * @return digit mapped to the corresponding character
	 */
	public static char getDigit(char alphabet) {
		if (alphabet >= '0' && alphabet <= '9') {
			return alphabet;
		}
		switch (alphabet) {
		case 'a':
		case 'b':
		case 'c':
			return '2';
		case 'd':
		case 'e':
		case 'f':
			return '3';
		case 'g':
		case 'h':
		case 'i':
			return '4';
		case 'j':
		case 'k':
		case 'l':
			return '5';
		case 'm':
		case 'n':
		case 'o':
			return '6';
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return '7';
		case 't':
		case 'u':
		case 'v':
			return '8';
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return '9';
		default:
			return '1';
		}
	}
}
