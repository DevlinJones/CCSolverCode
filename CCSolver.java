import java.util.*;
import java.math.*;
import java.io.*;

public class CCSolver{

	private static CCSolverLinkedList letterFrequency;
	private static char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private static ArrayList<String> englishWords;
	private static char[] prior = {'e', 't', 'a', 'o', 'i', 'n', 's', 'r', 'h'};

	private static void setupDic(){
		int counter = 0;
		String[] words = new String [466547];
		String fetchedLine = null;
		try{
			BufferedReader buf = new BufferedReader(new FileReader("words.txt"));
			while(true){
				fetchedLine = buf.readLine();
				if(fetchedLine == null){
					break;
				}
				else{
					words[counter] = fetchedLine;
					counter++;
				}
			}
			buf.close();
			englishWords = new ArrayList<String>(Arrays.asList(words));
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private static void printStart(){
		System.out.println("Welcome to the CCSolver! Please enter a piece of text that isn't short!");
	}

	private static String[][] readInput(){
		Scanner sc = new Scanner(System.in);
		String[] firstSplit;
		String[][] finalArray;
		firstSplit = sc.nextLine().toLowerCase().split(" ");
		finalArray = new String [firstSplit.length][100];
		for(int i = 0; i < firstSplit.length; i++){
			finalArray[i] = firstSplit[i].replaceAll("[^a-zA-Z]","").split("");
		}
		return finalArray;
	}

	private static void setupLetterFreqLL(){
		LetterDT tempLetter = null;
		char tempChar;

		letterFrequency = new CCSolverLinkedList(new LetterDT(0, 'a'));
		for(int i = 1; i < 26; i++){
			tempChar = (char) ('a' + i);
			tempLetter = new LetterDT(0, tempChar);
			letterFrequency.addNodeTail(tempLetter);
		}

	}

	private static void calcLetterFreq(String[][] inputString){
		for(int i = 0; i < inputString.length; i++){
			for(int j = 0; j < inputString[i].length; j++){
				if(!inputString[i][j].equals("")){
					letterFrequency.findLetterAndUpdate(inputString[i][j].charAt(0));
				}
			}
		}

	}

	private static int determineBestFit(int inIndex){
		int key = 0;
		char ch = letterFrequency.findMostOccuring();
		int startingPos = ch - 'a';
		while(ch != prior[inIndex]){
			key++;
			if(startingPos - key < 0){
				startingPos += 26;
			}
			ch = letters[startingPos - key];
		}
		System.out.println(key);
		return key;
	}

	private static String[] decryptString(String[][] inputString, int key){
		String decryptedString = "";
		String[] returnArray = new String[2];
		int testingValue;
		for(int i = 0; i < inputString.length; i++){
			for(int j = 0; j < inputString[i].length; j++){
				if(!inputString[i][j].equals("")){
					testingValue = (int)inputString[i][j].charAt(0) - 'a';
					if(testingValue - key < 0){
						testingValue += 26;
					}
					decryptedString += letters[testingValue - key];	
				}
			}
			if(i==0){
				returnArray[1] = decryptedString;
			}
			decryptedString += " ";
		}
		returnArray[0] = decryptedString;
		return returnArray;
	}

	private static void determineIfCorrect(int indexCounter, String[][] inputString, int key){
		String [] finalString, splittingArray;
		boolean found = false;
		while(true){
			key = determineBestFit(indexCounter);
			finalString = decryptString(inputString, key);
			splittingArray = finalString[0].split(" ");
			for(int i = 0; i < splittingArray.length; i++){
				if(englishWords.contains(splittingArray[i])){
					found = true;
				}
				else{
					found = false;
				}
			}
			if(found){
				break;
			}
			else{
				indexCounter++;
			}
		}

		System.out.println(finalString[0]);
		System.out.println(key);
	}
	private static void controller(){
		String[][] inputString;
		String[] finalString, splittingArray;
		int key = 0, indexCounter = 0;
		printStart();
		setupDic();
		inputString = readInput();
		setupLetterFreqLL();
		calcLetterFreq(inputString);
		determineIfCorrect(indexCounter, inputString, key);
	}
	public static void main(String [] args){
		controller();
	}
}