public class LetterDT{
	private int number;
	private char letter;

	public LetterDT(int inNumber, char inLetter){
		number = inNumber;
		letter = inLetter;
	}

	public char getLetter(){
		return letter;
	}

	public int getNumber(){
		return number;
	}

	public void updateNumber(){
		number++;
	}
}