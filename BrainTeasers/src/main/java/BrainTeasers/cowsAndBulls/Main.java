package BrainTeasers.cowsAndBulls;
import java.util.Random;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		int validDifficultyLevel = getValidDifficultyLevel();
		int amountOfGuesses = getMaxAmountOfGuessesByDifficultyLevel(validDifficultyLevel);
		int computerNum = generateComputerNum();

		boolean isUserWon = false;

		//    !isUserWon
		while (isUserWon == false && amountOfGuesses > 0){
			int userValidGuess = getValidUserGuess();
			int amountOfExactHits = calculateAmountOfExactHits(computerNum, userValidGuess);
			int amountOfNearHits = calculateAmountOfNearHits(computerNum, userValidGuess);

			System.out.println(userValidGuess + " Exact hits :" + amountOfExactHits + " Near hit :" + amountOfNearHits);

			if (amountOfExactHits == 4){
				isUserWon = true;
			}
			else{
				amountOfGuesses--;
			}
		}

		// if (isUserWon)
		if (isUserWon == true){
			System.out.println("Winner winner !! chicken dinner");
		}
		else{
			System.out.println("The number was "+ computerNum);
		}
	}

	private static int calculateAmountOfNearHits(int computerNum, int userValidGuess) {
		int [] digits1 = splitToDigits(computerNum);
		int [] digits2 = splitToDigits(userValidGuess);
		int counter=0;
		for (int i = 0; i < digits1.length; i++) {
			for (int j = 0; j < digits2.length; j++)
				if(i != j && digits1[i] == digits2[j])
					counter ++;
		}
		return counter;
	}

	private static int calculateAmountOfExactHits(int computerNum, int userValidGuess) {
		int [] digits1 = splitToDigits(computerNum);
		int [] digits2 = splitToDigits(userValidGuess);
		int counter=0;
		for (int i = 0; i < digits1.length; i++) {
			if(digits1[i]==digits2[i])
				counter ++;
		}
		return counter;
	}

	private static int[] splitToDigits(int userValidGuess) {
		int[] split = new int[4];
		for (int i = 0; i < split.length; i++) {
			split[i]= userValidGuess % 10;
			userValidGuess /= 10;
		}

		return split;
	}

	private static int getValidUserGuess() {
		int input=0;
		do {
			String inputString = JOptionPane.showInputDialog("Enter your guess!?");
			input = Integer.parseInt(inputString);
		}while(!isValidNumber(input));
		return input;

	}

	private static int generateComputerNum() {
		Random rand = new Random();
		int number;
		do {
			number= rand.nextInt(10001);
		}while(!isValidNumber(number));
		System.out.println(number);	
		return number;
	}

	private static boolean isValidNumber(int number) {
		if (number<1234 || number>9876)	
			return false; 	//test upper & lower bounds

		//find duplicate digits
		boolean [] hasDigit = new boolean [10]; 
		do {
			int digit = number % 10 ;
			if(hasDigit[digit])
				return false;
			else
				hasDigit[digit]=true;
			number /= 10;
		}while( number != 0);
		return true;
	}

	private static int getMaxAmountOfGuessesByDifficultyLevel(int validDifficultyLevel) {
		if(validDifficultyLevel==1) 
			return 13;
		if(validDifficultyLevel==2) 
			return 10;

		return 7;
	}

	private static int getValidDifficultyLevel() {

		int userDifficultyLevel = getDifficultyLevelViaMenu();

		// Checking if invalid value
		while (userDifficultyLevel<1 || userDifficultyLevel>3){
			// Giving an error message
			JOptionPane.showMessageDialog(null, "Invalid number, please enter a difficulty level 1-3");

			// Showing the menu again, and getting a new difficulty level
			userDifficultyLevel = getDifficultyLevelViaMenu();
		}
		return userDifficultyLevel;
	}

	private static int getDifficultyLevelViaMenu() {
		System.out.println("1. Easy - 13 attempts");
		System.out.println("2. Medium - 10 attempts");
		System.out.println("3. Hard - 7 attempts");
		String strUserDifficultyLevel = JOptionPane.showInputDialog("Please choose a difficulty level 1-3");
		int difficultyLevel = Integer.parseInt(strUserDifficultyLevel);
		return difficultyLevel;
	}

}
