package view;

import org.springframework.stereotype.Component;

@Component
public class ConsoleDialog implements Dialog{

	public void downloadingData() {
		System.out.println("Downloading data from external JSON APIs ...");
		System.out.println();
	}
	
	public void showNumberOfPokemonsDownloaded(int numberOfPokemons) {
		System.out.println("The number of Pokemons downloaded: ");
		System.out.println(numberOfPokemons);
	}
	
	public void showNumberOfStarWarriorsDownloaded(int numberOfStarWarriors) {
		System.out.println("The number of Star Wars characters downloaded: ");
		System.out.println(numberOfStarWarriors);
	}
	
	public void showDrawSetSize(int number) {
		System.out.println("Draw set size:");
		System.out.println(number);
	}
	
	public void downloadMoreData() {
		System.out.println("Download more data to perform the quiz");
	}
	
	public void printInstructions() {
		System.out.println();
		System.out.println("Ready! Let's start the quiz!!!");
		System.out.println();
		System.out
				.println("Proper answer format is the letter: \"p\" or \"s\" (p - Pokemon, s - Star Wars character).");

		System.out.println("Other forms of responses will be considered incorrect.");
		System.out.println();
	}
	
	public void markQuizEnd() {
		System.out.println();
		System.out.println("This is the end of the quiz!");
	}
	
	public void askQuestion(int index, String name) {
		System.out.println();
		System.out.println(index + ". Is " + name
				+ " a Pokemon or a Star Wars character? (p/s)");
	}
	
	public void informAboutIncorrectAnswer() {
		System.out.println("Unfortunately your answer is incorrect. Use only letters p or s!");
	}
	
	public void printNumberOfCorrectAnswers(int correctAnswers) {
		System.out.println("The number of correct answers is: " + correctAnswers);
		System.out.println();
	}
	
	public void sumUpPokemonAnswer(String name){
		System.out.println("You were asked about the creature " + name + ". You thought he was a Pokemon.");
	}
	
	public void sumUpStarWarriorAnswer(String name) {
		System.out.println("Is " + name + " really a Star Warrior?");
	}
	
	public void sumUpMaleformedAnswer(String name, String answer) {
		System.out.println("Your answer for the question about " + name + " was: " + answer + ".");
	}
	
	public void summarizeCorrectPokemonAnswer() {
		System.out.println("Good! That's right!.");
	}
	
	public void summarizeCorrectStarWarriorAnswer() {
		System.out.println("Yes. Your answer was correct.");
	}
	
	public void summarizeIncorrectAnswer() {
		System.out.println("No it's not. You were wrong.");
	}
	
	public void makeSomeSpace() {
		System.out.println();
	}
	
	public void printSummaryIntro() {
		System.out.println();
		System.out.println("Now a brief summary:");
		System.out.println();
	}
	
	public void commentLowScore(){
		System.out.println("You’re a SF Noob! ");
	}	
	
	public void commentMediumScore(){
		System.out.println("Looks like you’re a huge SF fan!");
	}
		
	public void commentHighScore(){
		System.out.println("You’re a SF master! Get out of your basement and see the outside world!");
	}
	
	public void printExceptionMessage(){
		System.out.println("Unexpected exception occurred");
	}
}
