package view;

public interface Dialog {

	void downloadingData();

	void showNumberOfPokemonsDownloaded(int numberOfPokemons);

	void showNumberOfStarWarriorsDownloaded(int numberOfStarWarriors);

	void showDrawSetSize(int i);

	void downloadMoreData();

	void printInstructions();

	void markQuizEnd();

	void askQuestion(int i, String creatureName);

	void printSummaryIntro();

	void printNumberOfCorrectAnswers(int correctAnswers);

	void sumUpPokemonAnswer(String creatureName);

	void sumUpStarWarriorAnswer(String creatureName);

	void sumUpMaleformedAnswer(String creatureName, String answer);

	void summarizeCorrectPokemonAnswer();

	void summarizeCorrectStarWarriorAnswer();

	void summarizeIncorrectAnswer();

	void makeSomeSpace();

	void commentLowScore();

	void commentMediumScore();

	void commentHighScore();

	void informAboutIncorrectAnswer();

	void printExceptionMessage();

}
