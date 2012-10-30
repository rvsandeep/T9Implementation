package t9.T9v1;


/**
 * Word Class has two fields, one is the word itself and another is the number
 * of occurrences of the word
 */
public class Word {
	String words;
	int frequency;

	public Word(String word) {
		this.words = word;
		this.frequency = 1;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object) Overrided equals returns
	 * true if the word matches false otherwise (frequency is ignored)
	 */
	@Override
	public boolean equals(Object word) {
//		System.out.println("My compare function : "+((Word) this).words + " " + ((Word) word).words);
		if (((Word) this).words.equals(((Word) word).words )) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
//		return this.words + " " + this.frequency;
		return this.words;
	}

}
