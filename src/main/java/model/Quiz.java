package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dataAccessLayer.DAO;
import view.Dialog;

@Service
public class Quiz {
	
	@Autowired
	private DAO dao;
		
	@Autowired
	private Dialog dialog; 

	int numberOfStarWarriors;
	int numberOfPokemons;
	int minCreatureNumber;
	private List<String> quizQuestions = new ArrayList<String>();
	
	public void prepareQuiz() throws Exception {
		prepareDrawSet();
		prepareQuestions();		
	}
	
	public void prepareDrawSet() throws Exception{	
		findMinCreatureNumber();
		informAboutNumberResults();
		checkIfEnoughtCreatures();
		markDrawSet();
	}
	
	public void prepareQuestions() {
		prepareQuestionList();
		dao.markQuizCreaturesInDatabase(quizQuestions);		
	}
	
	public void findMinCreatureNumber() {
		numberOfPokemons = dao.countCreatures("Pokemon");
		numberOfStarWarriors = dao.countCreatures("StarWarrior");
		minCreatureNumber = numberOfPokemons<numberOfStarWarriors ? numberOfPokemons:numberOfStarWarriors;
	}
	
	public void informAboutNumberResults() {
		dialog.showNumberOfPokemonsDownloaded(numberOfPokemons);
		dialog.showNumberOfStarWarriorsDownloaded(numberOfStarWarriors);
		dialog.showDrawSetSize(minCreatureNumber*2);	
	}
	
	public void checkIfEnoughtCreatures() throws Exception{
		if (minCreatureNumber<8) {
			dialog.downloadMoreData();
			throw new Exception("Not enought data to perform a quiz");
		}	
	}
	
	public void markDrawSet() {
		 if (numberOfStarWarriors<=numberOfPokemons) {
			dao.updateSmallerDrawSet("StarWarrior");
			List<String> creaturesFromLargerSetToBeMarkedAsDrawSet = markAsDrawSetCreaturesFromLargerCreatureSet("Pokemon");
			creaturesFromLargerSetToBeMarkedAsDrawSet.forEach(creature -> dao.updateLargerDrawSet(creature));
		}
		else {
			dao.updateSmallerDrawSet("Pokemon");
			List<String> creaturesFromLargerSetToBeMarkedAsDrawSet = markAsDrawSetCreaturesFromLargerCreatureSet("StarWarrior");
			creaturesFromLargerSetToBeMarkedAsDrawSet.forEach(creature -> dao.updateLargerDrawSet(creature));
		}
	}
	
	public List<String> markAsDrawSetCreaturesFromLargerCreatureSet(String name){
		List<String> creaturesFromLargerSetToBeMarkedAsDrawSet = new ArrayList<String>();
		List<Map<String, Object>> rows = dao.drawFromLargerDrawSet(minCreatureNumber, name);
		for (Map<String, Object> row : rows) {
			String creature = (String) row.get("name");
			creaturesFromLargerSetToBeMarkedAsDrawSet.add(creature);
		}
		return creaturesFromLargerSetToBeMarkedAsDrawSet;
	}
		
	public void prepareQuestionList() {
		List<Map<String, Object>> rows = dao.queryForQuizQuestionsList();
		for (Map<String, Object> row : rows) {
			String creatureName = (String) row.get("name");
			quizQuestions.add(creatureName);
		}
	} 	
	
	public List<String> getQuizQuestions() {
		return quizQuestions;
	}

	public int getNumberOfStarWarriors() {
		return numberOfStarWarriors;
	}

	public void setNumberOfStarWarriors(int numberOfStarWarriors) {
		this.numberOfStarWarriors = numberOfStarWarriors;
	}

	public int getNumberOfPokemons() {
		return numberOfPokemons;
	}

	public void setNumberOfPokemons(int numberOfPokemons) {
		this.numberOfPokemons = numberOfPokemons;
	}

	public int getMinCreatureNumber() {
		return minCreatureNumber;
	}

	public void setMinCreatureNumber(int minCreatureNumber) {
		this.minCreatureNumber = minCreatureNumber;
	}
	
	
}
