package dataAccessLayer;

import java.util.List;
import java.util.Map;

public interface DAO {
	
	public void saveObjects(String upperCaseCreatureName, String spicies);
	
	public List<Map<String, Object>> queryForQuizQuestionsList();
	
	public List<Map<String, Object>> queryForAnswerList();
	
	public void markQuizCreaturesInDatabase(List<String> quizQuestions);
		
	public void saveAnswers(String answer, String creatureName);
	
	public int countCreatures(String species);
	
	public void updateSmallerDrawSet(String origin);
	
	public List<Map<String, Object>> drawFromLargerDrawSet(int min, String name);
	
	public void clearDatabase();

	public void updateLargerDrawSet(String creature);
	}

