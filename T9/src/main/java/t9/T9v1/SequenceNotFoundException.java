package t9.T9v1;

/**
 * @author sandeeprv
 *throws an Exception if the user query sequence is not found 
 */
@SuppressWarnings("serial")
public class SequenceNotFoundException extends Exception{
	public String msg;
	public SequenceNotFoundException(String sequence) {
		msg = new String(sequence + ": corresponding word not found in Dictionary");
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
}
